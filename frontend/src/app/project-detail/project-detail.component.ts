import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BreadcrumbService } from '../breadcrumb.service';
import { Project } from '../model/project';
import { Branch } from '../model/branch';
import { EditProjectDialogComponent } from './edit-project-dialog/edit-project-dialog.component';
import { ProjectService } from '../project.service';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent implements OnInit {
  @ViewChild('editDialog') editDialog: EditProjectDialogComponent;
  project: Project;
  branches: Branch[];
  constructor(
    private route: ActivatedRoute,
    private crumbs: BreadcrumbService,
    private projectService: ProjectService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    $('#editProjectModal').on('hidden.bs.modal', () => {
      // Clear input
      this.editDialog.initServiceMethod();
    });
    this.route.data.subscribe(
      (data: { project: Project, branches: Branch[] }) => {
        this.project = data.project;
        this.crumbs.setBreadcrumbs([
          {
            name: this.project.name ? this.project.name : 'Error',
            path: 'projects/' + this.project.id
          }
        ]);
        this.branches = data.branches;
      }
    );
  }

  refreshProject() {
    this.projectService.refreshProject(this.project.id).subscribe(
      res => {
        if (res) {
          this.notificationService.send('The project is now being refreshed.', 'info');
        }
      });
  }
}
