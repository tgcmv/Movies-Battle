//package com.battle.movie.battle.controller;
//
//import com.battle.movie.battle.model.GameRound;
//import com.battle.movie.battle.model.dto.UserGameRoundDTO;
//import com.battle.movie.battle.service._IGameRoundService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.persistence.EntityNotFoundException;
//import javax.validation.Valid;
//import java.math.BigDecimal;
//import java.util.Collection;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequestMapping("/gameround")
//@Tag(name = "GameRound API")
//public class GameRoundController {
//
//	private _IGameRoundService service;
//
//    public GameRoundController(_IGameRoundService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public UserGameRoundDTO create(@RequestBody @Valid final UserGameRoundDTO GameRoundDTO) {
//		GameRound GameRound = service.insert(GameRoundDTO.toEntity(GameRoundDTO));
//		return new UserGameRoundDTO(GameRound);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<UserGameRoundDTO> update(@PathVariable(required = true) final String id,
//			@RequestBody @Valid final UserGameRoundDTO GameRoundDTO) {
//		GameRound GameRound;
//		try {
//			GameRound = service.update(id, UserGameRoundDTO.toEntity(GameRoundDTO));
//			return ResponseEntity.ok(new UserGameRoundDTO(GameRound));
//		} catch (EntityNotFoundException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@GetMapping("/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public ResponseEntity<UserGameRoundDTO> get(@PathVariable(required = true) final String id) {
//		Optional<GameRound> GameRound = service.findById(id);
//		return GameRound.isPresent() ? ResponseEntity.ok(new UserGameRoundDTO(GameRound.get())) :  ResponseEntity.notFound().build();
//	}
//
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public Collection<UserGameRoundDTO> get() {
//		return service.findAll().stream().map(UserGameRoundDTO::new).collect(Collectors.toList());
//	}
//
//	@GetMapping("/search")
//	@ResponseStatus(HttpStatus.OK)
//	public Collection<UserGameRoundDTO> search(@RequestParam(value="q", required = false) Optional<String> description,
//			@RequestParam(value="min_price", required = false) Optional<BigDecimal> minPrice,
//			@RequestParam(value="max_price", required = false) Optional<BigDecimal> maxPrice) {
//		return service.filter(description, minPrice, maxPrice).stream().map(UserGameRoundDTO::new).collect(Collectors.toList());
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable(required = true) final String id) {
//		try {
//			service.delete(id);
//			return ResponseEntity.ok().build();
//		} catch (EntityNotFoundException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
//}
