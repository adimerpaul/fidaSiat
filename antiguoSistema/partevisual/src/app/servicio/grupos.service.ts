import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Grupos } from '../modelo/grupos';

@Injectable({
  providedIn: 'root'
})
export class GruposService {

  ruta = `${environment.RUTA}/api/v1/grupos`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Grupos[]> {
    return this._httpClient.get<Grupos[]>(`${this.ruta}/todos`);
  }

  obtenerTodosPorTipo(tipo: number): Observable<Grupos[]> {
    return this._httpClient.get<Grupos[]>(`${this.ruta}/todosPorTipo?tipo=${tipo}`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Grupos[]> {
    return this._httpClient.get<Grupos[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerPorId(id: number): Observable<Grupos>{
    return this._httpClient.get<Grupos>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Grupos): Observable<any>{
    return this._httpClient.post<any>(`${this.ruta}`, dato);
  }

  modificar(dato: Grupos): Observable<any>{
    return this._httpClient.put<any>(`${this.ruta}`, dato);
  }
}
