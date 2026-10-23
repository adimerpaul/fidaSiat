import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Kardex } from '../modelo/kardex';

@Injectable({
  providedIn: 'root'
})
export class KardexService {

  ruta = `${environment.RUTA}/api/v1/kardex`;

  constructor(private _httpClient: HttpClient) {}

  obtenerporpedido(id: number): Observable<Kardex[]> {
    return this._httpClient.get<Kardex[]>(`${this.ruta}/porpedido/${id}`);
  }

}
