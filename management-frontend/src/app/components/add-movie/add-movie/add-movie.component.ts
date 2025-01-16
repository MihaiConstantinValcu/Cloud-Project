import { NgForOf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-movie',
  imports: [ReactiveFormsModule, NgForOf],
  templateUrl: './add-movie.component.html',
  styleUrl: './add-movie.component.css'
})
export class AddMovieComponent implements OnInit{
  movieForm!: FormGroup;
  ratings = ['G', 'PG', 'PG_13', 'R', 'NC_17'];
  apiUrl = 'http://localhost:8082/api/movies'; 

  constructor(
    private fb: FormBuilder, 
    private http: HttpClient) {}

  ngOnInit(): void {
    this.movieForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      rating: ['', Validators.required],
      publishDate: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.movieForm.valid) {
      const movie = {
        ...this.movieForm.value,
        publishDate: this.formatDate(this.movieForm.value.publishDate),
      };

      this.http.post(this.apiUrl, movie).subscribe(
        response => {
          console.log('Movie submitted successfully', response);
          this.movieForm.reset();
          alert("Movie saved successfully")
          
        },
        error => {
          console.error('Error submitting movie', error);
        }
      );
    }
  }

  private formatDate(date: string): string {
    const d = new Date(date);
    return `${d.getFullYear()}-${(d.getMonth() + 1).toString().padStart(2, '0')}-${d
      .getDate()
      .toString()
      .padStart(2, '0')}`;
  }
}
