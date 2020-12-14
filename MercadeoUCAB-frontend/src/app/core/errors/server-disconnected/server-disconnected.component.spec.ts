import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerDisconnectedComponent } from './server-disconnected.component';

describe('ServerDisconnectedComponent', () => {
  let component: ServerDisconnectedComponent;
  let fixture: ComponentFixture<ServerDisconnectedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServerDisconnectedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerDisconnectedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
