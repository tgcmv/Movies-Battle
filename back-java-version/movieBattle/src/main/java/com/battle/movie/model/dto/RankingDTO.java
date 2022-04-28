package com.battle.movie.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingDTO implements Serializable {

	@NotBlank
	@Getter
	private String name;

	@NotBlank
	private BigDecimal score;

	@JsonIgnore
	private Integer wrong;

	@JsonIgnore
	private Integer rounds;

	@JsonIgnore
	private Integer points;
}
