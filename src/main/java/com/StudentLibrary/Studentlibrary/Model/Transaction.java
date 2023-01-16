package com.StudentLibrary.Studentlibrary.Model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Document
public class Transaction {
    @Version
    private Long version;
    @Id
    private String  id;

    private String transactionId= UUID.randomUUID().toString();

    private Card card;

    private int fineAmount;

    private Book book;

    private Boolean isIssueOperation;

    private TransactionStatus transactionStatus;

    @CreatedDate
    private Date transactionDate;

    public Boolean getIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(Boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

}
