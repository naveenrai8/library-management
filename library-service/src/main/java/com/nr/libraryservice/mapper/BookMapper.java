package com.nr.libraryservice.mapper;

import com.nr.libraryservice.dto.BookRequestDTO;
import com.nr.libraryservice.dto.BookResponseDTO;
import com.nr.libraryservice.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponseDTO toDto(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId().toString())
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .build();
    }

    public Book toModel(BookRequestDTO bookRequestDTO) {
        return Book.builder()
                .name(bookRequestDTO.name())
                .author(bookRequestDTO.author())
                .genre(bookRequestDTO.genre())
                .build();
    }
}
