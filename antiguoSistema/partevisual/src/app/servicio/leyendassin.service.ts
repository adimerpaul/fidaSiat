import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Leyendassin } from '../modelo/leyendassin';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LeyendassinService {

  ruta = `${environment.RUTA}/api/v1/leyendassin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Leyendassin[]> {
    return this._httpClient.get<Leyendassin[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Leyendassin[]> {
    return this._httpClient.get<Leyendassin[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerCodigosSin(idpuntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?idpuntoventa=${idpuntoventa}`);
  }

}
