import { Component, Type } from '@angular/core';
import { Pokemon } from '../model/pokemon';
import { PokemonService } from '../service/pokemon.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-lista-pokemones',
  templateUrl: './pokemonList.component.html',
  styleUrl: './pokemonList.component.css',
})
export class ListaPokemonesComponent {
  pokemone: Pokemon[];

  constructor(
    private pokemonServicio: PokemonService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.obtenerPokemones();
  }

  updatePokemon(id: string) {
    this.router.navigate(['update-pokemon', id]);
  }

  private obtenerPokemones() {
    this.pokemonServicio.getAllPokemon().subscribe((dato) => {
      this.pokemone = dato;
      console.log('Datos recibidos del backend:',dato);
    });
  }

  deletePokemon(id: string) {
    this.pokemonServicio
      .deletePokemon(id)
      .pipe(
        tap((response: any) => {
          console.log(response.mensaje); 
          this.obtenerPokemones();
        })
      )
      .subscribe();
  }

  getImagenUrl(base64String: string): string {
      return base64String;
  }

  getPokemonClass(type: string): string {
   return ` pokemon-${type.toLowerCase()}`;
  }

  getImageContainerClass(type: string): string {
    return `image-container-${type.toLowerCase()}`;
  }

  getContentClass(type: string): string {
    return ` content-${type.toLowerCase()}`;
  }
} 