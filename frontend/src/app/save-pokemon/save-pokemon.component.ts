import { Component } from '@angular/core';
import { Pokemon } from '../model/pokemon';
import { PokemonService } from '../service/pokemon.service';
import { Router } from '@angular/router';
import { Observer } from 'rxjs';
import { pokemonType } from '../model/pokemon';
@Component({
  selector: 'app-save-pokemon',
  templateUrl: './save-pokemon.component.html',
  styleUrl: './save-pokemon.component.css',
})
export class SavePokemonComponent {
  poke: Pokemon = new Pokemon();
  selectedImage: File | undefined;
  imageUrl: string | ArrayBuffer | null = null; 
  /*pokemonTypes = Object.values(pokemonType);*/

  constructor(private pokeService: PokemonService, private router: Router) {}

  pokemonTypes = Object.keys(pokemonType).filter(key => isNaN(Number(key)));

      savePokemon() {
        if (this.selectedImage) {
          const reader = new FileReader();
          reader.readAsDataURL(this.selectedImage);
          reader.onload = () => {
            this.poke.image_base64 = reader.result as string; // Guarda la imagen como Base64
            this.sendPokemon();
          };
        } else {
          this.sendPokemon();
        }
      }
    
      sendPokemon() {
        this.pokeService.savePokemon(this.poke).subscribe({
          next: (data: any) => {
            console.log(data);
            this.goToListPokemon();
          },
          error: (error) => console.log(error),
        });
      }
    
      onFileSelected(event: any) {
        this.selectedImage = event.target.files[0];
        if (this.selectedImage) {
          const reader = new FileReader();
          reader.readAsDataURL(this.selectedImage);
          reader.onload = () => {
            this.imageUrl = reader.result;
          };
        }
      }
    
      goToListPokemon() {
        this.router.navigate(['/pokemons']);
      }
    
      onSubmit() {
        this.savePokemon();
      }
}

