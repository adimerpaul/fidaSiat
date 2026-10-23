import { Component, OnInit } from '@angular/core';
import { Respuestacufd } from 'src/app/modelo/respuestacufd';
import { Respuestas } from 'src/app/modelo/respuestas';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';

@Component({
  selector: 'app-etapatres',
  templateUrl: './etapatres.component.html',
  styleUrls: ['./etapatres.component.css']
})
export class EtapatresComponent implements OnInit {

  conexion: Respuestas[];
  respuesta: Respuestacufd;
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

  fcufd(punto: number) {
    this.respuesta = null;
    this._pruebasSIATService.obtenerCUFDS(punto).subscribe( data=> {
      this.punto = punto;
      this.respuesta = data
    })
  }

  fregistracufd(punto: number) {
    this.registro = null;
    this._pruebasSIATService.registrarCUFDS(punto).subscribe( data=> {
      this.punto = punto;
      this.registro = data;
    });
  }

}
