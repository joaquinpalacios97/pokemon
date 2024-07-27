
export enum pokemonType {
  WATER,
  STEEL ,
  ICE ,
  DRAGON ,
  FIRE ,
  PSYCHIC ,
  GHOST ,
  DARK ,
  FAIRY ,
  ROCK ,
  NORMAL ,
  BUG ,
  ELECTRIC ,
  GRASS ,
  GROUND,
  FIGHTING,
  FLYING ,
  POISON 
}

export class Pokemon {
  //id_pokemon: string;
  name: string;
  type: string;
  experience: number;
  evolutionLevel: number;
  evolves: boolean;
  image_base64: string;
  //imagen: File;
  attributes: {
    hp: number;
    attack: number;
    defense: number;
    sp_attack: number;
    sp_defense: number;
    speed: number;
  }= {
    hp: 1,
    attack: 1,
    defense: 1,
    sp_attack: 1,
    sp_defense: 1,
    speed: 1,
  };
}