import { ComponentFixture, TestBed } from '@angular/core/testing';
import { WorkationsComponent } from './workations.component';

describe('WorkationsComponent', () => {
  let component: WorkationsComponent;
  let fixture: ComponentFixture<WorkationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkationsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(WorkationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
