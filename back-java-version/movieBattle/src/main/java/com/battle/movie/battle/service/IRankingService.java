package com.battle.movie.battle.service;

import com.battle.movie.battle.model.dto.RankingDTO;
import com.battle.movie.battle.service.impl.RankingService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface IRankingService {
    Collection<RankingDTO> getTopRanking();
}
