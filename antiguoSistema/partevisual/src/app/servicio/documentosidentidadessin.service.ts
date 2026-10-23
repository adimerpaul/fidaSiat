import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Documentosidentidadessin } from '../modelo/documentosidentidadessin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocumentosidentidadessinService {

  ruta = `${environment.RUTA}/api/v1/documentosidentidadessin`;

  constructor(private _httpClient: HttpClient) {}

  obtenerTodos(): Observable<Documentosidentidadessin[]> {
    return this._httpClient.get<Documentosidentidadessin[]>(`${this.ruta}/todos`);
  }

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Documentosidentidadessin[]> {
    return this._httpClient.get<Documentosidentidadessin[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerCodigosSin(idpuntoventa: number): Observable<any> {
    return this._httpClient.get<any>(`${this.ruta}/obtenerCodigosSin?idpuntoventa=${idpuntoventa}`);
  }
}
