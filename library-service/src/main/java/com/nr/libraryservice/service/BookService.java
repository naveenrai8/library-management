package com.nr.libraryservice.service;

import com.nr.libraryservice.dto.BookRequestDTO;
import com.nr.libraryservice.dto.BookResponseDTO;
import com.nr.libraryservice.entity.Book;
import com.nr.libraryservice.exception.BookNotFoundException;
import com.nr.libraryservice.mapper.BookMapper;
import com.nr.libraryservice.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookResponseDTO getBookById(UUID bookId) throws BookNotFoundException {

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> {
                    log.error("Book with id {} not found", bookId);
                    return new BookNotFoundException(MessageFormat.format("Book with {0} not found", bookId));
                }
        );

        return mapper.toDto(book);
    }

    public BookResponseDTO addNewBook(@Valid BookRequestDTO bookRequestDTO) {
        Book book = mapper.toModel(bookRequestDTO);
        bookRepository.save(book);
        return mapper.toDto(book);
    }
}
