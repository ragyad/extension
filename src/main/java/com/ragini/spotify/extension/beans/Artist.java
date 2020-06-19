package com.ragini.spotify.extension.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//Fields
//"external_urls": {"spotify": "https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q"},
//"followers": {"href": null,"total": 203},
//"genres": [],
//"href": "https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q",
//"id": "08td7MxkoHQkXnWAYD8d6Q",
//"images": [
//        {
//        "height": 640,
//        "url": "https://i.scdn.co/image/ab67616d0000b2731ae2bdc1378da1b440e1f610",
//        "width": 640
//        },
//        {
//        "height": 300,
//        "url": "https://i.scdn.co/image/ab67616d00001e021ae2bdc1378da1b440e1f610",
//        "width": 300
//        },
//        {
//        "height": 64,
//        "url": "https://i.scdn.co/image/ab67616d000048511ae2bdc1378da1b440e1f610",
//        "width": 64
//        }
//        ],
//        "name": "Tania Bowra",
//        "popularity": 1,
//        "type": "artist",
//        "uri": "spotify:artist:08td7MxkoHQkXnWAYD8d6Q"

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("images")
    private List<Image> images;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
