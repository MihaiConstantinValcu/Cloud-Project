import { NgForOf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Location, LocationsService } from '../../services/locations/locations.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-locations',
  imports: [NgForOf],
  templateUrl: './locations.component.html',
  styleUrl: './locations.component.css'
})
export class LocationsComponent implements OnInit{
 locations: Location[] = []
 
   constructor(private locationService: LocationsService, private router: Router) {}
 
   ngOnInit() {
     this.locationService.getLocations().subscribe((data) => this.locations = data);
   }
 
   deleteLocation(id: string) {
     this.locationService.deleteLocation(id)
     .subscribe(() => window.location.reload());
   }

   goToDetails(id: string){
    this.router.navigate([`/locations/${id}`]);
   }
}
