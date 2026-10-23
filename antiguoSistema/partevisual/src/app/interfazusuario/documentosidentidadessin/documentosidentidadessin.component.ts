import { Component, OnInit } from '@angular/core';
import { Documentosidentidadessin } from 'src/app/modelo/documentosidentidadessin';
import { DocumentosidentidadessinService } from 'src/app/servicio/documentosidentidadessin.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-documentosidentidadessin',
  templateUrl: './documentosidentidadessin.component.html',
  styleUrls: ['./documentosidentidadessin.component.css']
})
export class DocumentosidentidadessinComponent implements OnInit {

  entidad: 'Documentosidentidadessin';

  datos: Documentosidentidadessin[];
  dato: Documentosidentidadessin;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;

  constructor(
    private _documentosidentidadessinService: DocumentosidentidadessinService,
  ) {
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._documentosidentidadessinService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._documentosidentidadessinService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fobtenercodigossinDocumentosidentidades(punto:number) {
    this._documentosidentidadessinService.obtenerCodigosSin(punto).subscribe( data=> {
      this.fdatos();
      swal.fire('Datos adicionados', 'Datos adicionados con exito', 'success');
    })
  }

}
