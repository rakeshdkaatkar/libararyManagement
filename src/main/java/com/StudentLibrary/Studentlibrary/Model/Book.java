package com.StudentLibrary.Studentlibrary.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Setter
@Getter
public class Book {
    @Id
    private String  id;

    private String name;

    private Genre genre;

    Author author;

    String cardId;

    private boolean available;

    private List<Transaction> transactions;

    public Book(){

    }

    public Book( String name, Genre genre,Author author) {
        this.name = name;
        this.genre = genre;
        this.author=author;
        this.available =true;
    }

}
