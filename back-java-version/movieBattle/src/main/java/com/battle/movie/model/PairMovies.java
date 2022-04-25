package com.battle.movie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pair_movies")
@Data
@Builder
@AllArgsConstructor
public class PairMovies {

    public PairMovies() {
        answered = false;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="increment")
    private Long id;

    @Column
    private String movieA;

    @Column
    private String movieB;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private GameRound gameRound;

    @Column
    @NonNull
    private boolean answered;
}