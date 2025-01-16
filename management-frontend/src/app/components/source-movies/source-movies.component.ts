import { Component, OnInit } from '@angular/core';
import { MovieService, SourceMovie } from '../../services/movies/movie.service';
import { Router } from '@angular/router';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-source-movies',
  imports: [ NgForOf],
  templateUrl: './source-movies.component.html',
  styleUrl: './source-movies.component.css'
})
export class SourceMoviesComponent implements OnInit {
  movies: SourceMovie[] = []

  constructor(private movieService: MovieService) {}

  ngOnInit() {
    this.movieService.getMovies().subscribe((data) => this.movies = data);
  }

  deleteMovie(id: string) {
    this.movieService.deleteMovie(id)
    .subscribe(() => window.location.reload());
  }

}
