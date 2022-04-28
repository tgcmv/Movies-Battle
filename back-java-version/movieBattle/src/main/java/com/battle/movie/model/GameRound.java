package com.battle.movie.model;

import com.battle.movie.core.GameStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GameRound implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column
    private Integer point;

    @Column
    private Integer wrong;

    @Column
    private GameStatus status;

    @OneToMany(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "pair_movies_ID")
    private List<PairMovies> pairMovies;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void addPoint(){
        point++;
    }
    public void addWrong(){
        wrong++;
    }

}
