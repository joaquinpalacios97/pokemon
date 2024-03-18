package com.api.poke.controller.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdatePokemonRequestDTO {

     @NotEmpty(message = "Name cannot be empty")
     String name;

     @NotNull(message = "Experience cannot be null")
     int experience;

     @NotNull(message = "Evolution level cannot be null")
     @Min(value = 0, message = "Evolution level must be at least 0")
     int evolutionLevel;

     boolean evolves;

     /*@NotNull(message = "Image is required")
     byte[] image;*/

}
