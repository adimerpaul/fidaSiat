import { Component, OnInit } from '@angular/core';
import { Respuestacufd } from 'src/app/modelo/respuestacufd';
import { Respuestas } from 'src/app/modelo/respuestas';
import { Respuestasfacturas } from 'src/app/modelo/respuestasfacturas';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';

@Component({
  selector: 'app-etapacuatro',
  templateUrl: './etapacuatro.component.html',
  styleUrls: ['./etapacuatro.component.css']
})
export class EtapacuatroComponent implements OnInit {

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

  femitirFactura(punto: number) {
    this.respuesta = null;
    this._pruebasSIATService.emitirFactura(punto).subscribe( data=> {
      this.punto = punto;
      this.respuesta = data
    })
  }

}
