package kz.alimzhan.lib.Repository;

import kz.alimzhan.lib.Entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {


    @Query(value = "select * from book b join library l on l.id = b.library_id where  lower(b.name) like concat('%',lower(:name),'%') ", nativeQuery = true)
    List<Book> findBooksByName(@Param("name") String name);

    @Query(value = "select * from book b join library l on l.id = b.library_id where lower(b.author) like concat('%',lower(:name),'%')", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("name") String name);

    @Query(value = "select * from book b join library l on b.library_id = l.id  where b.year_of_issue = :year", nativeQuery = true)
    List<Book> findBooksByYearOfIssue(@Param("year") Integer year);

    @Modifying
    @Transactional
    @Query(value = "update book set status = 'BOOKED', booked_by=:contacts where id = :id", nativeQuery = true)
    void reserveBook(Long id, String contacts);


}
