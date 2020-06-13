package com.ragini.spotify.extension;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/abc")
public class JaxrsConfig extends ResourceConfig {

    @PostConstruct
    public void init(){
        registerEndPoints();
    }

    private void registerEndPoints(){
        register(SpotifyClient.class);
    }
}
