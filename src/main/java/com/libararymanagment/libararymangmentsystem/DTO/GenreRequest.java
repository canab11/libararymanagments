package com.libararymanagment.libararymangmentsystem.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreRequest {

    @NotBlank(message = "Genre name is required")
    private String genreName;

}

