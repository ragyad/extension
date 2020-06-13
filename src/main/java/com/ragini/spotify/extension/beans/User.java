package com.ragini.spotify.extension.beans;

//"display_name": "ragini18",
//"external_urls": {
//  "spotify": "https://open.spotify.com/user/14zrxqt4icxzmz4o3ej6rbdx7"
//},
//"followers": {
//"href": null,
//"total": 0
//},
//"href": "https://api.spotify.com/v1/users/14zrxqt4icxzmz4o3ej6rbdx7",
//"id": "14zrxqt4icxzmz4o3ej6rbdx7",
//"images": [],
//"type": "user",
//"uri": "spotify:user:14zrxqt4icxzmz4o3ej6rbdx7"

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private String id;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("uri")
    private String uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
