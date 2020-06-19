package com.ragini.spotify.extension;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ragini.spotify.extension.beans.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SpotifyClient extends SpotifyClientBase{

    List<Artist> artists = null;
    Artist selectedArtist = null;


    @GetMapping("/searchArtists")
    public String searchArtists(@RequestParam("q") String q) {
        artists = searchArtists(q, "artist");
        artists = artists.stream().filter(artist -> artist.getImages()!=null && !artist.getImages().isEmpty()).collect(Collectors.toList());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(artists);
        //return artists;
    }

    @GetMapping("/Artist")
    public String getArtist(@RequestParam("id") String artistId) {
        List<Artist> temp = null;
        if(artists!=null) {
            temp = artists.stream().filter(artist -> artist.getId().equals(artistId)).collect(Collectors.toList());
        }
        selectedArtist = temp!=null?temp.get(0):null;
        Gson gson = new Gson();
        return gson.toJson(selectedArtist);
    }

    @GetMapping("/createPlaylist")
    public String createPlaylist(@RequestParam("id") String artistId) {
        //get all albums from the above id
        List<Album> albums = getAlbumsByArtist(selectedArtist.getId());

        //get all tracks from those albums
        Set<Track> tracks = new HashSet<>();
        albums.stream().forEach((entity) -> tracks.addAll(getTracksByAlbumIdAndFilterByArtistId(entity.getId(), selectedArtist.getId())));

        //get current users profile for user id
        User user = getCurrentUser();

        //create playlist by the name of the artist
        Playlist playlist = createPlaylist(selectedArtist.getName(), "songs of" + selectedArtist.getName(), false, user.getId());

        //add above tracks to the artist
        String response = addTracksToPlaylist(playlist.getId(), tracks);

        return response;
    }
}
