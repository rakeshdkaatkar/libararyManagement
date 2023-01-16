package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface StudentRepository extends MongoRepository<Student,String> {

    //JPQL-->Java persistence Query language-->(Objects and Attributes)
    //Native sql query-->(columns and tables)







    //find student by given name
    //Terminal---> Select * from student where email =email
    //1. JPQL--Dealing with java objects
    //Student(exact class name) class has the variable name as emailId so b.emailId
    // :mail has to passed in the argument of the function exact variable name as in the args

    @Query("{emailId:?0}")
    Student  findByEmailId(String mail);




}
