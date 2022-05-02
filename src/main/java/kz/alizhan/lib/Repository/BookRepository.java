package kz.alizhan.lib.Repository;

import kz.alizhan.lib.Entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "select * from book b join location l on b.location_id=l.id", nativeQuery = true)
    List<Book> findAll();

    @Query(value = "select * from book b join location l on b.location_id=l.id where  lower(name) like concat('%',lower(:name),'%') ", nativeQuery = true)
    List<Book> findBooksByName(@Param("name") String name);

    @Query(value = "select * from book b join location l on b.location_id=l.id where lower(author) like concat('%',lower(:name),'%')", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("name") String name);

    @Query(value = "select * from book b join location l on b.location_id=l.id where b.year_of_issue = :year", nativeQuery = true)
    List<Book> findBooksByYearOfIssue(@Param("year") Integer year);

    @Query(value = "insert into book ( name, year_of_issue, author, location_id) values (:name,:year,:author,:locationId)", nativeQuery = true)
    void addBook(Integer year, String name, String author, Long locationId);

}
