package com.ragini.spotify.extension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ragini.spotify.extension.beans.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;
import java.util.stream.Collectors;

public class SpotifyClientBase {

    SpotifyClientConnectionBase connectionBase;

    private final String BASE_URL = "https://api.spotify.com/v1";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private JsonParser jsonParser;


    public SpotifyClientBase(){
        connectionBase = new SpotifyClientConnectionBase();
    }

    public List<Artist> searchArtists(String q, String type){
        Map queryParams = new HashMap();
        queryParams.put("q", q);
        queryParams.put("type", type);
        List<Artist> artists = new ArrayList<Artist>();
        String responseStr = connectionBase.getResponse(BASE_URL+"/search", queryParams, Constants.GET, null);
        try {
            JSONObject obj = (JSONObject) (new JSONParser()).parse(responseStr);
            obj = (JSONObject) obj.get("artists");
            JSONArray arr = (JSONArray) obj.get("items");
            if (arr != null) {
                arr.stream().forEach((entity) -> {
                    try {
                        artists.add(objectMapper.readValue(entity.toString(), Artist.class));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
            }
            } catch(ParseException e){
                e.printStackTrace();
            }


        return artists;
    }

    public List<Album> getAlbumsByArtist(String artistId){
        String endPoint = BASE_URL+"/artists/" + artistId + "/albums";
        String responseStr = connectionBase.getResponse(endPoint, null, Constants.GET, null);
        List<Album> albums = new ArrayList<Album>();
        try {
            JSONObject obj = (JSONObject) (new JSONParser()).parse(responseStr);
            JSONArray arr = (JSONArray) obj.get("items");
            arr.stream().forEach(entity-> {
                try {
                    albums.add(objectMapper.readValue(entity.toString(), Album.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public List<Track> getTracksByAlbumIdAndFilterByArtistId(String albumId, String artistId){
        //https://api.spotify.com/v1/albums/{id}/tracks
        String endPoint = BASE_URL+"/albums/" + albumId + "/tracks";
        Map queryParams = new HashMap();
        queryParams.put("limit", 10);
        String responseStr = connectionBase.getResponse(endPoint, queryParams, Constants.GET, null);
        List<Track> tracks = new ArrayList();
        try {
            JSONObject obj = (JSONObject) (new JSONParser()).parse(responseStr);
            JSONArray arr = (JSONArray) obj.get("items");
            arr.stream().forEach(entity-> {
                try {
                    Track trackTobeAdded = objectMapper.readValue(entity.toString(), Track.class);
                    List<Artist> trackArtists = trackTobeAdded.getArtists().stream().filter(artist -> artist.getId().equals(artistId)).collect(Collectors.toList());
                    if(trackArtists != null && !trackArtists.isEmpty()){
                        tracks.add(trackTobeAdded);
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public User getCurrentUser(){
        //https://api.spotify.com/v1/me
        String endPoint = BASE_URL + "/me";
        String responseStr = connectionBase.getResponse(endPoint, null, Constants.GET, null);
        User currentUser = null;
        try {
            currentUser = objectMapper.readValue(responseStr, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return currentUser;
    }

    public Playlist createPlaylist(String playlistName, String playlistDescription, boolean isPublic, String userId){
        //https://api.spotify.com/v1/users/{user_id}/playlists
        String endPoint = BASE_URL + "/users/" + userId + "/playlists";

        Playlist playlistToBeCreated = new Playlist();
        playlistToBeCreated.setName(playlistName);
        playlistToBeCreated.setDescription(playlistDescription);
        playlistToBeCreated.setPublic(isPublic);

        Playlist createdPlaylist = null;
        try {
            String objectToPost = objectMapper.writeValueAsString(playlistToBeCreated);
            String responseStr = connectionBase.getResponse(endPoint, null, Constants.POST, objectToPost);
            createdPlaylist = objectMapper.readValue(responseStr, Playlist.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createdPlaylist;
    }

    public String addTracksToPlaylist(String playlistId, Set<Track> tracks){
        //https://api.spotify.com/v1/playlists/{playlist_id}/tracks

        String endPoint = BASE_URL + "/playlists/" + playlistId + "/tracks";
        StringBuilder str = new StringBuilder();
        tracks.stream().forEach(entity -> str.append(entity.getUri()).append(','));
        str.deleteCharAt(str.length()-1);
        Map queryParams = new HashMap();
        queryParams.put("uris", str.toString());

        String responseStr = connectionBase.getResponse(endPoint, queryParams, Constants.POST, null);
        return responseStr.contains(Constants.ERROR) ? Constants.FAILURE : Constants.SUCCESS;
    }
}
