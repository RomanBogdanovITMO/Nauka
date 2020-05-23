package ru.nauka.model;

import javax.persistence.*;


@Entity
@Table(name = "typeOfMarks")
public class TypeOfMark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type typeMark;

    @OneToOne(mappedBy = "markCalendar")
    private CalendarEmployee employee;

    public TypeOfMark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getTypeMark() {
        return typeMark;
    }

    public void setTypeMark(Type typeMark) {
        this.typeMark = typeMark;
    }

    public CalendarEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(CalendarEmployee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "TypeOfMark{" +
                "id=" + id +
                ", typeMark=" + typeMark +
                '}';
    }
}
