package com.StudentLibrary.Studentlibrary.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Setter
@Getter
@Document
public class Student {
    @Id
    private String id;

    private String emailId;

    private Gender gender;


    private String name;

    //Future scope adult books filter
    private int age;
    private String country;


    private Card card;


    private Date createdOn;

    private Date updatedOn;

    public Student(String emailId, String name, int age, String country) {
        this.emailId = emailId;
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public Student(){

    }


}
