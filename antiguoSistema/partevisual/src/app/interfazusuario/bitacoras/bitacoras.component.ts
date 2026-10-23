import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Bitacoras } from 'src/app/modelo/bitacoras';
import { BitacorasService } from 'src/app/servicio/bitacoras.service';

@Component({
  selector: 'app-bitacoras',
  templateUrl: './bitacoras.component.html',
  styleUrls: ['./bitacoras.component.css']
})
export class BitacorasComponent implements OnInit {

  entidad: 'Bitacora';

  datos: Bitacoras[];
  dato: Bitacoras;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';
  cantidadTotal: number = 0;

  constructor(
    private _bitacorasService: BitacorasService,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._bitacorasService.cantidadBuscarPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._bitacorasService.buscarPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fmostrar(id: number, content: any) {
    this.estado = 'Mostrar';
    this._bitacorasService.buscarPorId(id).subscribe((data) => {
      this.dato = data;
      this._modalService.open(content);
    });
  }

  fcerrar() {
    this._modalService.dismissAll();
  }

}
