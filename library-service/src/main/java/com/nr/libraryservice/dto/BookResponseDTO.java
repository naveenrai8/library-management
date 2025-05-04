package com.nr.libraryservice.dto;

import lombok.Builder;

@Builder
public record BookResponseDTO (
        String id,
        String name,
        String author,
        String genre
) {
}
