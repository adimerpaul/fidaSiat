import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Unidadesmedidas } from '../modelo/unidadesmedidas';

@Injectable({
  providedIn: 'root'
})
export class UnidadesmedidasService {

  ruta = `${environment.RUTA}/api/v1/unidadesmedidas`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Unidadesmedidas[]> {
    return this._httpClient.get<Unidadesmedidas[]>(`${this.ruta}/todos`);
  }

  obtenerCodigosSin(puntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?puntoventa=${puntoventa}`);
  }

}
