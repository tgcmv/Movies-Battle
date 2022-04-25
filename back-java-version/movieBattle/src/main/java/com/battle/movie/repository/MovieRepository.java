package com.battle.movie.repository;

<<<<<<< HEAD:back-java-version/movieBattle/src/main/java/com/battle/movie/repository/MovieRepository.java
import com.battle.movie.model.Movie;
=======
import com.battle.movie.battle.model.Movie;
>>>>>>> b85b6d164e76689b86d8b9036c7978a6fe634f8d:back-java-version/movieBattle/src/main/java/com/battle/movie/battle/repository/MovieRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>, JpaSpecificationExecutor<Movie> {

}
