package pl.almma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.almma.model.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}

