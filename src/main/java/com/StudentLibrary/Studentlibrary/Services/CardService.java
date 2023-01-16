package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Card;
import com.StudentLibrary.Studentlibrary.Model.CardStatus;
import com.StudentLibrary.Studentlibrary.Model.Student;
import com.StudentLibrary.Studentlibrary.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {


    @Autowired
    CardRepository cardRepository;


    public Card createCard(Student student){
        Card card =new Card();
        student.setCard(card);
        //card.setStudent(student);
        cardRepository.save(card);
        return card;
    }
    public void deactivate(String student_id){
        Card card = cardRepository.getCardByStudentId(student_id);
        card.setCardStatus(CardStatus.DEACTIVATED);
        cardRepository.save(card);
    }
}
