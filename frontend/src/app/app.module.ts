import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaPokemonesComponent } from './lista-pokemones/lista-pokemones.component';
import { HttpClientModule } from '@angular/common/http';
import { SavePokemonComponent } from './save-pokemon/save-pokemon.component';
import { FormsModule } from '@angular/forms';
import { UpdatePokemonComponent } from './update-pokemon/update-pokemon.component';
import { ListCharactersComponent } from './list-characters/list-characters.component';
import { SaveCharacterComponent } from './save-character/save-character.component';
import { UpdateCharacterComponent } from './update-character/update-character.component';

@NgModule({
  declarations: [
    AppComponent,
    ListaPokemonesComponent,
    SavePokemonComponent,
    UpdatePokemonComponent,
    ListCharactersComponent,
    SaveCharacterComponent,
    UpdateCharacterComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
