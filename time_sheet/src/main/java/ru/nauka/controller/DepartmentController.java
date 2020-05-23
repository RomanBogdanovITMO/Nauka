package ru.nauka.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nauka.model.Department;
import ru.nauka.model.Employee;
import ru.nauka.repos.DepartmentRepo;
import ru.nauka.repos.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    @GetMapping("/depart/create")
    @PreAuthorize("hasAuthority('USERDEPART')")
    public String createDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "createDepartment";
    }

    @PostMapping("depart/save")
    public String saveDepartment(@ModelAttribute Department department) {
        if (!department.getNameDepartment().equals("")) {
            departmentRepo.save(department);
        } else {
            System.out.println("erro");
        }

        return "redirect:/depart";
    }

    @GetMapping("/depart")
    @PreAuthorize("hasAuthority('USERDEPART')")
    public String getAllDepartment(Model model) {
        List<Department> list = Lists.newArrayList(departmentRepo.findAll());
        model.addAttribute("departments", list);
        return "listDep";
    }

    @GetMapping("/listEmp")
    public String getAllEmployee(@RequestParam String id, Model model) {
        Long idDepart = Long.parseLong(id);
        Department der = departmentRepo.findById(idDepart).get();

        List<Employee> employeeList = Lists.newArrayList(der.getEmployeeListDepart());
        model.addAttribute("employees", employeeList);

        List<Department> list = Lists.newArrayList(departmentRepo.findAll());
        model.addAttribute("departments", list);

        return "listDep";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('USERDEPART')")
    public String orderEdit(@RequestParam String id, Model model) {
        long idDepart = Long.parseLong(id);
        Department department = departmentRepo.findById(idDepart).get();
        model.addAttribute("department", department);

        return "editDepartment";
    }

    //добавляем сотрудников в департамент
    @GetMapping("/addEmployee")
    @PreAuthorize("hasAuthority('USERDEPART')")
    public String addEmployee(Model model) {
        List<Employee> emplList = Lists.newArrayList(employeeRepo.findAll());
        List<Employee> resultEmpl = new ArrayList<>();

        for (Employee emp : emplList) {
            if (emp.getDepartment() == null) {
                resultEmpl.add(emp);
            }
        }

        model.addAttribute("employees", resultEmpl);
        model.addAttribute("departments", departmentRepo.findAll());

        return "addEmployee";
    }

    // выбранный сотрудник добавляется в департамент и возвращаем страницу без добавленного сотрудника
    @GetMapping("/addEmployee/add")
    private String editEmployee(@RequestParam String idEmployee, @RequestParam String idDep) {
        Department department = departmentRepo.findById(Long.parseLong(idDep)).get();
        Employee employee = employeeRepo.findById(Long.parseLong(idEmployee)).get();
        department.addEmployee(employee);
        departmentRepo.save(department);
        return "redirect:/addEmployee";
    }
}
