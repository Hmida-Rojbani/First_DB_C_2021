package de.tekup.db.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.db.entities.Employee;
import de.tekup.db.errors.EmployeeSaveDBException;
import de.tekup.db.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeeCtrl {
	
	private EmployeeService empService;
	
	@PostMapping("/employee/add")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return empService.saveEmp(employee);
	}
	
	@GetMapping("/employee/get/{id}")
	public Employee getByIdEmployee(@PathVariable("id") int id) {
		return empService.getEmpById(id);
	}
	
	@GetMapping("/employee/get")
	public List<Employee> getAllEmployees() {
		return empService.getEmployees();
	}
	
	@PutMapping("/employee/update/{id}")
	public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee newEmployee) {
		return empService.updateEmployee(newEmployee, id);
	}
	
	@DeleteMapping("/employee/delete/{id}")
	public Employee deleteEmployee(@PathVariable("id") int id) {
		return empService.deleteEmployeeById(id);
	}
	
	@ExceptionHandler({NoSuchElementException.class,NumberFormatException.class})
	public ResponseEntity<String> handleNoSuchElementSndNumberFormat(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body("Error in finding Employee : "+e.getMessage());
							 
	}
	
	@ExceptionHandler(EmployeeSaveDBException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(EmployeeSaveDBException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body("Error in saving Employee : "+e.getMessage());
							 
	}

}
