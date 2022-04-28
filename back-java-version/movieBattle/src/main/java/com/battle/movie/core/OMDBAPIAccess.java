package com.battle.movie.core;


import com.battle.movie.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OMDBAPIAccess {

    public static final String URL = "http://www.omdbapi.com";
    private static OMDBAPIAccess api;

    public static OMDBAPIAccess getInstance(){
        if(api == null) {
            api = new OMDBAPIAccess();
        }
        return api;
    }

    public Movie  getMovie(String imdbID) throws IOException {
        String result = HttpApiUtil.httpGet(URL, getParamsById(imdbID));
        return mapperMovie(result);
    }

    private Movie mapperMovie(String result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(result, Movie.class);
    }

    private HashMap<String, String> getParamsById(String imdbID) {
        HashMap<String, String> param = new HashMap<>();
        //TODO get api key with env ?apikey=38767cb0
        param.put("apikey", "38767cb0");
        param.put("i", imdbID);
        return param;
    }
}
