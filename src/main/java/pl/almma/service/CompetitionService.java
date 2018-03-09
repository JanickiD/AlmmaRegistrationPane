package pl.almma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.almma.model.Competition;
import pl.almma.repository.CompetitionRepository;

@Service
public class CompetitionService {
	
	
	private CompetitionRepository competitionRepository;

	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		super();
		this.competitionRepository = competitionRepository;
	}
	
	
	public List<Competition> getAllCompetitions(){
		
		return competitionRepository.findAll();
		
	}

}
