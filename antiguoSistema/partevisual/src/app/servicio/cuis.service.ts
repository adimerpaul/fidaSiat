import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cuis } from '../modelo/cuis';
import { Respuestas } from '../modelo/respuestas';

@Injectable({
  providedIn: 'root'
})
export class CuisService {

  ruta = `${environment.RUTA}/api/v1/cuis`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Cuis[]> {
    return this._httpClient.get<Cuis[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Cuis[]> {
    return this._httpClient.get<Cuis[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerPorId(id: number): Observable<Cuis>{
    return this._httpClient.get<Cuis>(`${this.ruta}/${id}`);
  }

  adicionarPuntosventas(): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/adicionarPuntosventas`);
  }

  obtenerCui(id: number): Observable<Respuestas[]>{
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/obtenerCui/${id}`);
  }

  adicionarCui(id: number): Observable<Respuestas[]>{
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/adicionarCui/${id}`);
  }

  registrarPuntoventa(id: number): Observable<Respuestas[]>{
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/registrarPuntoventa/${id}`);
  }

}
