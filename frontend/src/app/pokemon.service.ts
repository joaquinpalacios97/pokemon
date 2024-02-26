import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Pokemon } from './pokemon';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class PokemonService {
  private baseUrl = 'http://localhost:8080/pokemon';

  constructor(private HttpClient: HttpClient) {}

  obtenerListaPokemones(): Observable<Pokemon[]> {
    return this.HttpClient.get<Pokemon[]>(`${this.baseUrl}/listar`).pipe(
      catchError(this.handleError)
    );
  }

  updatePokemon(id: number, poke: Pokemon): Observable<Object> {
    return this.HttpClient.put(`${this.baseUrl}/editar/${id}`, poke);
  }

  findPokemonById(id: number): Observable<Pokemon> {
    return this.HttpClient.get<Pokemon>(`${this.baseUrl}/${id}`);
  }

  savePokemon(pokemon: Pokemon | FormData): Observable<any> {
    if (pokemon instanceof Pokemon) {
      return this.HttpClient.post(`${this.baseUrl}/crear`, pokemon);
    } else if (pokemon instanceof FormData) {
      return this.HttpClient.post(`${this.baseUrl}/crear`, pokemon);
    } else {
      throw new Error('Tipo de objeto no válido');
    }
  }

  deletePokemon(id: number): Observable<Object> {
    return this.HttpClient.delete(`${this.baseUrl}/eliminar/${id}`);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      console.error('Ocurrió un error:', error.error.message);
    } else {
      // El backend retornó un código de error
      console.error(
        `Código de error ${error.status}, ` + `mensaje: ${error.error}`
      );
    }
    // Retorna un observable con un mensaje de error al consumidor del servicio
    return throwError(
      () =>
        new Error(
          'Ha ocurrido un error. Por favor, intenta nuevamente más tarde.'
        )
    );
  }
}
