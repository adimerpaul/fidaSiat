import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Sucursales } from '../modelo/sucursales';

@Injectable({
  providedIn: 'root'
})
export class SucursalesService {

  ruta = `${environment.RUTA}/api/v1/sucursales`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(idempresa: number): Observable<Sucursales[]> {
    return this._httpClient.get<Sucursales[]>(`${this.ruta}/todos?idempresa=${idempresa}`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number, idempresa: number): Observable<Sucursales[]> {
    return this._httpClient.get<Sucursales[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}&idempresa=${idempresa}`);
  }

  obtenerCantidadPorTermino(termino: string, idempresa: number): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}&idempresa=${idempresa}`);
  }

  obtenerPorId(id: number): Observable<Sucursales>{
    return this._httpClient.get<Sucursales>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Sucursales): Observable<any>{
    return this._httpClient.post<any>(`${this.ruta}`, dato);
  }

  modificar(dato: Sucursales): Observable<any>{
    return this._httpClient.put<any>(`${this.ruta}`, dato);
  }

  borrar(id: number): Observable<any>{
    return this._httpClient.delete<any>(`${this.ruta}/${id}`);
  }
}
