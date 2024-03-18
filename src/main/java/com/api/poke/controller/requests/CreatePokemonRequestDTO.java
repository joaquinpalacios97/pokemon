package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class CreatePokemonRequestDTO {
//ESTE VALIDA LOS DATOS QUE SE INGRESAN
    @NotEmpty(message = "Name cannot be empty")
    String name;
//min 0
    //no nullable
    Integer experience;
//nullable
    //min 1
    Integer evolutionLevel;

    Boolean evolves;

    MultipartFile image;
}
