package com.battle.movie.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String token;
    private String type;
}
