import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Character } from './character';

@Injectable({
  providedIn: 'root',
})
export class CharacterService {
  private baseUrl = 'http://localhost:8080/personaje';

  constructor(private HttpClient: HttpClient) {}

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

  getListCharacter(): Observable<Character[]> {
    return this.HttpClient.get<Character[]>(`${this.baseUrl}/listar`).pipe(
      catchError(this.handleError)
    );
  }

  updateCharacter(id: number, chara: Character): Observable<Object> {
    return this.HttpClient.put(`${this.baseUrl}/editar/${id}`, chara);
  }

  findCharacterById(id: number): Observable<Character> {
    return this.HttpClient.get<Character>(`${this.baseUrl}/${id}`);
  }

  saveCharacter(chara: Character | FormData): Observable<any> {
    if (chara instanceof Character) {
      return this.HttpClient.post(`${this.baseUrl}/crear`, chara);
    } else if (chara instanceof FormData) {
      return this.HttpClient.post(`${this.baseUrl}/crear`, chara);
    } else {
      throw new Error('Tipo de objeto no valido');
    }
  }

  deleteCharacter(id: number): Observable<Object> {
    return this.HttpClient.delete(`${this.baseUrl}/eliminar/${id}`);
  }
}
