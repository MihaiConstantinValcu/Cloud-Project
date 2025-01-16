import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface SourceMovie {
  id: string,
  name: string,
  description: string,
  rating: string,
  publishDate: Date
}

export interface Movie {
  id: string,
  title: string,
  sourceId: string,
  locationId: string,
  seats: number,
  fromDate: Date,
  toDate: Date
}

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private apiUrl = "http://localhost:8082/api/movies"
  private locationApiUrl = "http://localhost:8081/api/movies"

  constructor(private http: HttpClient) {}

  getMovies(): Observable<SourceMovie[]> {
    return this.http.get<SourceMovie[]>(this.apiUrl);
  }

  deleteMovie(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`)
  }

  deleteLocationMovie(id: string): Observable<any> {
    return this.http.delete(`${this.locationApiUrl}/${id}`)
  }
}
