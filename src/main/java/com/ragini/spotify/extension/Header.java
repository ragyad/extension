package com.ragini.spotify.extension;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class Header implements ContainerRequestFilter {

    private static final String token = "BQBiHtLXYYOoi-xhs6xAvLc2-ukq9oXBHZGRxQ177ekq2gGIgk_VxNU4RrQ_DTKEpE3FSpLQ3dB-Z1_iomrMDEYasVCwVwxaN72SLl7uDTZuGyYL5khJq7HaWpvVAOhW8wlLDjxF2Il1-vbRyawgyq3QfG_C9QIKYu3xzDu2OdH7ZZ8vVFCy2Ei70RSiy8lIeftUHQ0vQe2i4ovQQdNVvA4IurOyaZs";


    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        containerRequestContext.getHeaders().add("content-type","application/json");
        containerRequestContext.getHeaders().add("Authorization",
                "Bearer " + token);
    }

}
