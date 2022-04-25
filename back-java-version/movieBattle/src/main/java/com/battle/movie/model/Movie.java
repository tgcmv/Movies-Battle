package com.battle.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {


    @Id
    @GeneratedValue(generator="increment")
    @JsonIgnore
    private Integer id;

    @Column(name = "imdbID", unique = true, nullable = false)
    @JsonProperty("imdbID")
    private String imdbID;

    @Column
    @JsonProperty("Title")
    private String title;

    @Column
    @JsonProperty("Year")
    private String year;

    @Column
    @JsonProperty("Poster")
    private String poster;

    @Transient
    @JsonIgnore
    private BigDecimal imdbRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(imdbID, movie.imdbID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbID);
    }
}
