import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaPokemonesComponent } from './lista-pokemones/lista-pokemones.component';
import { SavePokemonComponent } from './save-pokemon/save-pokemon.component';
import { UpdatePokemonComponent } from './update-pokemon/update-pokemon.component';
import { ListCharactersComponent } from './list-characters/list-characters.component';
import { UpdateCharacterComponent } from './update-character/update-character.component';
import { SaveCharacterComponent } from './save-character/save-character.component';
const routes: Routes = [
  { path: 'pokemones', component: ListaPokemonesComponent },
  { path: '', redirectTo: 'pokemones', pathMatch: 'full' },
  {
    path: 'pokemon/save',
    component: SavePokemonComponent,
  },
  {
    path: 'update-pokemon/:id',
    component: UpdatePokemonComponent,
  },
  { path: 'characters', component: ListCharactersComponent },
  {
    path: 'update-character/:id',
    component: UpdateCharacterComponent,
  },
  {
    path: 'character/save',
    component: SaveCharacterComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
