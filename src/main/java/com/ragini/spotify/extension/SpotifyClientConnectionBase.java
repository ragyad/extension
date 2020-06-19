package com.ragini.spotify.extension;


import com.ragini.spotify.extension.beans.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.logging.Logger;

public class SpotifyClientConnectionBase {

    private static final String token = "BQAx-6NCnRjzY6Ewu66CFNKli_yV2g7BplZiUotdk9m2ZHT7UAp30KJXSaifXzilKQIhLxgZzyshk3bjTmDp1JdJqRrGE8YYkKITWUwYtE1MYTrTWk6PypXWxFygy6rMejb7fD_FQSxaMtA4dOiZvaeoIcg7FMPlde1USjWZr9Fft2Bt3DN0CIrvFzx4nB5EIY6jtNPLLkf5iK6N4MPYa0a9XLjWD_U";
    private static final Client client = ClientBuilder.newClient();
    private static final Logger logger = Logger.getLogger(SpotifyClientConnectionBase.class.getName());

    public SpotifyClientConnectionBase() {
        //client.register(Header.class);
    }

    public String getResponse(String endPoint, Map<String, Object> queryParams, String httpMethod, Object objectToPost){
        WebTarget webTarget = client.target(endPoint);
        if(queryParams!=null) {
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
            }
        }

        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
                .header("content-type","application/json")
                .header("Authorization",
                        "Bearer " + token);

        Response response = null;
        if(Constants.GET.equals(httpMethod)) {
            response = builder.get();
        }else if(Constants.POST.equals(httpMethod)){
            response = builder.post(Entity.text(objectToPost==null ? "" : objectToPost));
        }
        String responseStr = response!=null ? response.readEntity(String.class) : null;
        // log error
        if(validate(responseStr)) {
            return responseStr;
        }

        return null;
    }

    private boolean validate(String responseStr){
//        if(responseStr.contains(Constants.ERROR)) {
//            Gson g = new Gson();
//            JSONObject obj = g.fromJson(responseStr, JsonObject.class);
//            JSONObject val = (JsonObject) obj.get("error");
//            Error error = g.fromJson(val, Error.class);
//            logger.log(Level.SEVERE, error.getStatus() + " : " + error.getMessage());
//            return false;
//        }
        return true;
    }

}
