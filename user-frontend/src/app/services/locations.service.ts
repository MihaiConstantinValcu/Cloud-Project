import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Location {
  id: number,
  locationName: string,
  locationAddress: string
}

@Injectable({
  providedIn: 'root'
})

export class LocationsService {
  private apiUrl = "http://localhost:8083/api/tickets/locations"

  constructor(private http: HttpClient) { }

  getLocations(): Observable<Location[]> {
    return this.http.get<Location[]>(this.apiUrl);
  }

  getLocationById(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`)
  }
}
