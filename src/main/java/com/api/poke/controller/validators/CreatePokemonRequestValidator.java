package com.api.poke.controller.validators;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreatePokemonRequestValidator implements ConstraintValidator<ValidEvolutionLevelConstraint, CreatePokemonRequestDTO> {

    @Override
    public void initialize(ValidEvolutionLevelConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreatePokemonRequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (requestDTO.getEvolves() && (requestDTO.getEvolutionLevel() == null || requestDTO.getEvolutionLevel() <= 0)) {
            return false;
        }

        if (!requestDTO.getEvolves() && requestDTO.getEvolutionLevel() != null) {
            return false;
        }

        return true;
    }
}


