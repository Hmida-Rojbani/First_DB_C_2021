package de.tekup.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.db.entities.Matricule;
import de.tekup.db.entities.MatriculeCreator;

public interface MatriculeRepository extends JpaRepository<Matricule, Integer>{

	List<Matricule> findAllByCreator(MatriculeCreator creator);

}
