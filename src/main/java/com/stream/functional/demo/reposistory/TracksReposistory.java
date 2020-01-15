package com.stream.functional.demo.reposistory;

import com.stream.functional.demo.domain.Tracks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TracksReposistory extends JpaRepository<Tracks, Integer> {

    List<Tracks> findAll();

}
