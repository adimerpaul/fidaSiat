import { Component, OnInit } from '@angular/core';
import { Eventossignificativossin } from 'src/app/modelo/eventossignificativossin';
import { Facturas } from 'src/app/modelo/facturas';
import { EventossignificativossinService } from 'src/app/servicio/eventossignificativossin.service';
import { FacturasService } from 'src/app/servicio/facturas.service';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-eventossignificativos',
  templateUrl: './eventossignificativos.component.html',
  styleUrls: ['./eventossignificativos.component.css']
})
export class EventossignificativosComponent implements OnInit {

  fechaActual: string;
  codigo:any=null;

  eventossignigicativossin: Eventossignificativossin[];
  facturasnoenviadas: Facturas[];

  constructor(
    private _eventossignificativossinService: EventossignificativossinService,
    private _facturasService: FacturasService,
    private _pruebasSIATService: PruebasSIATService,
  ) { }

  ngOnInit(): void {
    const hoy = new Date();
    this.fechaActual = hoy.toISOString().slice(0, 16);
    this.feventossginificativossin();
    this.ffacturasnoenviadas();
  }

  ffacturasnoenviadas(){
    this._facturasService.obtenerNoEnviadoso().subscribe( data=> {
      this.facturasnoenviadas = data;
    })
  }

  feventossginificativossin() {
    this._eventossignificativossinService.obtenerTodos().subscribe( data=> {
      this.eventossignigicativossin = data;
    })
  }

  fobtenercodigo(ideventosignificativosin: number){
    this.codigo = null;
    this._pruebasSIATService.obtenerEventoSignificativo(ideventosignificativosin).subscribe( data=>{
      if (data) {
        swal.fire('Codigo', 'Se ha obtenido el codigo: '+data, 'success');
        this.codigo = data;
      } else {
        swal.fire('Error', 'Verifique los rangos de fecha o CUFDs nuevo', 'warning');
      };
    });
  }

  fenviar() {
    this._pruebasSIATService.regularizarEventoInaccesibilidadSIATo(this.codigo).subscribe( data=> {
        swal.fire('Proceso', 'Se ha procesado exitosamente bajo el siguiente detalle '+data.mensajes, 'success');
        this.ffacturasnoenviadas();
    })
  }

}
