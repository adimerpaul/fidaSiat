import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Bitacoras } from '../modelo/bitacoras';

@Injectable({
  providedIn: 'root'
})
export class BitacorasService {

  ruta = `${environment.RUTA}/api/v1/bitacoras`;

  constructor(private _httpClient: HttpClient) { }

  buscarPorTermino(termino: string, pagina: number, cantidad: number): Observable<Bitacoras[]> {
    return this._httpClient.get<Bitacoras[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  cantidadBuscarPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  buscarPorId(id: number): Observable<Bitacoras>{
    return this._httpClient.get<Bitacoras>(`${this.ruta}/${id}`);
  }

}
