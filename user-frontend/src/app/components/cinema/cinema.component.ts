import { CommonModule, DatePipe, LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { LocationsService } from '../../services/locations.service';
import { HttpClient } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-cinema',
  imports: [CommonModule, RouterModule],
  templateUrl: './cinema.component.html',
  styleUrl: './cinema.component.css',
  providers: [DatePipe]
})
export class CinemaComponent implements OnInit{
  location: any;
  movies: any[] = [];
  locationId: string | undefined;

  constructor(
    private route: ActivatedRoute,
    private locationsService: LocationsService,
    private datePipe: DatePipe,
    private http: HttpClient
  ) {}

  buyTicket(movieId: string) {
    const currentDate = new Date().toISOString().split('T')[0];

    const requestBody = {
      movieId: movieId,
      date: currentDate
    }

    this.http.post('http://localhost:8083/api/tickets', requestBody, { responseType: 'blob' })
      .subscribe((response: Blob) => {
        saveAs(response, 'ticket.txt');
      }, error => {
        alert('Nu mai sunt locuri disponibile pentru acest film');
        console.error('Error buying ticket:', error);
      });
  }

  formatDate(date: string): string {
    return this.datePipe.transform(date, 'yyyy-MM-dd') || ''; 
  }


  ngOnInit() {
    this.locationId = this.route.snapshot.paramMap.get('id')!;

    this.locationsService.getLocationById(this.locationId).subscribe((data) => {
      this.location = data;
      this.movies = data.movies;
    })
  }
}
