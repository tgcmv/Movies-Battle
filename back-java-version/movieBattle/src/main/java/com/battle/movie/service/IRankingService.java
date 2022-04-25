package com.battle.movie.service;

import com.battle.movie.model.dto.RankingDTO;

import java.util.Collection;

public interface IRankingService {
    Collection<RankingDTO> getTopRanking();
}
