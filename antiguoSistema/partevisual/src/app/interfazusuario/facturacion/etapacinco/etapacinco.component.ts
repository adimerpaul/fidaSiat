import { Component, OnInit } from '@angular/core';
import { Respuestacufd } from 'src/app/modelo/respuestacufd';
import { Respuestas } from 'src/app/modelo/respuestas';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';


@Component({
  selector: 'app-etapacinco',
  templateUrl: './etapacinco.component.html',
  styleUrls: ['./etapacinco.component.css']
})
export class EtapacincoComponent implements OnInit {

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

  fobtenerEventoSignificativo(punto: number) {
    this.registro = null;
    /* this._pruebasSIATService.obtenerEventoSignificativo(punto).subscribe( data=> {
      this.punto = punto;
    }); */
  }

}
