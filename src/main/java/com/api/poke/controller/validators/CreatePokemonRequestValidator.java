package com.api.poke.controller.validators;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreatePokemonRequestValidator implements ConstraintValidator<ValidEvolutionLevelConstraint, CreatePokemonRequestDTO> {
   @Override
   public void initialize(ValidEvolutionLevelConstraint constraintAnnotation) {
   }

    @Override
    public boolean isValid(CreatePokemonRequestDTO requestDTO, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (requestDTO.getEvolves() && (requestDTO.getEvolutionLevel() == null || requestDTO.getEvolutionLevel() <= 0)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("If evolves is true, evolutionLevel must be a positive number.")
                    .addPropertyNode("evolutionLevel").addConstraintViolation();
            isValid = false;
        }
        if (!requestDTO.getEvolves() && requestDTO.getEvolutionLevel() != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("If evolves is false, evolutionLevel must be null.")
                    .addPropertyNode("evolutionLevel").addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}


