package com.pofolio.web.development.project.NovaMarket.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {

    public static String payloadJWTExtraction(String token){
        token.replace("Bearer", "");

        //Separate the three parts of a token by a full-stop or a period.
        String[] chunks = token.split("\\.");
        Base64.Decoder decorder = Base64.getUrlDecoder();

        //chunks at the first index is going to be the second element because arrays start at zero
        //So this is going to be the payload
        String payload = new String(decorder.decode(chunks[1]));

        //Split the entries inside payload by ",".
        String[] entries = payload.split(",");
        Map<String, String> map = new HashMap<>();

        for(String entry: entries) {
            //key values are separated by :
            String[] keyValue = entry.split(":");
            if (keyValue[0].equals("\"sub\"")) {
                int remove = 1;
                if (keyValue[1].endsWith("}")) {
                    remove = 2;
                }
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
                keyValue[1] = keyValue[1].substring(1);

                map.put(keyValue[0], keyValue[1]);
            }
        }
        if(map.containsKey("\"sub\"")){
            return map.get("\"sub\"");
        }
        return null;
    }

}
