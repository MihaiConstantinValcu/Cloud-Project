import { NgForOf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-location',
  imports: [ReactiveFormsModule],
  templateUrl: './add-location.component.html',
  styleUrl: './add-location.component.css'
})
export class AddLocationComponent {
  locationForm!: FormGroup;
  apiUrl = 'http://localhost:8081/api/locations'; 

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.locationForm = this.fb.group({
      locationName: ['', Validators.required],
      locationAddress: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.locationForm.valid) {
      const location = {
        ...this.locationForm.value
      };

      this.http.post(this.apiUrl, location).subscribe(
        response => {
          console.log('Location submitted successfully', response);
          this.locationForm.reset();
          alert("Location saved successfully")
          
        },
        error => {
          console.error('Error submitting location', error);
        }
      );
    }
  }
}
