import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SavePokemonComponent } from './save-pokemon.component';

describe('SavePokemonComponent', () => {
  let component: SavePokemonComponent;
  let fixture: ComponentFixture<SavePokemonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SavePokemonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SavePokemonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
