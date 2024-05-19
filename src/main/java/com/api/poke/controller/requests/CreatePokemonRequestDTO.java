package com.api.poke.controller.requests;


import com.api.poke.controller.validators.ValidEvolutionLevelConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
@Getter
@ValidEvolutionLevelConstraint
public class CreatePokemonRequestDTO {

    // TODO : evolutionLevel (si evolves = false , evolutionLevel = null)

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotNull(message = "Experience cannot be null")
    @Min(value = 0)
    Integer experience;

    Integer evolutionLevel;

    Boolean evolves;

   /* @NotNull(message = "Image is required")
    MultipartFile image;*/

}