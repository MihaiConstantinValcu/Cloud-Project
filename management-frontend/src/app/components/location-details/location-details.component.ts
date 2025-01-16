import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location, LocationsService } from '../../services/locations/locations.service';
import { Movie, MovieService, SourceMovie } from '../../services/movies/movie.service';
import { NgForOf } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-location-details',
  imports: [ReactiveFormsModule, NgForOf],
  templateUrl: './location-details.component.html',
  styleUrl: './location-details.component.css'
})
export class LocationDetailsComponent implements OnInit{
  location?: Location;
  locationId: string | undefined
  movies: Movie[] = []
  sourceMovies: SourceMovie[] = []
  movieForm!: FormGroup
  apiUrl = 'http://localhost:8081/api/movies'; 

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private locationService: LocationsService,
    private movieService: MovieService,
    private router: Router,
    private route: ActivatedRoute,) {}

  ngOnInit() {
    this.locationId = this.route.snapshot.paramMap.get('locationId')!;
    
    this.locationService.getById(this.locationId)
    .subscribe((data) => {
      this.location = data
      this.movies = this.location.movies
    });

    this.movieService.getMovies().subscribe((data) => this.sourceMovies = data)

    this.movieForm = this.fb.group({
          sourceMovie: ['', Validators.required],
          seats: ['', Validators.required],
          fromDate: ['', Validators.required],
          toDate: ['', Validators.required],
        });
  }

  onSubmit(): void {
    console.table(this.movieForm.value.sourceMovie)
    if (this.movieForm.valid) {
      const movie = {
        seats: this.movieForm.value.seats,
        fromDate: this.formatDate(this.movieForm.value.fromDate),
        toDate: this.formatDate(this.movieForm.value.toDate),
        title: this.movieForm.value.sourceMovie.name,
        sourceId: this.movieForm.value.sourceMovie.id,
        locationId: this.locationId
      };

      this.http.post(this.apiUrl, movie).subscribe(
        response => {
          window.location.reload()
          
        },
        error => {
          console.error('Error submitting movie', error);
        }
      );
    }
  }

  deleteMovie(id: string) {
    this.movieService.deleteLocationMovie(id)
    .subscribe(() => window.location.reload());
  }

  private formatDate(date: string): string {
    const d = new Date(date);
    return `${d.getFullYear()}-${(d.getMonth() + 1).toString().padStart(2, '0')}-${d
      .getDate()
      .toString()
      .padStart(2, '0')}`;
  }

}
