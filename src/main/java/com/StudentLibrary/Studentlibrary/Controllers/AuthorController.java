package com.StudentLibrary.Studentlibrary.Controllers;

import com.StudentLibrary.Studentlibrary.Model.Author;
import com.StudentLibrary.Studentlibrary.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/createAuthor")
    public ResponseEntity createAuthor(@RequestBody Author author) throws Exception {
        Author author1= authorService.createAuthor(author);
        return new ResponseEntity(author1, HttpStatus.CREATED);
    }
    @PutMapping("/updateAuthor")
    public ResponseEntity updateAuthor(@RequestBody Author author) throws Exception {

        return new ResponseEntity(authorService.updateAuthor(author),HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/deleteAuthor")
    public ResponseEntity deleteAuthor(@RequestParam("id") String  id){
        authorService.deleteAuthor(id);
        return new ResponseEntity("Author deleted!!",HttpStatus.ACCEPTED);

    }
    @GetMapping("/all")
    public ResponseEntity getAuthors(){
        List<Author> authorList=authorService.getAllAuthor();
        return new ResponseEntity(authorList,HttpStatus.OK);
    }
    @GetMapping("/getAuthorById")
    public ResponseEntity getAuthors(@RequestParam("id") String  id){
        Author authorList=authorService.getAuthorById(id);
        return new ResponseEntity(authorList,HttpStatus.OK);
    }

}
