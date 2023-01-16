package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Author;
import com.StudentLibrary.Studentlibrary.Repositories.AuthorRepository;
import com.StudentLibrary.Studentlibrary.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;


    public Author createAuthor(Author author) throws Exception {
        List<Author> authorList = authorRepository.getAuthorByEmail(author.getEmail());
        if(authorList !=null && authorList.size()>0){
            throw new NoDataFoundException("Duplicate Author");
        }
        return authorRepository.save(author);

    }
    public Author updateAuthor(Author author) throws Exception {
        List<Author> authorList = authorRepository.getAuthorByEmail(author.getEmail());
        if(authorList ==null || authorList.size()>0){
            Author author1 = authorList.get(0);
            author1.setAge(author.getAge());
            author1.setName(author.getName());
            author1.setCountry(author.getCountry());
            author1.setBooks_written(author.getBooks_written());
            return authorRepository.save(author1);

        }else{
            throw new NoDataFoundException("Duplicate Author");
        }



    }
    public void deleteAuthor(String id ){
        authorRepository.deleteById(id);
    }
    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(String id){
        return authorRepository.findById(id).get();
    }
    public List<Author> getAuthorByEmail(String email){
        return authorRepository.getAuthorByEmail(email);
    }
}
