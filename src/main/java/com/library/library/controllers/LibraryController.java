package com.library.library.controllers;

import com.library.library.models.Book;
import com.library.library.models.Shelf;
import com.library.library.repository.BookRepo;
import com.library.library.repository.ShelfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    private ShelfRepo shelf_repository;

    @Autowired
    private BookRepo book_repository;

    @GetMapping("/library")
    public String library(Model model) {
        Iterable<Shelf> shelfes = shelf_repository.findAll();
        model.addAttribute("shelfes",  shelfes);
        return "library";
    }

    @GetMapping("/admin/shelfes/")
    public String admin_shelfes(Model model) {
        Iterable<Shelf> shelfes = shelf_repository.findAll();
        model.addAttribute("shelfes",  shelfes);
        return "admin_shelfes";
    }

    @GetMapping("/library/shelfes/{id}/view")
    public String library_shelfes_view(@PathVariable(value = "id") long id, Model model) {
        Shelf shelf = shelf_repository.findById(id).orElseThrow();
        Iterable <Book> books = book_repository.findAll();
        List<Book> current_books = new ArrayList<>();
        for (Book book:books){
            if (book.getShelf() != null) {
                if (book.getShelf().getId() == id){
                    current_books.add(book);
                }
            }
        }
        model.addAttribute("books",  current_books);
        model.addAttribute("shelf",  id);
        return "library_shelf_view";
    }

}
