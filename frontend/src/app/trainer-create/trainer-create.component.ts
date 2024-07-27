import { Component } from '@angular/core';
import { TrainerService } from '../service/trainer.service';
import { Trainer } from '../model/trainer';

@Component({
  selector: 'app-create-trainer',
  templateUrl: './trainer-create.component.html',
  styleUrls: ['./trainer-create.component.css']
})
export class CreateTrainerComponent {
  trainerName: string = '';
  trainer: any = null;
  error: string = '';

  constructor(private trainerService: TrainerService) { }

  createTrainer() {
    this.trainerService.createTrainer(this.trainerName).subscribe({
      next: (response) => {
        console.log(response); // Ver todos los datos de la respuesta en la consola
        this.trainer = response;
        this.error = '';
      },
      error: (err) => {
        this.error = 'An error occurred while creating the trainer.';
        console.error(err);
      }
    });
  }

  getImagenUrl(base64String: string): string {
    console.log('Valor recibido en getImagenUrl:', base64String); // Depuración
    if (base64String) {
      console.log('Base64 de la imagen:', base64String); // Depuración
      //return 'data:image/jpeg;base64,' + base64String;
      return base64String;
    } else {
      console.log('Base64 de la imagen es null o undefined'); // Depuración
      return ''; // O una URL de placeholder
    }
  }


}