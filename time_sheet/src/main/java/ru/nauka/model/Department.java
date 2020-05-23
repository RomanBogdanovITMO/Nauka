package ru.nauka.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameDepartment;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private Set<Employee> employeeListDepart = new HashSet<>();

    public Department() {
    }

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Set<Employee> getEmployeeListDepart() {
        return employeeListDepart;
    }

    public void setEmployeeListDepart(Set<Employee> employeeListDepart) {
        this.employeeListDepart = employeeListDepart;
    }
    public void addEmployee(Employee employee){
        employee.setDepartment(this);
        this.employeeListDepart.add(employee);
    }

    @Override
    public String toString() {
        return "Department{" + "id: " + id + ", nameDepartment: " + nameDepartment + " }";
    }
}
