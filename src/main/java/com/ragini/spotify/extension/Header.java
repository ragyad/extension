package com.ragini.spotify.extension;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class Header implements ContainerRequestFilter {

    private static final String token = "BQC9MURu-wR8Z_Uz1XCXEJxSPsWyfZJwYZBI0lpf8gy378g5PUySIAcWR-GSmt8ZHEKXfzv76jaM8u6Rt-3UL3dj-e0lBqAjQO2kUa6yA1qfds6BOLMX5MkwTdA8sMsVhIi8xXK7jkq3xBlNHyXVcUvXue4gddikHIq5hcnNaoYOyfZdm1h-GurmQzPsbJRX7m7zOmPmYduBiB0EQv9QEfjWgjC3C58";


    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        containerRequestContext.getHeaders().add("content-type","application/json");
        containerRequestContext.getHeaders().add("Authorization",
                "Bearer " + token);
    }

}
