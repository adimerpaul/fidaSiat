import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Metodospagossin } from '../modelo/metodospagossin';

@Injectable({
  providedIn: 'root'
})
export class MetodospagossinService {

  ruta = `${environment.RUTA}/api/v1/metodospagossin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Metodospagossin[]> {
    return this._httpClient.get<Metodospagossin[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Metodospagossin[]> {
    return this._httpClient.get<Metodospagossin[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerCodigosSin(idpuntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?idpuntoventa=${idpuntoventa}`);
  }
}
