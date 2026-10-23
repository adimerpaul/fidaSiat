import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cufds } from '../modelo/cufds';

@Injectable({
  providedIn: 'root'
})
export class CufdsService {

  ruta = `${environment.RUTA}/api/v1/cufds`;

  constructor(private _httpClient: HttpClient) {}

  obtenerUltimos(id: number): Observable<Cufds[]> {
    return this._httpClient.get<Cufds[]>(`${this.ruta}/ultimos?idpuntoventa=${id}`);
  }
}
