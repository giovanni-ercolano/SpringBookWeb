package it.itsrizzoli.springbookweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Transient
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(length = 101)
    String title;

    @NotNull
    @Size(max = 100)
    @Column(length = 101)
    String author;

    @Size(max = 100)
    @Column(length = 101)
    String description;

    @Column(length = 10)
    @Size(max = 10)
    String publicationDate;

    @NotNull
//    @Min(value = 5)
//    @Max(value = 50)
    Integer price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBook> userBooks = new HashSet<>();

    public Book(){}

    public Book(String title, String author, String description, String publicationDate, Integer price) {
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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
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
