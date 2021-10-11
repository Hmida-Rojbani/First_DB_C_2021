package de.tekup.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.db.entities.Matricule;

public interface MatriculeRepository extends JpaRepository<Matricule, Integer>{

}
