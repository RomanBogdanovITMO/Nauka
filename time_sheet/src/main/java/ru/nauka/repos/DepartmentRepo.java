package ru.nauka.repos;

import org.springframework.data.repository.CrudRepository;
import ru.nauka.model.Department;

public interface DepartmentRepo extends CrudRepository<Department,Long> {
}

