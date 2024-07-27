import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Pokemon } from '../model/pokemon';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class PokemonService {

  private apiUrl = 'http://localhost:8080/pokemons';

  constructor(private httpClient: HttpClient) {}

  getAllPokemon(): Observable<Pokemon[]> {
    return this.httpClient.get<Pokemon[]>(`${this.apiUrl}`).pipe(
      catchError(this.handleError)
    );
  }

  updatePokemon(id: string, poke: Pokemon): Observable<Object> {
    return this.httpClient.put(`${this.apiUrl}/editar/${id}`, poke);
  }

  findPokemonById(id: string): Observable<Pokemon> {
    return this.httpClient.get<Pokemon>(`${this.apiUrl}/${id}`);
  }

      savePokemon(pokemon: Pokemon): Observable<any> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.httpClient
          .post(this.apiUrl, JSON.stringify(pokemon), { headers })
          .pipe(catchError(this.handleError));
      }

  deletePokemon(id: string): Observable<Object> {
    return this.httpClient.delete(`${this.apiUrl}/eliminar/${id}`);
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
    // Retorna un observable con un mensaje de error 
    return throwError(
      () =>
        new Error(
          'Ha ocurrido un error. Por favor, intenta nuevamente más tarde.'
        )
    );
  }
}
