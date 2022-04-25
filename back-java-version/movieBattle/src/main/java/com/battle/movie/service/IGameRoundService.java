package com.battle.movie.service;

import com.battle.movie.model.GameRound;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface IGameRoundService extends IReaderService<String, GameRound>, IWriterService<String, GameRound>{

	public Collection<GameRound> filter(Optional<String> description, Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice);
}
