package com.battle.movie.battle.model.dto;

import com.battle.movie.battle.model.GameRound;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;



@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserGameRoundDTO implements Serializable {

	private static final long serialVersionUID = -8802512413689790338L;

	private String id;

	@NotBlank
	private String name;

	public UserGameRoundDTO(GameRound p) {
		this.id = p.getId();
		this.name = p.getUser().getName();
	}

	public static GameRound toEntity(UserGameRoundDTO dto) {
		return GameRound.builder()
				.id(dto.id)
//				.user()
		.build();
	}
}
