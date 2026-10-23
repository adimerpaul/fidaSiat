import { Component, OnInit } from '@angular/core';
import { Eventossignificativossin } from 'src/app/modelo/eventossignificativossin';
import { EventossignificativossinService } from 'src/app/servicio/eventossignificativossin.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-eventossignificataivossin',
  templateUrl: './eventossignificataivossin.component.html',
  styleUrls: ['./eventossignificataivossin.component.css']
})
export class EventossignificataivossinComponent implements OnInit {

  entidad: 'Eventossignificativossin';

  datos: Eventossignificativossin[];
  dato: Eventossignificativossin;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;

  constructor(
    private _eventossignificativossinService: EventossignificativossinService,
  ) {
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._eventossignificativossinService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._eventossignificativossinService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
      this.fcantidad();
      this.datos = data;
    });
  }

  limpiar() {
    this.pagina = 0;
    this.buscar = '';
    this.fdatos();
  }

  mostrarMas(evento: any) {
    this.pagina = evento;
    this.fdatos();
  }

  fcambiarcantidad(cantidad: number) {
    this.cantidad=cantidad;
    this.pagina = 0;
    this.buscar = '';
    this.fdatos();
  }

  fobtenercodigossinEventossignificativos(punto:number) {
    this._eventossignificativossinService.obtenerCodigosSin(punto).subscribe( data=> {
      this.fdatos();
      swal.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

}
