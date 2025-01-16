import { RouterModule, Routes } from '@angular/router';
import { SourceMoviesComponent } from './components/source-movies/source-movies.component';
import { LayoutComponent } from './components/layout/layout.component';
import { LocationsComponent } from './components/locations/locations.component';
import { LocationDetailsComponent } from './components/location-details/location-details.component';
import { AddLocationComponent } from './components/add-location/add-location.component';
import { AddMovieComponent } from './components/add-movie/add-movie/add-movie.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

export const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
          {
            path: '',
            component: SourceMoviesComponent,
            title: 'Source Movies'
          },
          {
            path: 'locations',
            component: LocationsComponent,
            title: 'Locations'
          },
          {
            path: 'locations/add',
            component: AddLocationComponent,
            title: 'Add location'
          },
          {
            path: 'locations/:locationId',
            component: LocationDetailsComponent,
            title: 'Location details'
          },
        
          {
            path: 'movies/add',
            component: AddMovieComponent,
            title: 'Add movie'
          }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes), BrowserModule],
    exports: [RouterModule]
  })
  export class AppRoutingModule {}
