import { Injectable } from '@angular/core';
import { PokemonType, pokemonType } from '../model/pokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonTypeService {

  private pokemonTypeClasses :{ [key in pokemonType]?: string} = {
    BUG: 'pokemon-bug',
    DARK:'pokemon-dark',
    DRAGON:'pokemon-dragon',
    FAIRY:'pokemon-fairy',
    FIGHTING:'pokemon-fighting',
    FIRE:'pokemon-fire',
    FLYING:'pokemon-flying',
    GHOST:'pokemon-ghost',
    GRASS:'pokemon-grass',
    GROUND:'pokemon-ground',
    ICE:'pokemon-ice',
    NORMAL:'pokemon-normal',
    POISON:'pokemon-poison',
    PSYCHIC:'pokemon-psychic',
    ROCK:'pokemon-rock',
    STEEL:'pokemon-steel',
    WATER:'pokemon-water',
  }

  getPokemonClass(pokemonType: PokemonType): string {
    return this.pokemonTypeClasses[pokemonType] || '';
  }

}