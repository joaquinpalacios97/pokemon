import { Component } from '@angular/core';
import { Pokemon } from '../pokemon';
import { PokemonService } from '../pokemon.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-lista-pokemones',
  templateUrl: './lista-pokemones.component.html',
  styleUrl: './lista-pokemones.component.css',
})
export class ListaPokemonesComponent {
  pokemone: Pokemon[];

  constructor(
    private pokemonServicio: PokemonService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.obtenerPokemones();
  }

  updatePokemon(id: number) {
    this.router.navigate(['update-pokemon', id]);
  }

  private obtenerPokemones() {
    this.pokemonServicio.obtenerListaPokemones().subscribe((dato) => {
      this.pokemone = dato;
    });
  }

  deletePokemon(id: number) {
    this.pokemonServicio
      .deletePokemon(id)
      .pipe(
        tap((response: any) => {
          console.log(response.mensaje); // Mensaje de éxito devuelto por el servidor
          this.obtenerPokemones();
        })
      )
      .subscribe();
  }

  getImagenUrl(base64String: string): string {
    return 'data:image/jpeg;base64,' + base64String;
  }
}
