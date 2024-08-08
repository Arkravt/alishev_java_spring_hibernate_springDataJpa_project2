package ru.spring.project2.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Person {

    private int id;

    @NotEmpty(message = "ФИО должно быть заполнено")
    private String fullName;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int yearBirth;

    //private List<Book> books;

    public Person() {
    }

    public Person(String fullName, int yearBirth) {
        this.fullName = fullName;
        this.yearBirth = yearBirth;
        //this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

}
