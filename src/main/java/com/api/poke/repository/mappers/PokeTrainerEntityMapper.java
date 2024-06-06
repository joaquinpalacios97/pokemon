package com.api.poke.repository.mappers;

import com.api.poke.model.Attribute;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.entities.AttributeEntity;
import com.api.poke.repository.entities.PokemonEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class PokeTrainerEntityMapper {

    public PokemonEntity toEntity (Pokemon pokemon){
        AttributeEntity attributes = AttributeEntity.builder()
                .id(pokemon.getId())
                .hp(pokemon.getAttributes().getHp())
                .attack(pokemon.getAttributes().getAttack())
                .defense(pokemon.getAttributes().getDefense())
                .sp_attack(pokemon.getAttributes().getSp_attack())
                .sp_defense(pokemon.getAttributes().getSp_defense())
                .speed(pokemon.getAttributes().getSpeed())
                .build();

        PokemonEntity entity = new PokemonEntity();
        entity.setId(pokemon.getId());
        entity.setName(pokemon.getName());
        entity.setExperience(pokemon.getExperience());
        entity.setEvolutionLevel(pokemon.getEvolutionLevel());
        entity.setEvolves(pokemon.isEvolves());
        entity.setAttributes(attributes);
        //entity.setImage(pokemon.getImage());
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
                .experience(entity.getExperience())
                .evolutionLevel(entity.getEvolutionLevel())
                .evolves(entity.isEvolves())
                .attributes(attribute)
                //  .image(entity.getImage())
                .build();
        return pokemon;
    }

}
