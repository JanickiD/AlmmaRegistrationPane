package pl.almma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.almma.model.Competition;
import pl.almma.model.CompetitionStatus;
import pl.almma.repository.CompetitionRepository;
import pl.almma.repository.StatusRepository;

@Service
public class CompetitionService {
	
	
	private CompetitionRepository competitionRepository;
	private StatusRepository statusRepository;

	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository, StatusRepository statusRepository) {
		super();
		this.competitionRepository = competitionRepository;
		this.statusRepository = statusRepository;
	}
	
	
	public List<Competition> getAllCompetitions(){
		return competitionRepository.findAll();
		
	}
	
	public Competition addCompetition(Competition competition) {
		
		CompetitionStatus status = statusRepository.findOne((long) 1);
		competition.setStatus(status);
		
		return competitionRepository.save(competition);
	}

}
