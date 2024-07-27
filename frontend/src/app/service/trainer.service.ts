import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trainer } from '../model/trainer'

@Injectable({
  providedIn: 'root'
})
export class TrainerService {
  private apiUrl = 'http://localhost:8080/trainers'; 

  constructor(private http: HttpClient) {}

  createTrainer(name: string): Observable<Trainer> {
    return this.http.post<Trainer>(this.apiUrl, { name });
  }
}