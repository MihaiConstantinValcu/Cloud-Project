import { Component, OnInit } from '@angular/core';
import { Location, LocationsService } from '../../services/locations.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-location-list',
  imports: [CommonModule],
  templateUrl: './location-list.component.html',
  styleUrl: './location-list.component.css'
})
export class LocationListComponent implements OnInit{
  locations: Location[] = []

  constructor(private locationsService: LocationsService, private router: Router) {}

  ngOnInit() {
    this.locationsService.getLocations().subscribe((data) => (this.locations = data));
  }

  seeMovies(locationId: number): void {
    this.router.navigate([`/cinema/${locationId}`]);
  }
}
