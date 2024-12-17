import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Lancamentos } from '../models/lancamentos';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  constructor(private http:HttpClient) { }

  create(lancamento: Lancamentos): Observable<Lancamentos> {
    return this.http.post<Lancamentos>(`${API_CONFIG.baseUrl}/api/lancamento`, lancamento);
  }

  update(lancamento: Lancamentos): Observable<Lancamentos> {
    return this.http.put<Lancamentos>(`${API_CONFIG.baseUrl}/api/lancamento`, lancamento);
  }

  delete(id: Number): Observable<Lancamentos> {
    return this.http.delete<Lancamentos>(`${API_CONFIG.baseUrl}/api/lancamento/${id}`);
  }



  findById(id: any): Observable<Lancamentos> {
    return this.http.get<Lancamentos>(`${API_CONFIG.baseUrl}/api/lancamento/${id}`);
  }
}
