package ru.nauka.repos;

import org.springframework.data.repository.CrudRepository;
import ru.nauka.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
