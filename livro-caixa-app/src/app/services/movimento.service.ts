import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movimento } from '../models/movimento';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class MovimentoService {

  constructor(private http:HttpClient) { }

  findAll(): Observable<Movimento[]> {
    return this.http.get<Movimento[]>(`${API_CONFIG.baseUrl}/api/movimento`);
  }

  findById(id: any): Observable<Movimento> {
    return this.http.get<Movimento>(`${API_CONFIG.baseUrl}/api/movimento/${id}`);
  }

   create(movimento: Movimento): Observable<Movimento> {
     return this.http.post<Movimento>(`${API_CONFIG.baseUrl}/api/movimento`, movimento);
   }

   update(movimento: Movimento): Observable<Movimento> {
     return this.http.put<Movimento>(`${API_CONFIG.baseUrl}/api/movimento`, movimento);
   }

  delete(id: any): Observable<Movimento> {
    return this.http.delete<Movimento>(`${API_CONFIG.baseUrl}/api/movimento/${id}`);
  }
}
