package com.StudentLibrary.Studentlibrary.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Author {
    @Id
    private String  id;

    private String name;

    private String email;

    private int age;
    private String country;

    @JsonIgnore
    private List<Book> books_written;

    public Author(){}

    public Author(String name, String email, int age, String country) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
    }

}
