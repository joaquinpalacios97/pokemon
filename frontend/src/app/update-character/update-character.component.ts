import { Component } from '@angular/core';
import { Character } from '../character';
import { CharacterService } from '../character.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-character',
  templateUrl: './update-character.component.html',
  styleUrl: './update-character.component.css',
})
export class UpdateCharacterComponent {
  id: number;
  chara: Character = new Character();
  imagenSeleccionada: File;

  constructor(
    private charaService: CharacterService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInitd(): void {
    this.id = this.route.snapshot.params['id'];
    this.charaService.findCharacterById(this.id).subscribe({
      next: (dato: any) => {
        this.chara = dato;
      },
      error: (error) => console.log(error),
    });
  }

  onFileSelected(event: any) {
    this.imagenSeleccionada = event.target.files[0];
  }

  goToListChara() {
    this.router.navigate(['/characters']);
  }

  onSubmit() {
    if (this.imagenSeleccionada) {
      this.convertirImagenABase64(this.imagenSeleccionada)
        .then((base64String) => {
          this.chara.imagenBase64 = base64String;
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
    // this.poke.imagen = null; 
    this.charaService.updateCharacter(this.id, this.chara).subscribe({
      next: (dato: any) => {
        this.goToListChara();
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
  }
}
