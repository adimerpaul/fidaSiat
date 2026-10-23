import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Puntosventas } from '../modelo/puntosventas';

@Injectable({
  providedIn: 'root'
})
export class PuntosventasService {

  ruta = `${environment.RUTA}/api/v1/puntosventas`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(idsucursal: number): Observable<Puntosventas[]> {
    return this._httpClient.get<Puntosventas[]>(`${this.ruta}/todos?idsucursal=${idsucursal}`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number, idsucursal: number): Observable<Puntosventas[]> {
    return this._httpClient.get<Puntosventas[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}&idsucursal=${idsucursal}`);
  }

  obtenerCantidadPorTermino(termino: string, idsucursal: number): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}&idsucursal=${idsucursal}`);
  }

  obtenerPorId(id: number): Observable<Puntosventas>{
    return this._httpClient.get<Puntosventas>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Puntosventas): Observable<any>{
    return this._httpClient.post<any>(`${this.ruta}`, dato);
  }

  modificar(dato: Puntosventas): Observable<any>{
    return this._httpClient.put<any>(`${this.ruta}`, dato);
  }

  borrar(id: number): Observable<any>{
    return this._httpClient.delete<any>(`${this.ruta}/${id}`);
  }
}
