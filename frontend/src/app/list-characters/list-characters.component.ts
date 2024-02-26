import { Component } from '@angular/core';
import { Character } from '../character';
import { CharacterService } from '../character.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-list-characters',
  templateUrl: './list-characters.component.html',
  styleUrl: './list-characters.component.css',
})
export class ListCharactersComponent {
  chara: Character[];

  constructor(private charaServi: CharacterService, private router: Router) {}

  private getCharacters() {
    this.charaServi.getListCharacter().subscribe((dato) => {
      this.chara = dato;
    });
  }

  ngOnInit(): void {
    this.getCharacters();
  }

  updateCharacter(id: number) {
    this.router.navigate(['update-character', id]);
  }

  deleteCharacter(id: number) {
    this.charaServi
      .deleteCharacter(id)
      .pipe(
        tap((response: any) => {
          console.log(response.mensaje);
          this.getCharacters();
        })
      )
      .subscribe();
  }

  getImagenUrl(base64String: string): string {
    return 'data:image/jpeg;base64,' + base64String;
  }
}
