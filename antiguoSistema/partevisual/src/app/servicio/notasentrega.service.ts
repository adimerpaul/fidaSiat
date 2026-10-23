import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Notasentrega } from '../modelo/notasentrega';

@Injectable({
  providedIn: 'root'
})
export class NotasentregaService {

  ruta = `${environment.RUTA}/api/v1/notasentrega`;

  constructor(private _httpClient: HttpClient) {}

  obtenerNotasentrega(termino: string): Observable<Notasentrega[]> {
    return this._httpClient.get<Notasentrega[]>(`${this.ruta}/?termino=${termino}`);
  }

  migrarFactura(id: number): Observable<number>{
    return this._httpClient.get<number>(`${this.ruta}/migrarFactura/${id}`);
  }

  facturaPDF(idfactura: number) {
    return this._httpClient.get(`${this.ruta}/facturaPDF?idfactura=${idfactura}`, {
      responseType: "blob",
    });
  }

  obtenerPorId(id: number): Observable<Notasentrega>{
    return this._httpClient.get<Notasentrega>(`${this.ruta}/${id}`);
  }

}
