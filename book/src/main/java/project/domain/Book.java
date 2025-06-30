package project.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import project.BookApplication;
import project.domain.BookDeleted;
import project.domain.BookPublished;
import project.domain.BookViewed;
import project.domain.EditedBookInfo;

@Entity
@Table(name = "Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    private Long authorId;

    private String bookTitle;

    private String category;

    private Date createDate;

    private Date modifyDate;

    private String bookSummary;

    private String bookCoverImage;

    private String bookContent;

    private Integer viewCount;

    private Integer likeCount;

    @PostPersist
    public void onPostPersist() {
        BookViewed bookViewed = new BookViewed(this);
        bookViewed.publishAfterCommit();

        EditedBookInfo editedBookInfo = new EditedBookInfo(this);
        editedBookInfo.publishAfterCommit();

        BookPublished bookPublished = new BookPublished(this);
        bookPublished.publishAfterCommit();

        BookDeleted bookDeleted = new BookDeleted(this);
        bookDeleted.publishAfterCommit();
    }

    public static BookRepository repository() {
        BookRepository bookRepository = BookApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    public void ViewBook() {
        //
    }
}
//>>> DDD / Aggregate Root
