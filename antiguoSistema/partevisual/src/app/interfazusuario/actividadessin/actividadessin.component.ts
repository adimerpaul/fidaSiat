import { Component, OnInit } from '@angular/core';
import { Actividadessin } from 'src/app/modelo/actividadessin';
import { ActividadessinService } from 'src/app/servicio/actividadessin.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-actividadessin',
  templateUrl: './actividadessin.component.html',
  styleUrls: ['./actividadessin.component.css']
})
export class ActividadessinComponent implements OnInit {

  entidad: 'Actividadessin';

  datos: Actividadessin[];
  dato: Actividadessin;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;

  constructor(
    private _actividadessinService: ActividadessinService,
  ) {
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._actividadessinService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._actividadessinService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fobtenercodigossinActividades(punto:number) {
    this._actividadessinService.obtenerCodigosSin(punto).subscribe( data=> {
      this.fdatos();
      swal.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

}
