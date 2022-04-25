package com.battle.movie.battle.service;

import com.battle.movie.battle.model.dto.RankingDTO;

import java.util.Collection;

public interface IRankingService {
    Collection<RankingDTO> getTopRanking();
}
