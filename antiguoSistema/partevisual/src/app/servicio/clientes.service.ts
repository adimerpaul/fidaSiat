import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Clientes } from '../modelo/clientes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  ruta = `${environment.RUTA}/api/v1/clientes`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Clientes[]> {
    return this._httpClient.get<Clientes[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Clientes[]> {
    return this._httpClient.get<Clientes[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerPorId(id: number): Observable<Clientes>{
    return this._httpClient.get<Clientes>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Clientes): Observable<any>{
    return this._httpClient.post<any>(`${this.ruta}`, dato);
  }

  modificar(dato: Clientes): Observable<any>{
    return this._httpClient.put<any>(`${this.ruta}`, dato);
  }

  borrarPorId(id: number): Observable<any>{
    return this._httpClient.delete<any>(`${this.ruta}/${id}`);
  }

  buscarPorDocumentoResgistradoImpuestos(buscar: string): Observable<Clientes>{
    return this._httpClient.get<Clientes>(`${this.ruta}/buscarcliente?buscar=${buscar}`);
  }
}
