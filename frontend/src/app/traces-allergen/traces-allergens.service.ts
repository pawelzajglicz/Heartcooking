import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TracesAllergen } from '../models/traces-allergen';

@Injectable({
  providedIn: 'root'
})
export class TracesAllergensService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getTracesAllergens(): Observable<TracesAllergen[]> {
    return this.http.get<TracesAllergen[]>(this.baseUrl + 'traces-allergens');
  }
}
