package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Card;
import com.StudentLibrary.Studentlibrary.Model.Student;
import com.StudentLibrary.Studentlibrary.Repositories.CardRepository;
import com.StudentLibrary.Studentlibrary.Repositories.StudentRepository;
import com.StudentLibrary.Studentlibrary.exception.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    Logger logger= LoggerFactory.getLogger(StudentService.class);


    @Autowired
    StudentRepository studentRepository ;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    public Student getStudentByUsername(String email){
        return studentRepository.findByEmailId(email);
    }
    public Student getById(String id ){
        return studentRepository.findById(id).get();
    }

    public Student createStudent (Student student){

        Student student1 = getStudentByUsername(student.getEmailId());
        if(student1!=null){
            throw new NoDataFoundException("User already exists");
        }
        Card card=cardService.createCard(student);
        student.setCard(card);
        student.setCreatedOn(new Date());
        student.setUpdatedOn(new Date());
        logger.info("The card for the student{} is created with the card details{}",student,card);
        Student student2 =  studentRepository.save(student);
        card.setStudentId(student2.getId());
        cardRepository.save(card);
        return student2;
    }
    public Student  updateStudent(Student student){
        Student student1 = getStudentByUsername(student.getEmailId());
        if(student1==null){
            throw new NoDataFoundException("User does not exists");
        }
        student1.setCard(student.getCard());
        student1.setAge(student.getAge());
        student1.setGender(student.getGender());
        student1.setName(student.getName());
        student1.setCountry(student.getCountry());
        student1.setUpdatedOn(new Date());
        return studentRepository.save(student1);


    }


    public void deleteStudent(String  id ){

        cardService.deactivate(id);
        studentRepository.deleteById(id);

    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
}
