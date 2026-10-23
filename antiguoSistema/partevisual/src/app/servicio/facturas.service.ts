import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Facturas } from '../modelo/facturas';
import { Detalles } from '../modelo/detalles';

@Injectable({
  providedIn: 'root'
})
export class FacturasService {

  ruta = `${environment.RUTA}/api/v1/facturas`;

  constructor(private _httpClient: HttpClient) {}

  obtenerPorTermino(termino: string, pagina: number, cantidad: number): Observable<Facturas[]> {
    return this._httpClient.get<Facturas[]>(`${this.ruta}/?pagina=${pagina}&cantidad=${cantidad}&termino=${termino}`);
  }

  obtenerPorNotasPedido(): Observable<Facturas[]> {
    return this._httpClient.get<Facturas[]>(`${this.ruta}/notasentrega`);
  }

  obtenerNoEnviados(): Observable<Facturas[]> {
    return this._httpClient.get<Facturas[]>(`${this.ruta}/noenviados`);
  }

  obtenerNoEnviadoso(): Observable<Facturas[]> {
    return this._httpClient.get<Facturas[]>(`${this.ruta}/noenviadoso`);
  }

  obtenerCantidadPorTermino(termino: string): Observable<number> {
    return this._httpClient.get<number>(`${this.ruta}/cantidad?termino=${termino}`);
  }

  obtenerPorId(id: number): Observable<Facturas>{
    return this._httpClient.get<Facturas>(`${this.ruta}/${id}`);
  }

  adicionar(dato: Facturas): Observable<number>{
    return this._httpClient.post<number>(`${this.ruta}`, dato);
  }

  facturaPDF(idfactura: number) {
    return this._httpClient.get(`${this.ruta}/facturaPDF?idfactura=${idfactura}`, {
      responseType: "blob",
    });
  }

  obtenerDescuentos(id: number, dato: Detalles[], idcliente: number): Observable<number>{
    return this._httpClient.put<number>(`${this.ruta}/descuento/${id}/${idcliente}`, dato);
  }

  reporteValidosPDF(fecha: Date) {
    return this._httpClient.get(`${this.ruta}/facturaValidosPDF?fecha=${fecha}`, {
      responseType: "blob",
    });
  }

  reporteAnuladosPDF(fecha: Date) {
    return this._httpClient.get(`${this.ruta}/facturaAnuladosPDF?fecha=${fecha}`, {
      responseType: "blob",
    });
  }

  reporteREchazadosAnuladosPDF(fecha: Date) {
    return this._httpClient.get(`${this.ruta}/facturaRechazadosAnuladosPDF?fecha=${fecha}`, {
      responseType: "blob",
    });
  }

  reporteOtrosPDF(fecha: Date) {
    return this._httpClient.get(`${this.ruta}/facturaOtrosPDF?fecha=${fecha}`, {
      responseType: "blob",
    });
  }

  reporteGeneralPDF(fecha: Date) {
    return this._httpClient.get(`${this.ruta}/facturaGeneralPDF?fecha=${fecha}`, {
      responseType: "blob",
    });
  }

}
