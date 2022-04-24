package com.battle.movie.battle.core;


import com.battle.movie.battle.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

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

    public Movie getMovie(){
        //TODO pegar um filme random do banco e consultar na api
        return Movie.builder()
                .title(UUID.randomUUID().toString())
                .imdbID(UUID.randomUUID().toString())
                .imdbRating(new BigDecimal(Math.random()))
                .build();
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
