import { Component, OnInit } from '@angular/core';
import { NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Cuis } from 'src/app/modelo/cuis';
import { Respuestacui } from 'src/app/modelo/respuestacui';
import { Respuestas } from 'src/app/modelo/respuestas';
import { CuisService } from 'src/app/servicio/cuis.service';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-etapauno',
  templateUrl: './etapauno.component.html',
  styleUrls: ['./etapauno.component.css']
})
export class EtapaunoComponent implements OnInit {

  conexion: Respuestas[];
  respuesta: Respuestacui;
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

  fcui(punto: number) {
    this.respuesta = null;
    this._pruebasSIATService.obtenerCUI(punto).subscribe( data=> {
      this.punto = punto;
      this.respuesta = data
    })
  }

  fregistrarPuntoventa(punto: number) {
    this.registro = null;
    this._pruebasSIATService.registrarPuntoventa(punto).subscribe( data=> {
      this.punto = punto;
      this.registro =  data;
    });
  }

  fregistracui(punto: number) {
    this.registro = null;
    this._pruebasSIATService.registrarCUIS(punto).subscribe( data=> {
      this.punto = punto;
      this.registro =  data;
    });
  }

}
