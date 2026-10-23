import { Component, OnInit } from '@angular/core';
import { Respuestacufd } from 'src/app/modelo/respuestacufd';
import { Respuestas } from 'src/app/modelo/respuestas';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';

@Component({
  selector: 'app-etapasiete',
  templateUrl: './etapasiete.component.html',
  styleUrls: ['./etapasiete.component.css']
})
export class EtapasieteComponent implements OnInit {

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

  fanularFactura(punto: number) {
    this.registro = null;
    /* this._pruebasSIATService.anularFactura(1).subscribe( data=> {
      this.punto = punto;
    }); */
  }

}
