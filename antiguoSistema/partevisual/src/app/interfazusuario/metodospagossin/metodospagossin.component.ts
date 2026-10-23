import { Component, OnInit } from '@angular/core';
import { Metodospagossin } from 'src/app/modelo/metodospagossin';
import { MetodospagossinService } from 'src/app/servicio/metodospagossin.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-metodospagossin',
  templateUrl: './metodospagossin.component.html',
  styleUrls: ['./metodospagossin.component.css']
})
export class MetodospagossinComponent implements OnInit {

  entidad: 'Metodospagossin';

  datos: Metodospagossin[];
  dato: Metodospagossin;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;

  constructor(
    private _metodospagossinService: MetodospagossinService,
  ) {
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._metodospagossinService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._metodospagossinService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fobtenercodigossinMetodospagos(punto:number) {
    this._metodospagossinService.obtenerCodigosSin(punto).subscribe( data=> {
      this.fdatos();
      swal.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

}
