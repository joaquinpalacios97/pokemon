package com.api.poke.controller.requests;


import com.api.poke.controller.validators.ValidEvolutionLevelConstraint;
import com.api.poke.model.PokemonType;
import com.api.poke.repository.entities.AttributeEntity;
import com.api.poke.repository.entities.MovementEntity;
import com.api.poke.repository.entities.PokemonEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
@Getter
@ValidEvolutionLevelConstraint
public class CreatePokemonRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotNull(message = "Type cannot be null")
    private PokemonType type;

    @NotNull(message = "Experience cannot be null")
    @Min(value = 0)
    Integer experience;

    Integer evolutionLevel;

    Boolean evolves;

    @NotNull(message = "Attributes cannot be null")
    CreateAttributeDTO atributes;

   /* @NotNull(message = "Image is required")
    MultipartFile image;*/

}