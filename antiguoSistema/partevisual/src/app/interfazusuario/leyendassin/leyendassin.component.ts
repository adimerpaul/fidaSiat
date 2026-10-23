import { Component, OnInit } from '@angular/core';
import { Leyendassin } from 'src/app/modelo/leyendassin';
import { LeyendassinService } from 'src/app/servicio/leyendassin.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-leyendassin',
  templateUrl: './leyendassin.component.html',
  styleUrls: ['./leyendassin.component.css']
})
export class LeyendassinComponent implements OnInit {

  entidad: 'Leyendassin';

  datos: Leyendassin[];
  dato: Leyendassin;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;

  constructor(
    private _leyendassinService: LeyendassinService,
  ) {
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._leyendassinService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._leyendassinService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fobtenercodigossinLeyendas(punto:number) {
    this._leyendassinService.obtenerCodigosSin(punto).subscribe( data=> {
      this.fdatos();
      swal.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

}
