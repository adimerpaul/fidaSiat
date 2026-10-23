import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Empresas } from '../modelo/empresas';

@Injectable({
  providedIn: 'root'
})
export class EmpresasService {

  ruta = `${environment.RUTA}/api/v1/empresas`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Empresas[]> {
    return this._httpClient.get<Empresas[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Empresas[]> {
    return this._httpClient.get<Empresas[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerPorId(id: number): Observable<Empresas>{
    return this._httpClient.get<Empresas>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Empresas): Observable<any>{
    return this._httpClient.post<any>(`${this.ruta}`, dato);
  }

  modificar(dato: Empresas): Observable<any>{
    return this._httpClient.put<any>(`${this.ruta}`, dato);
  }
}
