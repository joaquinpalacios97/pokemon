import { Component } from '@angular/core';
import { Pokemon } from '../model/pokemon';
import { PokemonService } from '../service/pokemon.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-pokemon',
  templateUrl: './update-pokemon.component.html',
  styleUrl: './update-pokemon.component.css',
})
export class UpdatePokemonComponent {
onFileSelected($event: Event) {
throw new Error('Method not implemented.');
}
onSubmit() {
throw new Error('Method not implemented.');
}
  id: string;
  poke: Pokemon = new Pokemon();
  imagenSeleccionada: File;

  constructor(
    private pokemonService: PokemonService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];

    this.pokemonService.findPokemonById(this.id).subscribe({
      next: (dato: any) => {
        this.poke = dato;
      },
      error: (error) => console.log(error),
    });
  }


 /* onFileSelected(event: any) {
    this.imagenSeleccionada = event.target.files[0];
  }*/

  goToListPokemon() {
    this.router.navigate(['/pokemones']);
  }

/*  onSubmit() {
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
  }*/

  /*enviarActualizacion() {
    
    // this.poke.imagen = null; 
    this.pokemonService.updatePokemon(this.id, this.poke).subscribe({
      next: (dato: any) => {
        this.goToListPokemon();
      },
      error: (error) => console.error(error),
    });
  }


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
  }*/
}
