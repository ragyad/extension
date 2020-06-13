package com.ragini.spotify.extension.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// Fields
// "artists": [{
//        "external_urls": {
//        "spotify": "https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q"
//        },
//        "href": "https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q",
//        "id": "08td7MxkoHQkXnWAYD8d6Q",
//        "name": "Tania Bowra",
//        "type": "artist",
//        "uri": "spotify:artist:08td7MxkoHQkXnWAYD8d6Q"
//        }],
//"available_markets": ["AD"],
//"disc_number": 1,
//"duration_ms": 276773,
//"explicit": false,
//"external_urls": {
//      "spotify": "https://open.spotify.com/track/2TpxZ7JUBn3uw46aR7qd6V"
// },
//"href": "https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V",
//"id": "2TpxZ7JUBn3uw46aR7qd6V",
// "is_local": false,
//"name": "All I Want",
// "preview_url": "https://p.scdn.co/mp3-preview/12b8cee72118f995f5494e1b34251e4ac997445e?cid=7c46dcfd83164918861d70e2bcb74247",
// "track_number": 1,
// "type": "track",
//  "uri": "spotify:track:2TpxZ7JUBn3uw46aR7qd6V"

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("track_number")
    private String trackNumber;

    @JsonProperty("artists")
    private List<Artist> artists;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Track){
            Track track1 = (Track) obj;
            if(track1.getName()!=null && track1.getName().equals(getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
