import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Respuestacui } from '../modelo/respuestacui';
import { Respuestas } from '../modelo/respuestas';
import { Respuestasactividades } from '../modelo/respuestasactividades';
import { Respuestasactividadessector } from '../modelo/respuestasactividadessector';
import { Respuestasfechas } from '../modelo/respuestasfechas';
import { Respuestasleyendas } from '../modelo/respuestasleyendas';
import { Respuestacufd } from '../modelo/respuestacufd';
import { Respuestasmensajesservicio } from '../modelo/respuestasmensajesservicio';
import { Respuestasproductos } from '../modelo/respuestasproductos';
import { Respuestaseventossignificativos } from '../modelo/respuestaseventossignificativos';
import { Respuestasmotivoanulacion } from '../modelo/respuestasmotivoanulacion';
import { Respuestaspaises } from '../modelo/respuestaspaises';
import { Respuestastiposdocumentosidentidad } from '../modelo/respuestastiposdocumentosidentidad';
import { Respuestastiposdocumentossector } from '../modelo/respuestastiposdocumentossector';
import { Respuestastiposemision } from '../modelo/respuestastiposemision';
import { Respuestastiposhabitacion } from '../modelo/respuestastiposhabitacion';
import { Respuestastiposmetodopago } from '../modelo/respuestastiposmetodopago';
import { Respuestastiposmoneda } from '../modelo/respuestastiposmoneda';
import { Respuestastipospuntoventa } from '../modelo/respuestastipospuntoventa';
import { Respuestastiposfactura } from '../modelo/respuestastiposfactura';
import { Respuestastiposunidadmedida } from '../modelo/respuestastiposunidadmedida';
import { Respuestasfacturas } from '../modelo/respuestasfacturas';

@Injectable({
  providedIn: 'root'
})
export class PruebasSIATService {

  private ruta = `${environment.RUTA}/api/v1/pruebasSIAT`;

  constructor(private _httpClient: HttpClient) {}

  verificarConexion(): Observable<Respuestas[]> {
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/verificarConexion`);
  }

  obtenerCUI(puntoventa: number): Observable<Respuestacui> {
    return this._httpClient.get<Respuestacui>(`${this.ruta}/obtenerCUI?idpuntoventa=${puntoventa}`);
  }

  registrarPuntoventa(puntoventa: number): Observable<Respuestas[]> {
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/registrarPuntoventa?idpuntoventa=${puntoventa}`);
  }

  registrarCUIS(puntoventa: number): Observable<Respuestas[]> {
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/registrarCUIS?idpuntoventa=${puntoventa}`);
  }

  obtenerActividades(idpuntoventa: number): Observable<Respuestasactividades> {
    return this._httpClient.get<Respuestasactividades>(`${this.ruta}/obtenerActividades?idpuntoventa=${idpuntoventa}`);
  }

  obtenerFechahora(puntoventa: number): Observable<Respuestasfechas> {
    return this._httpClient.get<Respuestasfechas>(`${this.ruta}/obtenerFechahora?puntoventa=${puntoventa}`);
  }

  obtenerActividadesSector(puntoventa: number): Observable<Respuestasactividadessector> {
    return this._httpClient.get<Respuestasactividadessector>(`${this.ruta}/obtenerActividadesSector?puntoventa=${puntoventa}`);
  }

  obtenerLeyendas(puntoventa: number): Observable<Respuestasleyendas> {
    return this._httpClient.get<Respuestasleyendas>(`${this.ruta}/obtenerLeyendas?puntoventa=${puntoventa}`);
  }

  obtenerMensajesServicio(puntoventa: number): Observable<Respuestasmensajesservicio> {
    return this._httpClient.get<Respuestasmensajesservicio>(`${this.ruta}/obtenerMensajesServicio?puntoventa=${puntoventa}`);
  }

  obtenerProductos(puntoventa: number): Observable<Respuestasproductos> {
    return this._httpClient.get<Respuestasproductos>(`${this.ruta}/obtenerProductos?puntoventa=${puntoventa}`);
  }

  obtenerEventosSignificativos(puntoventa: number): Observable<Respuestaseventossignificativos> {
    return this._httpClient.get<Respuestaseventossignificativos>(`${this.ruta}/obtenerEventosSignificativos?puntoventa=${puntoventa}`);
  }

  obtenerMotivoAnulacion(puntoventa: number): Observable<Respuestasmotivoanulacion> {
    return this._httpClient.get<Respuestasmotivoanulacion>(`${this.ruta}/obtenerMotivoAnulacion?puntoventa=${puntoventa}`);
  }

  obtenerPaises(puntoventa: number): Observable<Respuestaspaises> {
    return this._httpClient.get<Respuestaspaises>(`${this.ruta}/obtenerPaises?puntoventa=${puntoventa}`);
  }

  obtenerTipoDocumentoIdentidad(puntoventa: number): Observable<Respuestastiposdocumentosidentidad> {
    return this._httpClient.get<Respuestastiposdocumentosidentidad>(`${this.ruta}/obtenerTipoDocumentoIdentidad?puntoventa=${puntoventa}`);
  }

  obtenerTipoDocumentosector(puntoventa: number): Observable<Respuestastiposdocumentossector> {
    return this._httpClient.get<Respuestastiposdocumentossector>(`${this.ruta}/obtenerTipoDocumentosector?puntoventa=${puntoventa}`);
  }

  obtenerTipoEmision(puntoventa: number): Observable<Respuestastiposemision> {
    return this._httpClient.get<Respuestastiposemision>(`${this.ruta}/obtenerTipoEmision?puntoventa=${puntoventa}`);
  }

  obtenerTipoHabitacion(puntoventa: number): Observable<Respuestastiposhabitacion> {
    return this._httpClient.get<Respuestastiposhabitacion>(`${this.ruta}/obtenerTipoHabitacion?puntoventa=${puntoventa}`);
  }

  obtenerTipoMetodoPago(puntoventa: number): Observable<Respuestastiposmetodopago> {
    return this._httpClient.get<Respuestastiposmetodopago>(`${this.ruta}/obtenerTipoMetodoPago?puntoventa=${puntoventa}`);
  }

  obtenerTipoMoneda(puntoventa: number): Observable<Respuestastiposmoneda> {
    return this._httpClient.get<Respuestastiposmoneda>(`${this.ruta}/obtenerTipoMoneda?puntoventa=${puntoventa}`);
  }

  obtenerTipoPuntoVenta(puntoventa: number): Observable<Respuestastipospuntoventa> {
    return this._httpClient.get<Respuestastipospuntoventa>(`${this.ruta}/obtenerTipoPuntoVenta?puntoventa=${puntoventa}`);
  }

  obtenerTipoFactura(puntoventa: number): Observable<Respuestastiposfactura> {
    return this._httpClient.get<Respuestastiposfactura>(`${this.ruta}/obtenerTipoFactura?puntoventa=${puntoventa}`);
  }

  obtenerTipoUnidadMedida(puntoventa: number): Observable<Respuestastiposunidadmedida> {
    return this._httpClient.get<Respuestastiposunidadmedida>(`${this.ruta}/obtenerTipoUnidadMedida?puntoventa=${puntoventa}`);
  }

  obtenerCUFDS(puntoventa: number): Observable<Respuestacufd> {
    return this._httpClient.get<Respuestacufd>(`${this.ruta}/obtenerCUFDS?idpuntoventa=${puntoventa}`);
  }

  registrarCUFDS(puntoventa: number): Observable<Respuestas[]> {
    return this._httpClient.get<Respuestas[]>(`${this.ruta}/registrarCUFDS?idpuntoventa=${puntoventa}`);
  }

  emitirFactura(puntoventa: number): Observable<Respuestasfacturas> {
    return this._httpClient.get<Respuestasfacturas>(`${this.ruta}/emitirFactura?puntoventa=${puntoventa}`);
  }

  obtenerEventoSignificativo(ideventosignificativosin: number): Observable<Respuestacufd> {
    return this._httpClient.get<Respuestacufd>(`${this.ruta}/obtenerEventoSignificativo?ideventosignificativosin=${ideventosignificativosin}`);
  }

  regularizarEventoInaccesibilidadSIATo(codigo: number): Observable<Respuestasfacturas> {
    return this._httpClient.get<Respuestasfacturas>(`${this.ruta}/regularizarEventoInaccesibilidadSIATo?codigo=${codigo}`);
  }

  anularFactura(idfactura: number, idanulacionsin: number, motivoanulacion: string): Observable<Respuestasfacturas> {
    return this._httpClient.get<Respuestasfacturas>(`${this.ruta}/anularFactura?idfactura=${idfactura}&idanulacionsin=${idanulacionsin}&motivoanulacion=${motivoanulacion}`);
  }


}
