package com.api.poke.usecases;

import com.api.poke.controller.requests.CreateAttributeDTO;
import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.model.Attribute;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.AttributeEntity;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreatePokemon implements PokemonCreator{

    PokemonRepository pokemonRepository;
    PokemonEntityMapper pokemonEntityMapper;

    public Pokemon execute(CreatePokemonRequestDTO requestDTO) {

        CreateAttributeDTO attributeDTO = requestDTO.getAttributes();

        Attribute attributeEntity = Attribute.builder()
                .hp(attributeDTO.getHp())
                .attack(attributeDTO.getAttack())
                .defense(attributeDTO.getDefense())
                .sp_attack(attributeDTO.getSp_attack())
                .sp_defense(attributeDTO.getSp_defense())
                .speed(attributeDTO.getSpeed())
                .build();

        Pokemon pokemon =  Pokemon.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .experience(requestDTO.getExperience())
                .evolutionLevel(requestDTO.getEvolutionLevel())
                .evolves(requestDTO.getEvolves())
                .attributes(attributeEntity)
                .imageBase64(requestDTO.getImageBase64())
               // .imageBase64(base64Image)
                .build();

        PokemonEntity pokemonEntity = pokemonEntityMapper.toEntity(pokemon);

        return pokemonEntityMapper.toModel(pokemonRepository.save(pokemonEntity));
    }
}