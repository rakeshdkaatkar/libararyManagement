package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Card;
import com.StudentLibrary.Studentlibrary.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CardRepository extends MongoRepository<Card,String> {

    @Query("{studentId: ?0}")
    Card getCardByStudentId(@Param("student_id") String student_id);

//    @Modifying
//    @Query("update Card c set c.books=:#{#new_card.books} where c.id=:#{#new_card.id}")
//    int updateCard(@Param("new_card") Card card);
}
