package com.ragini.spotify.extension;


import com.ragini.spotify.extension.beans.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.logging.Logger;

public class SpotifyClientConnectionBase {

    private static final String token = "BQBrvQeQnb86nRFM5B4xIneBJ1Pcq1StMSsejexrKVaee6azXnHfIoUDBtRy2Hx4fByPsUrhYohsZHEwQBErsp-fTbdznBBs9VLr-kViadzEKz-wWP62zEDq6FjRko8J7dG6NlLDf40sC7RfNQmvJP8HMfzb_C2-kPHpsB5zFvsCfS4kMweQhH9-p8JRc2pDUm5nN1MP74WlixTlj9mFK1xXzvtI94g";
    private static final Client client = ClientBuilder.newClient();
    private static final Logger logger = Logger.getLogger(SpotifyClientConnectionBase.class.getName());

    public SpotifyClientConnectionBase() {
        client.register(Header.class);
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
