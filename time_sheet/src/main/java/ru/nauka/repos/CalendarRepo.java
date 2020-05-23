package ru.nauka.repos;

import org.springframework.data.repository.CrudRepository;
import ru.nauka.model.CalendarEmployee;

public interface CalendarRepo extends CrudRepository<CalendarEmployee,Long> {

}
