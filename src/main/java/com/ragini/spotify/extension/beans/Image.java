package com.ragini.spotify.extension.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//{//3 sizes 640*640, 300*300, 64*64
//        "height": 640,
//        "url": "https://i.scdn.co/image/ab67616d0000b2731ae2bdc1378da1b440e1f610",
//        "width": 640
//        },
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    @JsonProperty("height")
    private String height;

    @JsonProperty("url")
    private String impURL;

    @JsonProperty("width")
    private String width;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImpURL() {
        return impURL;
    }

    public void setImpURL(String impURL) {
        this.impURL = impURL;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
