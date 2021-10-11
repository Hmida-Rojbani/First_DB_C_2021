package de.tekup.db.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.tekup.db.entities.Employee;
import de.tekup.db.entities.Matricule;
import de.tekup.db.entities.MatriculeCreator;
import de.tekup.db.repositories.MatriculeCreatorRepository;
import de.tekup.db.repositories.MatriculeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MatriculeCreatorService {
	
	private MatriculeRepository matRepos;
	private MatriculeCreatorRepository creatorRepository;
	
	public MatriculeCreator addCreator(MatriculeCreator creator) {
		return creatorRepository.save(creator);
	}
	
	public MatriculeCreator getCreatorById(int id) {
		Optional<MatriculeCreator> opt = creatorRepository.findById(id);
		return opt.orElseThrow(() -> new NoSuchElementException("MatriculeCreator with this id is not found"));
		
	}
	
	public Matricule addMatricule(int idCreator, Matricule matricule) {
		MatriculeCreator creator = getCreatorById(idCreator);
		matricule.setCreator(creator);
		return matRepos.save(matricule);
	}
	
	public List<Matricule> getMatriculesByCreatorId(int idCreator){
		MatriculeCreator creator = getCreatorById(idCreator);
		return matRepos.findAllByCreator(creator);
	}

}
