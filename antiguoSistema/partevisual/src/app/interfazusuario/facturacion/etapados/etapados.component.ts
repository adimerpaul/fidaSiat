import { Component, OnInit } from '@angular/core';
import { Productossin } from 'src/app/modelo/productossin';
import { Respuestas } from 'src/app/modelo/respuestas';
import { Respuestasactividades } from 'src/app/modelo/respuestasactividades';
import { Respuestasactividadessector } from 'src/app/modelo/respuestasactividadessector';
import { Respuestaseventossignificativos } from 'src/app/modelo/respuestaseventossignificativos';
import { Respuestasfechas } from 'src/app/modelo/respuestasfechas';
import { Respuestasleyendas } from 'src/app/modelo/respuestasleyendas';
import { Respuestasmensajesservicio } from 'src/app/modelo/respuestasmensajesservicio';
import { Respuestasmotivoanulacion } from 'src/app/modelo/respuestasmotivoanulacion';
import { Respuestaspaises } from 'src/app/modelo/respuestaspaises';
import { Respuestasproductos } from 'src/app/modelo/respuestasproductos';
import { Respuestastiposdocumentosidentidad } from 'src/app/modelo/respuestastiposdocumentosidentidad';
import { Respuestastiposdocumentossector } from 'src/app/modelo/respuestastiposdocumentossector';
import { Respuestastiposemision } from 'src/app/modelo/respuestastiposemision';
import { Respuestastiposfactura } from 'src/app/modelo/respuestastiposfactura';
import { Respuestastiposhabitacion } from 'src/app/modelo/respuestastiposhabitacion';
import { Respuestastiposmetodopago } from 'src/app/modelo/respuestastiposmetodopago';
import { Respuestastiposmoneda } from 'src/app/modelo/respuestastiposmoneda';
import { Respuestastipospuntoventa } from 'src/app/modelo/respuestastipospuntoventa';
import { Respuestastiposunidadmedida } from 'src/app/modelo/respuestastiposunidadmedida';
import { ActividadessinService } from 'src/app/servicio/actividadessin.service';
import { LeyendassinService } from 'src/app/servicio/leyendassin.service';
import { ProductossinService } from 'src/app/servicio/productossin.service';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';
import { UnidadesmedidasService } from 'src/app/servicio/unidadesmedidas.service';
import mensajes from 'sweetalert2';


@Component({
  selector: 'app-etapados',
  templateUrl: './etapados.component.html',
  styleUrls: ['./etapados.component.css']
})
export class EtapadosComponent implements OnInit {

  punto: number;

  conexion: Respuestas[];
  respuestaActividades: Respuestasactividades;
  respuestaFechas: Respuestasfechas;
  respuestaActividadessector :Respuestasactividadessector;
  respuestaLeyendas :Respuestasleyendas;
  respuestaMensajesservicio : Respuestasmensajesservicio
  respuestaProductos: Respuestasproductos;
  respuestaEventossignificativos: Respuestaseventossignificativos;
  respuestaMotivoanulacion: Respuestasmotivoanulacion;
  respuestaPaises: Respuestaspaises;
  respuestaTiposdocumentosidentidad: Respuestastiposdocumentosidentidad;
  respuestaTiposdocumentossector: Respuestastiposdocumentossector;
  respuestaTiposemision: Respuestastiposemision;
  respuestaTiposhabitacion: Respuestastiposhabitacion;
  respuestaTiposmetodopago: Respuestastiposmetodopago;
  respuestaTiposmoneda: Respuestastiposmoneda;
  respuestaTipospuntoventa: Respuestastipospuntoventa;
  respuestaTiposfactura: Respuestastiposfactura;
  respuestaTiposunidadmedida: Respuestastiposunidadmedida;

  constructor(
    private _pruebasSIATService: PruebasSIATService,
    private _unidadesmedidasService: UnidadesmedidasService,
    private _actividadessinService: ActividadessinService,
    private _leyendassinService: LeyendassinService,
    private _productossinService: ProductossinService
  ) {}

  ngOnInit() {
    this.fverificarConexion();
  }

  fverificarConexion() {
    this.conexion = null;
    this._pruebasSIATService.verificarConexion().subscribe( data=> {
      this.conexion =  data;
    });
  }

  fobtenerActividades(idpuntoventa: number) {
    this.respuestaActividades = null;
    this._pruebasSIATService.obtenerActividades(idpuntoventa).subscribe( data=> {
      this.punto = idpuntoventa;
      this.respuestaActividades = data
    })
  }

  fobtenerFechahora(punto: number) {
    this.respuestaFechas = null;
    this._pruebasSIATService.obtenerFechahora(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaFechas = data
    })
  }

  fobtenerActividadesSector(punto: number) {
    this.respuestaActividadessector = null;
    this._pruebasSIATService.obtenerActividadesSector(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaActividadessector = data
    })
  }

  fobtenerLeyendas(punto: number) {
    this.respuestaLeyendas = null;
    this._pruebasSIATService.obtenerLeyendas(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaLeyendas = data
    })
  }

  fobtenerMensajesServicio(punto: number) {
    this.respuestaMensajesservicio = null;
    this._pruebasSIATService.obtenerMensajesServicio(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaMensajesservicio = data
    })
  }

  fobtenerProductos(punto: number) {
    this.respuestaProductos = null;
    this._pruebasSIATService.obtenerProductos(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaProductos = data
    })
  }

  fobtenerEventosSignificativos(punto: number) {
    this.respuestaEventossignificativos = null;
    this._pruebasSIATService.obtenerEventosSignificativos(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaEventossignificativos = data
    })
  }

  fobtenerMotivoAnulacion(punto: number) {
    this.respuestaMotivoanulacion = null;
    this._pruebasSIATService.obtenerMotivoAnulacion(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaMotivoanulacion = data
    })
  }

  fobtenerPaises(punto: number) {
    this.respuestaPaises = null;
    this._pruebasSIATService.obtenerPaises(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaPaises = data
    })
  }

  fobtenerTipoDocumentoIdentidad(punto: number) {
    this.respuestaTiposdocumentosidentidad = null;
    this._pruebasSIATService.obtenerTipoDocumentoIdentidad(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposdocumentosidentidad = data
    })
  }

  fobtenerTipoDocumentosector(punto: number) {
    this.respuestaTiposdocumentossector = null;
    this._pruebasSIATService.obtenerTipoDocumentosector(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposdocumentossector = data
    })
  }

  fobtenerTipoEmision(punto: number) {
    this.respuestaTiposemision = null;
    this._pruebasSIATService.obtenerTipoEmision(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposemision = data
    })
  }

  fobtenerTipoHabitacion(punto: number) {
    this.respuestaTiposhabitacion = null;
    this._pruebasSIATService.obtenerTipoHabitacion(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposhabitacion = data
    })
  }

  fobtenerTipoMetodoPago(punto: number) {
    this.respuestaTiposmetodopago = null;
    this._pruebasSIATService.obtenerTipoMetodoPago(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposmetodopago = data
    })
  }

  fobtenerTipoMoneda(punto: number) {
    this.respuestaTiposmoneda = null;
    this._pruebasSIATService.obtenerTipoMoneda(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposmoneda = data
    })
  }

  fobtenerTipoPuntoVenta(punto: number) {
    this.respuestaTipospuntoventa = null;
    this._pruebasSIATService.obtenerTipoPuntoVenta(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTipospuntoventa = data
    })
  }

  fobtenerTipoFactura(punto: number) {
    this.respuestaTiposfactura = null;
    this._pruebasSIATService.obtenerTipoFactura(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposfactura = data
    })
  }

  fobtenerTipoUnidadMedida(punto: number) {
    this.respuestaTiposunidadmedida = null;
    this._pruebasSIATService.obtenerTipoUnidadMedida(punto).subscribe( data=> {
      this.punto = punto;
      this.respuestaTiposunidadmedida = data
    })
  }

  fobtenercodigossinActividades(punto:number) {
    this._actividadessinService.obtenerCodigosSin(punto).subscribe( data=> {
      mensajes.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

  fobtenercodigossinLeyendas(punto:number) {
    this._leyendassinService.obtenerCodigosSin(punto).subscribe( data=> {
      mensajes.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

  fobtenercodigossinUnidadesMedidas(punto:number) {
    this._unidadesmedidasService.obtenerCodigosSin(punto).subscribe( data=> {
      mensajes.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

  fobtenercodigossinProductosSIN(punto:number) {
    this._productossinService.obtenerCodigosSin(punto).subscribe( data=> {
      mensajes.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }


}
