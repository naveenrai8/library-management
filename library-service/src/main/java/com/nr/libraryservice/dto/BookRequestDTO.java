package com.nr.libraryservice.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRequestDTO(
        @NotBlank(message = "Book name is mandatory")
        String name,

        @NotBlank(message = "Book author is mandatory")
        String author,

        @NotBlank(message = "Book genre is mandatory")
        String genre
) {
}
