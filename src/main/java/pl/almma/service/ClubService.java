package pl.almma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.almma.model.Club;
import pl.almma.repository.ClubRepository;

@Service
public class ClubService {
	
	private ClubRepository clubRepository;
	
	@Autowired
	public ClubService(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}

	public List<Club>getAll(){
		return clubRepository.findAll();
	}
	
	public Page<Club>getAll(Pageable pageable){
		return clubRepository.findAll(pageable);
	}
	
	public Club findById(long id) {
		return clubRepository.findByClub_id(id);
	}
	
}
