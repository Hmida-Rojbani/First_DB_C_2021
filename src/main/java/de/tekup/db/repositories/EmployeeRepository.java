package de.tekup.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.db.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
