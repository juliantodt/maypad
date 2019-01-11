import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { BranchDetailComponent } from './branch-detail/branch-detail.component';
import { RouterStubsModule } from 'src/testing/router-link-directive-stub.directive';
import { ProjectgroupDetailComponent } from './dashboard/projectgroup-detail/projectgroup-detail.component';
import { AddProjectgroupDialogComponent } from './dashboard/add-projectgroup-dialog/add-projectgroup-dialog.component';
// tslint:disable-next-line
import { EditProjectgroupDialogComponent } from './dashboard/projectgroup-detail/edit-projectgroup-dialog/edit-projectgroup-dialog.component';
import { ProjectListItemComponent } from './dashboard/projectgroup-detail/project-list-item/project-list-item.component';
import { AddProjectDialogComponent } from './dashboard/projectgroup-detail/add-project-dialog/add-project-dialog.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PageNotFoundComponent,
    DashboardComponent,
    ProjectDetailComponent,
    BranchDetailComponent,
    ProjectgroupDetailComponent,
    AddProjectgroupDialogComponent,
    EditProjectgroupDialogComponent,
    ProjectListItemComponent,
    AddProjectDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterStubsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
