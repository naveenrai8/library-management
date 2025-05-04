package com.nr.libraryservice.controller;

import com.nr.libraryservice.dto.BookRequestDTO;
import com.nr.libraryservice.dto.BookResponseDTO;
import com.nr.libraryservice.exception.BookNotFoundException;
import com.nr.libraryservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<String> getBooks() {
        return ResponseEntity.ok("Fetching all the books");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable("id") UUID bookId) throws BookNotFoundException {
        BookResponseDTO bookResponseDTO = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addNewBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookResponseDTO = bookService.addNewBook(bookRequestDTO);
        return ResponseEntity.ok(bookResponseDTO);
    }


}
