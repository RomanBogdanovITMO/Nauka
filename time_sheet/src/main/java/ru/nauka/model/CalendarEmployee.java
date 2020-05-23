package ru.nauka.model;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table(name = "calendars")
public class CalendarEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idEmployee;

    @Temporal(TemporalType.DATE)
    private Calendar utilCalendar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private TypeOfMark markCalendar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Calendar getUtilCalendar() {
        return utilCalendar;
    }

    public void setUtilCalendar(Calendar utilCalendar) {
        this.utilCalendar = utilCalendar;
    }

    public TypeOfMark getMarkCalendar() {
        return markCalendar;
    }

    public void setMarkCalendar(TypeOfMark markCalendar) {
        this.markCalendar = markCalendar;
    }

    @Override
    public String toString() {
        return "CalendarEmployee{" +
                "id=" + id +
                ", idEmployee=" + idEmployee +
                ", utilCalendar=" + utilCalendar +
                ", markCalendar=" + markCalendar +
                '}';
    }
}
