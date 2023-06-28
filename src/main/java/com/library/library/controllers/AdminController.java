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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookRepo book_repository;

    @Autowired
    private ShelfRepo shelf_repository;

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/admin/books")
    public String admin_books(Model model) {
        Iterable<Book> books = book_repository.findAll();
        model.addAttribute("books",  books);
        return "admin_books";
    }

    @GetMapping("/admin/shelfes")
    public String admin_shelfes(Model model) {
        Iterable<Shelf> shelfes = shelf_repository.findAll();
        model.addAttribute("shelfes",  shelfes);
        return "admin_shelfes";
    }

    @GetMapping("/admin/shelfes/add")
    public String admin_shelfes_add(Model model) {
        return "admin_add_shelf";
    }

    @PostMapping("/admin/shelfes/add")
    public String admin_shelf_add_post(@RequestParam String name) {
        Shelf shelf = new Shelf(name);
        shelf_repository.save(shelf);
        return "redirect:/admin/shelfes";
    }

    @PostMapping("/admin/shelfes/{id}/remove")
    public String admin_shelfes_add_post(@PathVariable(value = "id") long id, Model model) {
        Shelf shelf = shelf_repository.findById(id).orElseThrow();
        shelf_repository.delete(shelf);
        return "redirect:/admin/shelfes";
    }

    @GetMapping("/admin/shelfes/{id}/view")
    public String admin_shelfes_view_post(@PathVariable(value = "id") long id, Model model) {
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
        return "admin_shelf_view";
    }

    @GetMapping("/admin/shelfes/{id}/add_book")
    public String admin_shelfes_add_book(@PathVariable(value = "id") long id, Model model) {
        Iterable <Book> books = book_repository.findAll();
        model.addAttribute("books",  books);
        model.addAttribute("shelf",  id);
        return "admin_shelf_add_book";
    }

    @PostMapping("/admin/shelfes/{id}/add_book")
    public String admin_shelfes_add_book_post(@PathVariable(value = "id") long id,
                                              @RequestParam Long book_id,
                                              Model model) {
        Book book = book_repository.findById(book_id).orElseThrow();
        Shelf shelf = shelf_repository.findById(id).orElseThrow();
        book.setShelf(shelf);
        book_repository.save(book);
        return "redirect:/admin/shelfes";
    }

    @PostMapping("/admin/shelfes/{id}/remove_book")
    public String admin_shelfes_remove_book_post(@PathVariable(value = "id") long book_id,
                                              Model model) {
        Book book = book_repository.findById(book_id).orElseThrow();
        book.setShelf(null);
        book_repository.save(book);
        return "redirect:/admin/shelfes";
    }

    @GetMapping("/admin/books/add")
    public String admin_books_add(Model model) {
        return "admin_add_book";
    }

    @PostMapping("/admin/books/add")
    public String admin_books_add_post(@RequestParam String author_name,
                                       @RequestParam String book_name,
                                       @RequestParam String publisher,
                                       @RequestParam Integer year,
                                       @RequestParam String description) {
        Book book = new Book(author_name, book_name, publisher, year, description);
        book_repository.save(book);
        return "redirect:/admin/books";
    }

    @PostMapping("/admin/books/{id}/remove")
    public String admin_books_add_post(@PathVariable(value = "id") long id, Model model) {
        Book book = book_repository.findById(id).orElseThrow();
        book_repository.delete(book);
        return "redirect:/admin/books";
    }

}
