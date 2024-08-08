package ru.spring.project2.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Book {

    private int id;

    @NotEmpty(message = "Наименование должно быть заполнено")
    private String name;

    @NotEmpty(message = "Автор должен быть заполнен")
    private String author;

    @Min(value = 1500, message = "Год должен быть больше 1500")
    private int year;

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
