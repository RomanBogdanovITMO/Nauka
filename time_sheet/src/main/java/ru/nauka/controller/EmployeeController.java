package ru.nauka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nauka.model.Employee;
import ru.nauka.repos.EmployeeRepo;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/list")
    public String getAllEmployee(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
        return "listEmployee";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        if (!employee.getName().equals("")) {
            employeeRepo.save(employee);
        } else {
            System.out.println("erro");
        }

        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    private String editEmployee(@RequestParam String id, Model model) {
        Employee employee = employeeRepo.findById(Long.parseLong(id)).get();
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @GetMapping("/delete")
    private String delete(@RequestParam String id) {
        employeeRepo.deleteById(Long.parseLong(id));
        return "redirect:/employee/list";
    }

}
