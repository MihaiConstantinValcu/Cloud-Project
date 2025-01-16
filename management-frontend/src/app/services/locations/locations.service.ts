import { Injectable } from '@angular/core';
import { Movie } from '../movies/movie.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Location {
  id: string,
  locationName: string,
  locationAddress: string,
  movies: Movie[]
}

@Injectable({
  providedIn: 'root'
})
export class LocationsService {
  private apiUrl = "http://localhost:8081/api/locations"

  constructor(private http: HttpClient) {}

  getLocations(): Observable<Location[]> {
      return this.http.get<Location[]>(this.apiUrl);
    }

  deleteLocation(id: string): Observable<any> {
      return this.http.delete(`${this.apiUrl}/${id}`)
    }

  getById(id: string): Observable<Location> {
    return this.http.get<Location>(`${this.apiUrl}/${id}`)
  }
}
