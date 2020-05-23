package ru.nauka.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nauka.model.CalendarEmployee;
import ru.nauka.model.Type;
import ru.nauka.model.TypeOfMark;
import ru.nauka.repos.CalendarRepo;
import ru.nauka.repos.DepartmentRepo;
import ru.nauka.repos.EmployeeRepo;

import java.util.*;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarRepo calendarRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("calendars", calendarRepo.findAll());
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("employees", employeeRepo.findAll());
        return "calendar";
    }

    @GetMapping("/record")
    public String calendarCreate(Model model) {
        List<Type> list = Arrays.asList(Type.values());
        model.addAttribute("marks", list);
        model.addAttribute("employees", employeeRepo.findAll());
        return "recordCalendar";
    }

    @PostMapping("/save")
    public String recordSave(@RequestParam Long id, @RequestParam String data, @RequestParam String mark) {
        TypeOfMark mark1 = new TypeOfMark();

        if (!mark.isEmpty()) mark1.setTypeMark(Type.valueOf(mark));

        String[] mass = data.split("\\.");
        int first = Integer.parseInt(mass[2]);
        int second = Integer.parseInt(mass[1]) - 1;
        int last = Integer.parseInt(mass[0]);

        CalendarEmployee calendarEmployee = new CalendarEmployee();
        calendarEmployee.setIdEmployee(id);
        calendarEmployee.setMarkCalendar(mark1);
        calendarEmployee.setUtilCalendar(new GregorianCalendar(first, second, last));

        calendarRepo.save(calendarEmployee);

        return "redirect:/calendar/list";
    }

    @PostMapping("/saveEdit")
    private String saveEdit(@ModelAttribute CalendarEmployee calendarEmployee) {
        calendarRepo.save(calendarEmployee);
        return "redirect:/calendar/list";
    }

    @GetMapping("/edit")
    public String editCalendar(@RequestParam Long id, Model model) {
        model.addAttribute("calendar", calendarRepo.findById(id).get());

        return "editCalendar";
    }

    //возвращает календарь с январским месяцом
    @GetMapping("/january")
    public String january(Model model) {

        model.addAttribute("calendars", equalsMonth(0));

        return "calendar";
    }

    //возвращает календарь с февральским месяцом
    @GetMapping("/february")
    public String february(Model model) {

        model.addAttribute("calendars", equalsMonth(1));

        return "calendar";
    }

    //сравненивает основоные записи в календаре с выбранным месяцем
    private List<CalendarEmployee> equalsMonth(int value) {
        List<CalendarEmployee> lists = Lists.newArrayList(calendarRepo.findAll());
        List<CalendarEmployee> result = new ArrayList<>();

        Calendar calendarResult = new GregorianCalendar();
        calendarResult.set(Calendar.MONTH, value);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(calendarResult.getTime());

        for (CalendarEmployee list : lists) {
            if (calendar1.get(Calendar.MONTH) == list.getUtilCalendar().get(Calendar.MONTH)) {
                result.add(list);
            }
        }
        return result;
    }
}
