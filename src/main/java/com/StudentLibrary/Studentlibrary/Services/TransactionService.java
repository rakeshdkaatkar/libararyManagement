package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.*;
import com.StudentLibrary.Studentlibrary.Repositories.BookRepository;
import com.StudentLibrary.Studentlibrary.Repositories.CardRepository;
import com.StudentLibrary.Studentlibrary.Repositories.StudentRepository;
import com.StudentLibrary.Studentlibrary.Repositories.TransactionRepository;
import com.StudentLibrary.Studentlibrary.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    @Value("${books.max_allowed}")
    int max_allowed_books;
    @Value("${books.max_allowed_days}")
    int max_days_allowed;
    @Value("${books.fine.per_day}")
    int fine_per_day;


    public String issueBooks(String cardId, String bookId) throws Exception {
        Book book = bookRepository.findById(bookId).get();
        System.out.println(book);

        if (book == null || book.isAvailable() != true) {
            throw new NoDataFoundException("Book is either unavailable or not present!!");
        }
        Card card = cardRepository.findById(cardId).get();
        if (card == null || card.getCardStatus() == CardStatus.DEACTIVATED) {
            throw new NoDataFoundException("Card is invalid!!");
        }
        if (card.getBooks() != null && card.getBooks().size() > max_allowed_books) {
            throw new NoDataFoundException("Book limit reached for this card!!");
        }
        int totalBookAllocated = card.getTotalBook();
        Optional<Student> student = studentRepository.findById(card.getStudentId());
        if (student.isPresent()) {
            if (student.get().getAge() >= 18) {
                if (card.getTotalBook() >= 2) {
                    throw new NoDataFoundException("Adults cant have more than 2 books");
                }
            } else {
                if (student.get().getGender().equals(Gender.FEMALE)) {
                    if (card.getTotalBook() >= 6) {
                        throw new NoDataFoundException("Girls cant have more than 6 books");
                    }
                } else {
                    if (card.getTotalBook() >= 4) {
                        throw new NoDataFoundException("Boys cant have more than 4 books");
                    }
                }
            }
        } else {
            throw new NoDataFoundException("Card is invalid!!");
        }
        System.out.println(card);
        book.setAvailable(false);
        book.setCardId(cardId);
        List<Book> books = card.getBooks();
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        card.setBooks(books);
        bookRepository.save(book);
        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setIssueOperation(true);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionRepository.save(transaction);
        card.setTotalBook(card.getTotalBook() + 1);
        cardRepository.save(card);
        return transaction.getTransactionId();
    }

    public String returnBooks(String cardId, String bookId) throws Exception {
        try {
            List<Transaction> transactions = transactionRepository.findByCard_Book(cardId, bookId, TransactionStatus.SUCCESSFUL, true);
            Transaction last_issue_transaction = transactions.get(transactions.size() - 1);
            //Last transaction that has been done ^^^^
            Date issueDate = last_issue_transaction.getTransactionDate();
            Long issueTime = Math.abs(issueDate.getTime() - System.currentTimeMillis());
            long number_of_days_passed = TimeUnit.MINUTES.convert(issueTime, TimeUnit.MILLISECONDS);
            int fine = 0;
            if (number_of_days_passed > max_days_allowed) {
                fine = (int) Math.abs(number_of_days_passed - max_days_allowed) * fine_per_day;
            }
            Card card = last_issue_transaction.getCard();
            Book book = last_issue_transaction.getBook();
            book.setCardId(null);
            book.setAvailable(true);
            bookRepository.save(book);
            Transaction new_transaction = new Transaction();
            new_transaction.setBook(book);
            new_transaction.setCard(card);
            new_transaction.setFineAmount(fine);
            new_transaction.setIssueOperation(false);
            new_transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            transactionRepository.save(new_transaction);
            Card cardNew = cardRepository.findById(cardId).get();
            cardNew.setTotalBook(cardNew.getTotalBook() - 1);
            cardRepository.save(cardNew);
            return new_transaction.getTransactionId();
        } catch (Exception ex) {
            throw new NoDataFoundException("Unable to return book");
        }

    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }


}
