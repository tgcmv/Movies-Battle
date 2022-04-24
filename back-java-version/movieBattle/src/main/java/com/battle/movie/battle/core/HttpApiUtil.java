package com.battle.movie.battle.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpApiUtil {

    public static String httpGet(String stringURL, Map<String, String> parameters) {

        //TODO get api key with env ?apikey=38767cb0&i=tt0458339
        try {
            URL url = new URL(stringURL + "?" + HttpApiUtil.getParamsString(parameters));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();

            return content.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
