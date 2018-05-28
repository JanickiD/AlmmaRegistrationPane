package pl.almma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.almma.model.CompetitionStatus;

public interface StatusRepository extends JpaRepository<CompetitionStatus, Long> {
	
}
