package com.api.poke.usecases;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.model.Pokemon;
import org.springframework.web.multipart.MultipartFile;

public interface PokemonCreator {
    Pokemon execute(CreatePokemonRequestDTO requestDTO);
}