package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Book;
import com.StudentLibrary.Studentlibrary.Model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Transactional
@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    @Query("update Book b set b.card=:#{#book?.card},b.available=:#{#book?.available} where b.id=:#{#book?.id}")
    int updateBook(@RequestParam("book") Book book);


    @Query("{$and :[{genre: ?0},{author.name: ?1}] }")
    List<Book> findBooksByGenre_Author(@Param("genre") Genre genre, @Param("author") String author, @Param("isAvailable") boolean isAvailable);

    @Query("{$and :[{genre: ?0},{isAvailable: ?1}] }")
    List<Book> findBooksByGenre(@Param("genre") Genre genre,@Param("isAvailable") boolean isAvailable);

    @Query("{$and :[{author.name: ?0},{isAvailable: ?1}] }")
    List<Book> findBooksByAuthor(@Param("author") String author,@Param("isAvailable") boolean isAvailable);

    @Query("{available:?0}")
    List<Book> findBooksByAvailability(@Param("isAvailable") boolean isAvailable);

}
