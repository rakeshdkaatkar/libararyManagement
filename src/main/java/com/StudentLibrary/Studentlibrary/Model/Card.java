package com.StudentLibrary.Studentlibrary.Model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Document
public class Card {



    @Id
    private String  id;


    private String studentId;

    private int totalBook;

    @CreatedDate
    private Date createdOn;

    @LastModifiedDate
    private Date updatedOn;

    private CardStatus cardStatus;

    private List<Transaction> transactions;

    private List<Book> books;



    public Card(){
        this.cardStatus=CardStatus.ACTIVATED;
    }


}
