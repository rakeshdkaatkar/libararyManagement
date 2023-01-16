package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Transaction;
import com.StudentLibrary.Studentlibrary.Model.TransactionStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {


    @Query(value = "{'card.id': ?0},{'book.id': ?1},{transactionStatus: ?2},{isIssue:?3}")
    public List<Transaction> findByCard_Book(@Param("card_id") String card_id,
                                            @Param("book_id") String book_id,
                                            @Param("status") TransactionStatus status,
                                            @Param("isIssue") boolean isIssue);



}
