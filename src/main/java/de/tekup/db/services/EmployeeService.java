package de.tekup.db.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import de.tekup.db.entities.Employee;
import de.tekup.db.entities.Matricule;
import de.tekup.db.errors.EmployeeSaveDBException;
import de.tekup.db.repositories.EmployeeRepository;
import de.tekup.db.repositories.MatriculeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private EmployeeRepository empRepos;
	private MatriculeRepository matRepos;
	
	//save Employee in DB
	public Employee saveEmp(Employee employee) {
		//extract matricule
		Matricule matricule = employee.getMatricule();
		//matRepos.save(matricule);
		try {
			return empRepos.save(employee);
		}catch (DataIntegrityViolationException e) {
			throw new EmployeeSaveDBException(e.getMessage());
		}
		
	}
	//Get employee from DB by Id
	public Employee getEmpById(int id) {
		Optional<Employee> opt = empRepos.findById(id);
		//if(opt.isPresent())
		//	return opt.get();
		//throw new NoSuchElementException("Employee with this id is not found");
		
		return opt.orElseThrow(() -> new NoSuchElementException("Employee with this id is not found"));
		
	}
	// get All employees from DB
	public List<Employee> getEmployees(){
		List<Employee> list =empRepos.findAll();
		return list;
	}
	
	// update employee
	public Employee updateEmployee(Employee newEmployee, int id) {
		Employee emp = getEmpById(id);
		if(newEmployee.getName() != null)
			emp.setName(newEmployee.getName());
		if(newEmployee.getDob() != null)
			emp.setDob(newEmployee.getDob());
		if(newEmployee.getEmail() != null)
			emp.setEmail(newEmployee.getEmail());
		return empRepos.save(emp);
	}
	
	// delete By ID
	public Employee deleteEmployeeById(int id) {
		Employee emp = getEmpById(id);
		empRepos.deleteById(id);
		return emp;
	}

}
