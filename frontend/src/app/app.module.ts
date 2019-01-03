import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { BranchDetailComponent } from './branch-detail/branch-detail.component';
import { RouterLinkDirectiveStub } from 'src/testing/router-link-directive-stub.directive';
import { DeploymentHistoryComponent } from './deployment-history/deployment-history.component';
import { BuildHistoryComponent } from './build-history/build-history.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PageNotFoundComponent,
    DashboardComponent,
    ProjectDetailComponent,
    BranchDetailComponent,
    RouterLinkDirectiveStub,
    DeploymentHistoryComponent,
    BuildHistoryComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
