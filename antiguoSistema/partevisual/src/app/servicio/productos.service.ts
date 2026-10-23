import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Productos } from '../modelo/productos';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  ruta = `${environment.RUTA}/api/v1/productos`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Productos[]> {
    return this._httpClient.get<Productos[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Productos[]> {
    return this._httpClient.get<Productos[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerPorId(id: number): Observable<Productos>{
    return this._httpClient.get<Productos>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Productos): Observable<any>{
    return this._httpClient.post<any>(`${this.ruta}`, dato);
  }

  modificar(dato: Productos): Observable<any>{
    return this._httpClient.put<any>(`${this.ruta}`, dato);
  }

  subirImagen(archivo: File, id: number): Observable<any>{
    const formArchivo = new FormData();
    formArchivo.append("archivo", archivo);
    return this._httpClient.post<void>(`${this.ruta}/subirImagen/${id}`, formArchivo, {
      reportProgress: true,
      responseType: 'json',
    });
  }

  obtenerPorIdgrupoConPaginacion(id: number, pagina: number, cantidad: number): Observable<Productos[]> {
    return this._httpClient.get<Productos[]>(`${this.ruta}/obtenerPorIdgrupoConPaginacion?idgrupo=${id}&pagina=${pagina}&cantidad=${cantidad}`);
  }

  cantidadPorIdgrupo(id: number): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidadPorIdgrupo?idgrupo=${id}`);
  }

  obtenerPorCodigocorto(codigocorto: number): Observable<Productos>{
    return this._httpClient.get<Productos>(`${this.ruta}/codigocorto/${codigocorto}`);
  }

}
