import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveCharacterComponent } from './save-character.component';

describe('SaveCharacterComponent', () => {
  let component: SaveCharacterComponent;
  let fixture: ComponentFixture<SaveCharacterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SaveCharacterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SaveCharacterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
