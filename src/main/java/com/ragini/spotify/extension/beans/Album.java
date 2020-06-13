package com.ragini.spotify.extension.beans;

//Fields
//{
//        "album_group": "album",
//        "album_type": "album",
//        "artists": [
//        {
//        "external_urls": {
//        "spotify": "https://open.spotify.com/artist/0tC995Rfn9k2l7nqgCZsV7"
//        },
//        "href": "https://api.spotify.com/v1/artists/0tC995Rfn9k2l7nqgCZsV7",
//        "id": "0tC995Rfn9k2l7nqgCZsV7",
//        "name": "Prateek Kuhad",
//        "type": "artist",
//        "uri": "spotify:artist:0tC995Rfn9k2l7nqgCZsV7"
//        }
//        ],
//        "available_markets": [
//        "AD",
//        ],
//        "external_urls": {
//        "spotify": "https://open.spotify.com/album/1no8c4PYhEMGKXtGg79fMR"
//        },
//        "href": "https://api.spotify.com/v1/albums/1no8c4PYhEMGKXtGg79fMR",
//        "id": "1no8c4PYhEMGKXtGg79fMR",
//        "images": [
//        {
//        "height": 640,
//        "url": "https://i.scdn.co/image/ab67616d0000b273438dc50d2695ba85b1be6e08",
//        "width": 640
//        },
//        {
//        "height": 300,
//        "url": "https://i.scdn.co/image/ab67616d00001e02438dc50d2695ba85b1be6e08",
//        "width": 300
//        },
//        {
//        "height": 64,
//        "url": "https://i.scdn.co/image/ab67616d00004851438dc50d2695ba85b1be6e08",
//        "width": 64
//        }
//        ],
//        "name": "In Tokens & Charms (Deluxe Edition)",
//        "release_date": "2017-11-10",
//        "release_date_precision": "day",
//        "total_tracks": 14,
//        "type": "album",
//        "uri": "spotify:album:1no8c4PYhEMGKXtGg79fMR"

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("total_tracks")
    private Integer totalTracks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
