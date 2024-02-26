package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
public class CreatePokemonRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @()
    Integer experience;

    Integer evolutionLevel;
    Boolean evolves;
    MultipartFile image;
}
