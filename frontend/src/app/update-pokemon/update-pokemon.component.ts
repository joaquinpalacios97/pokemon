import { Component } from '@angular/core';
import { Pokemon } from '../pokemon';
import { PokemonService } from '../pokemon.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-pokemon',
  templateUrl: './update-pokemon.component.html',
  styleUrl: './update-pokemon.component.css',
})
export class UpdatePokemonComponent {
  id: number;
  poke: Pokemon = new Pokemon();
  imagenSeleccionada: File;

  constructor(
    private pokemonService: PokemonService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Obtener el ID del Pokémon de la URL
    this.id = this.route.snapshot.params['id'];
    // Obtener los datos del Pokémon para mostrar en el formulario
    this.pokemonService.findPokemonById(this.id).subscribe({
      next: (dato: any) => {
        this.poke = dato;
      },
      error: (error) => console.log(error),
    });
  }

  // Método para manejar la selección de la imagen
  onFileSelected(event: any) {
    this.imagenSeleccionada = event.target.files[0];
  }

  goToListPokemon() {
    this.router.navigate(['/pokemones']);
  }

  onSubmit() {
    if (this.imagenSeleccionada) {
      this.convertirImagenABase64(this.imagenSeleccionada)
        .then((base64String) => {
          this.poke.imagenBase64 = base64String;
          this.enviarActualizacion();
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      this.enviarActualizacion();
    }
  }

  enviarActualizacion() {
    // Solo enviar la imagenBase64 en lugar de la imagen completa
    // this.poke.imagen = null; // Asegúrate de que no se esté enviando la imagen como File
    this.pokemonService.updatePokemon(this.id, this.poke).subscribe({
      next: (dato: any) => {
        this.goToListPokemon();
      },
      error: (error) => console.error(error),
    });
  }

  // Método para convertir la imagen seleccionada a base64
  convertirImagenABase64(imagen: File): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(imagen);
      reader.onload = () => {
        const base64String = reader.result?.toString();
        if (base64String) {
          resolve(base64String);
        } else {
          reject('No se pudo convertir la imagen a base64');
        }
      };
      reader.onerror = (error) => {
        reject(error);
      };
    });
  }
}
