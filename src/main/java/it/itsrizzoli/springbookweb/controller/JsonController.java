package it.itsrizzoli.springbookweb.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.itsrizzoli.springbookweb.model.Book;
import it.itsrizzoli.springbookweb.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class JsonController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/sincronizza")
    public String sincronizza(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.googleapis.com/books/v1/volumes?q=harry+potter";

        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode items = objectMapper.readTree(response).path("items");

            for (JsonNode item : items) {
                // Verifica se l'oggetto item è nullo
                if (item != null) {
                    String title = item.path("volumeInfo").path("title").asText() == null ? "unknown" : item.path("volumeInfo").path("title").asText();
                    JsonNode arrayAuthors = item.path("volumeInfo").path("authors");
                    String authors = arrayAuthors.toString() == null ? "unknown" : arrayAuthors.toString();
                    String publishedDate = item.path("volumeInfo").path("publishedDate").asText() == null ? "unknown" : item.path("volumeInfo").path("publishedDate").asText();
                    String description = item.path("volumeInfo").path("description").asText() == null ? "unknown" : item.path("volumeInfo").path("description").asText();
                    int listPrice = (int) item.path("saleInfo").path("listPrice").path("amount").asDouble() == 0 ? 0 : (int) item.path("saleInfo").path("listPrice").path("amount").asDouble();
                    //se le due variabili sono troppo lunghe le "taglio"
                    //faccio così perché mi da diversi errori di lunghezza rispetto ai dati che prendo
                    if (description.length() > 30) {
                        description = description.substring(0, 29) + "...";
                    }
                    if (title.length() > 30) {
                        title = title.substring(0, 29) + "...";
                    }
                    if (publishedDate.length() > 10) {
                        publishedDate = publishedDate.substring(0, 9);
                    }
                    if (authors.contains("[") && authors.contains("]")) {
                        authors = authors.replace("[", "").replace("]", "").replace("\"", "");
                    }

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(authors);
                    book.setPublicationDate(publishedDate);
                    book.setDescription(description);
                    book.setPrice(listPrice);

                    System.out.println(book);

                    // Salva il libro nel repository
                    bookRepository.save(book);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/home";
    }
}
