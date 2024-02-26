import { Component } from '@angular/core';
import { Character } from '../character';
import { CharacterService } from '../character.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-save-character',
  templateUrl: './save-character.component.html',
  styleUrl: './save-character.component.css',
})
export class SaveCharacterComponent {
  chara: Character = new Character();
  selectedImage: File | undefined;
  imageUrl: string | ArrayBuffer | null = null;

  constructor(private charaService: CharacterService, private router: Router) {}

  saveCharacter() {
    const formData = new FormData();
    formData.append('nombre', this.chara.nombre);
    if (this.selectedImage) {
      formData.append('imagen', this.selectedImage, this.selectedImage.name);
    }
    this.charaService.saveCharacter(formData).subscribe({
      next: (dato: any) => {
        console.log(dato);
        this.goToListCharacter();
      },

      error: (error) => console.log(error),
    });
  }

  onFileSelected(event: any) {
    this.selectedImage = event.target.files[0];
    if (this.selectedImage) {
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedImage);
      reader.onload = () => {
        this.imageUrl = reader.result as string;
      };
    }
  }

  goToListCharacter() {
    this.router.navigate(['/characters']);
  }

  onSubmit() {
    this.saveCharacter();
  }
}
