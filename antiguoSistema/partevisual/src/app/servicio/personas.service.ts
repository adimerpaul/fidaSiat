import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Personas } from '../modelo/personas';

@Injectable({
  providedIn: 'root',
})
export class PersonasService {
  ruta = `${environment.RUTA}/api/v1/personas`;

  constructor(private _httpClient: HttpClient) {}

  perfil(): Observable<Personas> {
    return this._httpClient.get<Personas>(`${this.ruta}/perfil`);
  }

  buscarPersona(termino: string): Observable<Personas> {
    return this._httpClient.get<Personas>(`${this.ruta}/buscarPersona?termino=${termino}`);
  }

}
