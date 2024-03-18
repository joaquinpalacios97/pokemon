package com.api.poke.controller.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Experience cannot be null")
    Integer experience;
    //nullable
    //min 1
    @NotNull(message = "Evolution level cannot be null")
    @Min(value = 0, message = "Evolution level must be at least 0")
    Integer evolutionLevel;

    Boolean evolves;

   /* @NotNull(message = "Image is required")
    MultipartFile image;*/
}
