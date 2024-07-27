package com.api.poke.repository.mappers;

import com.api.poke.model.Attribute;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.entities.AttributeEntity;
import com.api.poke.repository.entities.MovementEntity;
import com.api.poke.repository.entities.PokemonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.stream.Collectors;


@Component
public class PokemonEntityMapper {
    public PokemonEntity toEntity(Pokemon pokemon) {

        AttributeEntity attributes = AttributeEntity.builder()
                .id(pokemon.getId())
                .hp(pokemon.getAttributes().getHp())
                .attack(pokemon.getAttributes().getAttack())
                .defense(pokemon.getAttributes().getDefense())
                .sp_attack(pokemon.getAttributes().getSp_attack())
                .sp_defense(pokemon.getAttributes().getSp_defense())
                .speed(pokemon.getAttributes().getSpeed())
                .build();

        PokemonEntity entity =  PokemonEntity.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .type(pokemon.getType())
                .experience(pokemon.getExperience())
                .evolutionLevel(pokemon.getEvolutionLevel())
                .evolves(pokemon.isEvolves())
                .attributes(attributes)
                .imageBase64(pokemon.getImageBase64())
                /*.image(pokemon.getImage())
                .imageBase64(pokemon.getImageBase64())*/
        //entity.setImage(pokemon.getImage());
                .build();
        return entity;
    }


    public Pokemon toModel(PokemonEntity entity) {

        Attribute attribute = Attribute.builder()
                .id(entity.getAttributes().getId())
                .hp(entity.getAttributes().getHp())
                .attack(entity.getAttributes().getAttack())
                .defense(entity.getAttributes().getDefense())
                .sp_attack(entity.getAttributes().getSp_attack())
                .sp_defense(entity.getAttributes().getSp_defense())
                .speed(entity.getAttributes().getSpeed())
                .build();

        Pokemon pokemon =  Pokemon.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .experience(entity.getExperience())
                .evolutionLevel(entity.getEvolutionLevel())
                .evolves(entity.isEvolves())
                .attributes(attribute)
                .imageBase64(entity.getImageBase64())
               /* .image(entity.getImage())
                .imageBase64(entity.getImageBase64())*/
                .build();
        return pokemon;
    }
}
