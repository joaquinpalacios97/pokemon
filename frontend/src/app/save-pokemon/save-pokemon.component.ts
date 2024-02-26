import { Component } from '@angular/core';
import { Pokemon } from '../pokemon';
import { PokemonService } from '../pokemon.service';
import { Router } from '@angular/router';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-save-pokemon',
  templateUrl: './save-pokemon.component.html',
  styleUrl: './save-pokemon.component.css',
})
export class SavePokemonComponent {
  poke: Pokemon = new Pokemon();
  selectedImage: File | undefined;
  imageUrl: string | ArrayBuffer | null = null; // Agrega esta línea para definir la propiedad imageUrl

  constructor(private pokeService: PokemonService, private router: Router) {}

  savePokemon() {
    const formData = new FormData();
    formData.append('nombre', this.poke.nombre);
    formData.append('experiencia', this.poke.experiencia.toString());
    formData.append('nivel_evolucion', this.poke.nivel_evolucion.toString());
    formData.append('evoluciona', this.poke.evoluciona.toString());
    if (this.selectedImage) {
      formData.append('imagen', this.selectedImage, this.selectedImage.name);
    }
    this.pokeService.savePokemon(formData).subscribe({
      next: (dato: any) => {
        console.log(dato);
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
        this.imageUrl = reader.result as string;
      };
    }
  }

  goToListPokemon() {
    this.router.navigate(['/pokemones']);
  }

  onSubmit() {
    this.savePokemon();
  }
}
