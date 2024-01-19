package it.itsrizzoli.springbookweb.model;

import it.itsrizzoli.springbookweb.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    //@Transient
    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    String title;

    @NotNull
    @Size(min=2, max=50)
    String author;

    @Size(min=0, max=30)
    String description;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date publicationDate;

    @NotNull
    @Min(value = 5)
    @Max(value = 50)
    Integer price;

    @OneToMany(mappedBy = "book")
    private Set<UserBook> userBooks = new HashSet<>();

    public Book(){}

    public Book(String title, String author, String description, Date publicationDate, Integer price) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publicationDate = publicationDate;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                ", price=" + price +
                ", userBooks=" + userBooks +
                '}';
    }
}
