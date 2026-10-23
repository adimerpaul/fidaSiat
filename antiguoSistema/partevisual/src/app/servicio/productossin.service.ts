import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Productossin } from '../modelo/productossin';

@Injectable({
  providedIn: 'root'
})
export class ProductossinService {

  ruta = `${environment.RUTA}/api/v1/productossin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Productossin[]> {
    return this._httpClient.get<Productossin[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Productossin[]> {
    return this._httpClient.get<Productossin[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerCodigosSin(idpuntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?idpuntoventa=${idpuntoventa}`);
  }
}
