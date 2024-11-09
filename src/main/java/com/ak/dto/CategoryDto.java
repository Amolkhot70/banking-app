package com.ak.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Category DTO (Data transfer Object) to transfer data between client and server"
)
public record CategoryDto(
        @Schema(description = "Category Id")
        Long id,
        @Schema(description = "Category name")
        String name) {
}
