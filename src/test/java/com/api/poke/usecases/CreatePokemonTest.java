package com.api.poke.usecases;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import com.api.poke.usecases.CreatePokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CreatePokemonTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokemonEntityMapper pokemonEntityMapper;

    @InjectMocks
    private CreatePokemon createPokemon;


    @Test
    public void testSavePokemon_ValidRequestDTO_SavesPokemon() {
        // Arrange
        CreatePokemonRequestDTO requestDTO = CreatePokemonRequestDTO.builder()
                .name("Pikachu")
                .experience(100)
                .evolutionLevel(3)
                .evolves(true)
                .build();

        Pokemon pokemon =  Pokemon.builder()
                .name(requestDTO.getName())
                .experience(requestDTO.getExperience())
                .evolutionLevel(requestDTO.getEvolutionLevel())
                .evolves(requestDTO.getEvolves())
                .build();

        PokemonEntity pokemonEntity = new PokemonEntity();

        when(pokemonEntityMapper.toEntity(any(Pokemon.class))).thenReturn(pokemonEntity);
        when(pokemonRepository.save(any(PokemonEntity.class))).thenReturn(pokemonEntity);
        when(pokemonEntityMapper.toModel(any(PokemonEntity.class))).thenReturn(pokemon);


        Pokemon savedPokemon = createPokemon.execute(requestDTO);


        assertNotNull(savedPokemon);
        assertEquals(pokemon.getName(), savedPokemon.getName());
        assertEquals(pokemon.getExperience(), savedPokemon.getExperience());
        assertEquals(pokemon.getEvolutionLevel(), savedPokemon.getEvolutionLevel());
        assertEquals(pokemon.isEvolves(), savedPokemon.isEvolves());
    }
}
