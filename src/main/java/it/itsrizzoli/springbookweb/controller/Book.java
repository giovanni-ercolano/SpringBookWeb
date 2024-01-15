package it.itsrizzoli.springbookweb.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    @NotNull
    @Size(min=2, max=30)
    String title;

    @NotNull
    @Size(min=2, max=50)
    String author;

    @Size(min=0, max=30)
    String description;

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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
