package com.example.demo.book.dto;

import com.example.demo.common.validation.OnCreate;
import com.example.demo.common.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record BookDto(
        @Null(groups = OnCreate.class) @NotNull(groups = OnUpdate.class) Long id,
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank String description) {
    public BookDto withId(Long id) {
        return new BookDto(id, title, author, description);
    }
}
