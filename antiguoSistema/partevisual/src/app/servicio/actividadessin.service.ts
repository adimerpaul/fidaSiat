import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Actividadessin } from '../modelo/actividadessin';

@Injectable({
  providedIn: 'root'
})
export class ActividadessinService {

  ruta = `${environment.RUTA}/api/v1/actividadessin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Actividadessin[]> {
    return this._httpClient.get<Actividadessin[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Actividadessin[]> {
    return this._httpClient.get<Actividadessin[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerCodigosSin(puntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?puntoventa=${puntoventa}`);
  }
}
