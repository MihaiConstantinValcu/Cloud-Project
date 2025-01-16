import { NgModule } from '@angular/core';
import { LocationListComponent } from './components/location-list/location-list.component';
import { RouterModule, Routes } from '@angular/router';
import { CinemaComponent } from './components/cinema/cinema.component';


export const routes: Routes = [
    { path: '', component: LocationListComponent },
    { path: 'cinema/:id', component: CinemaComponent },
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}
