package com.ragini.spotify.extension;

import com.ragini.spotify.extension.beans.*;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Path("/SpotifyClient")
public class SpotifyClient extends SpotifyClientBase{

    List<Artist> artists = null;
    Artist selectedArtist = null;

    @GET
    @Path("/searchArtists")
    public List<Artist> searchArtists( @QueryParam("q") String q) {
        artists = searchArtists(q, "artist");
        return artists;
    }

    @GET
    @Path("/{id}")
    public Artist getArtist(@PathParam("id") String artistId) {
        List<Artist> temp = null;
        if(artists!=null) {
            temp = artists.stream().filter(artist -> artist.getId().equals(artistId)).collect(Collectors.toList());
        }
        selectedArtist = temp!=null?temp.get(0):null;
        return selectedArtist;
    }

    @GET
    @Path("/{id}")
    public String createPlaylist(@PathParam("id") String artistId) {
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
