import { Component, OnInit } from '@angular/core';
import { Respuestas } from 'src/app/modelo/respuestas';
import { Respuestasfacturas } from 'src/app/modelo/respuestasfacturas';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';

@Component({
  selector: 'app-etapaseis',
  templateUrl: './etapaseis.component.html',
  styleUrls: ['./etapaseis.component.css']
})
export class EtapaseisComponent implements OnInit {

  conexion: Respuestas[];
  respuesta: Respuestasfacturas;
  registro: Respuestas[];
  punto: number;

  constructor(
    private _pruebasSIATService: PruebasSIATService,
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

 /*  femitirFacturaCafc(punto: number) {
    this.respuesta = null;
    this._pruebasSIATService.emitirFacturaCafc(punto).subscribe( data=> {
      this.punto = punto;
      this.respuesta = data
    })
  } */

}
