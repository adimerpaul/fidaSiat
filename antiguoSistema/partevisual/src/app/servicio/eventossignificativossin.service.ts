import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Eventossignificativossin } from '../modelo/eventossignificativossin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventossignificativossinService {

  ruta = `${environment.RUTA}/api/v1/eventossignificativossin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Eventossignificativossin[]> {
    return this._httpClient.get<Eventossignificativossin[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Eventossignificativossin[]> {
    return this._httpClient.get<Eventossignificativossin[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerCodigosSin(idpuntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?idpuntoventa=${idpuntoventa}`);
  }
}
