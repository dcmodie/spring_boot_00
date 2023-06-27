package com.dcm.payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;
	private final EmployeeModelAssembler assembler;

	EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler assembler) {
		this.employeeRepository = employeeRepository;
		this.assembler = assembler;

	}

	@GetMapping
	CollectionModel<EntityModel<Employee>> all() {

		List<EntityModel<Employee>> employees = employeeRepository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());
		
		

		return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}

	@PostMapping("/add")
	Employee add(@RequestBody Employee newEmployee) {
		return employeeRepository.save(newEmployee);
	}

	@GetMapping("/{id}")
	Employee one(@PathVariable Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@DeleteMapping("/delete/{id}")
	void delete(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	Employee update(@RequestBody Employee newEmployee, @PathVariable Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			employee.get().setName(newEmployee.getName());
			employee.get().setRole(newEmployee.getRole());
			employeeRepository.save(employee.get());
			return employee.get();
		} else {

			Employee addedEmployee = new Employee(newEmployee.getName(), newEmployee.getRole());
			employeeRepository.save(addedEmployee);
			return addedEmployee;
		}
		// .findFirst((Employee employee)->{
		// employee.setName(newEmployee.getName());
		// employee.setRole(newEmployee.getRole());
		// employeeRepository.save(newEmployee);

		// })

	};

}

// crud
// get one
// update one
// delete one

// curl -d '{"name=Thomas&role=Cleaner"}' -X POST
// 'Content-type:application/json' http://localhost:8080/employees/add
// curl -d "name=Ja&role=aaaa" -X PUT http://localhost:8080/employees/2
// curl http://localhost:8080/employees/1
