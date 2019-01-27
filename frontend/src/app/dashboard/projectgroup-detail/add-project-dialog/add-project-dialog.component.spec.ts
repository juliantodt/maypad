import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProjectDialogComponent } from './add-project-dialog.component';
import { FormsModule } from '@angular/forms';
import { ProjectgroupServiceStub } from 'src/testing/projectgroup.service.stub';
import { ProjectgroupService } from 'src/app/projectgroup.service';

describe('AddProjectDialogComponent', () => {
  let component: AddProjectDialogComponent;
  let fixture: ComponentFixture<AddProjectDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AddProjectDialogComponent],
      imports: [FormsModule],
      providers: [{ provide: ProjectgroupService, useClass: ProjectgroupServiceStub }]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProjectDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should clear info', () => {
    component.repoUrl = 'a';
    component.sshKey = 'a';
    component.username = 'a';
    component.password = 'a';
    component.clearInput();
    expect(component.repoUrl).toBe('');
    expect(component.sshKey).toBe('');
    expect(component.username).toBe('');
    expect(component.password).toBe('');
  });

  it('should select index', () => {
    component.selectedIndex = 0;
    component.setSelected(2);
    expect(component.selectedIndex).toBe(2);
  });

});
