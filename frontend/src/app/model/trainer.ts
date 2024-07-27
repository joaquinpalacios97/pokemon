
export class Trainer {
    name: string;
    pokeTrainers: TrainerPokemon[];
  }
  
  export class TrainerPokemon {
    id: string;
    pokemon: Pokemon;
    movements: TrainerPokemonMovement[];
  }
  
  export class Pokemon {
    id: string;
    name: string;
    type: string;
    experience: number;
    evolutionLevel: number;
    evolves: boolean;
    attributes: Attribute;
    image_base64: string;
  }
  
  export class Attribute {
    id: string;
    hp: number;
    attack: number;
    defense: number;
    spAttack: number;
    spDefense: number;
    speed: number;
  }
  
  export class TrainerPokemonMovement {
    id: string;
    movement: Movement;
  }
  
  export class Movement {
    id: string;
    name: string;
    type: string;
    power: number;
    accuracy: number;
    pp: number;
  }