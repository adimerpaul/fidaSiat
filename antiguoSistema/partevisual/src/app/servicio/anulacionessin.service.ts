import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Anulacionessin } from '../modelo/anulacionessin';

@Injectable({
  providedIn: 'root'
})
export class AnulacionessinService {

  ruta = `${environment.RUTA}/api/v1/anulacionessin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Anulacionessin[]> {
    return this._httpClient.get<Anulacionessin[]>(`${this.ruta}/todos`);
  }

}
