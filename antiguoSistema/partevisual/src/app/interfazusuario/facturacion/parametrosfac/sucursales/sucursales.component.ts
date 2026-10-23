import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Sucursales } from 'src/app/modelo/sucursales';
import { SucursalesService } from 'src/app/servicio/sucursales.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-sucursales',
  templateUrl: './sucursales.component.html',
  styleUrls: ['./sucursales.component.css']
})
export class SucursalesComponent implements OnInit {

  entidad: 'Sucursales';

  datos: Sucursales[];
  dato: Sucursales;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  enviando: boolean = false;

  id: number;

  constructor(
    private _sucursalesService: SucursalesService,
    private _ruta: Router,
    private _route: ActivatedRoute,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
  }

  ngOnInit(): void {
    this.id = this._route.snapshot.params['id'];
    this.fdatos();
  }

  fcantidad() {
    this._sucursalesService
      .obtenerCantidadPorTermino(this.buscar, this.id)
      .subscribe((data) => {
        this.total = data;
      });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._sucursalesService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad, this.id).subscribe((data) => {
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
    this.cantidad = cantidad;
    this.pagina = 0;
    this.buscar = '';
    this.fdatos();
  }

  fformulario(dato: Sucursales) {
    this.formulario = this._fb.group({
      sucursal: [dato.sucursal, [Validators.required]],
      nombresucursal: [dato.nombresucursal, [Validators.required]],
      municipio: [dato.municipio, [Validators.required]],
      telefono: [dato.telefono, [Validators.required]],
      direccion: [dato.direccion, [Validators.required]]
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fadicionar(content: any) {
    this.estado = 'Adicionar';
    this.dato = new Sucursales();
    this.fformulario(this.dato);
    this._modalService.open(content);
  }

  fmodificar(id: number, content: any) {
    this.estado = 'Modificar';
    this._sucursalesService.obtenerPorId(id).subscribe((data) => {
      this.dato = data;
      this.fformulario(this.dato);
      this._modalService.open(content);
    });
  }

  faceptar(): void {
    this.enviando = true;
    this.dato.sucursal = this.formulario.value.sucursal;
    this.dato.nombresucursal = this.formulario.value.nombresucursal;
    this.dato.municipio = this.formulario.value.municipio;
    this.dato.telefono = this.formulario.value.telefono;
    this.dato.direccion = this.formulario.value.direccion;
    this.dato.idempresa = this.id;
    if (this.estado === 'Modificar') {
      this._sucursalesService.modificar(this.dato).subscribe(
        (data) => {
          this.fdatos();
          this._modalService.dismissAll();
          swal.fire('Dato modificado', 'Dato modificado con exito', 'success');
        },
        (error) => {
          swal.fire('Error', 'Dato duplicado', 'error');
        }
      );
    } else {
      this._sucursalesService.adicionar(this.dato).subscribe(
        (data) => {
          this.fdatos();
          this._modalService.dismissAll();
          swal.fire('Dato adicionado', 'Dato adicionado con exito', 'success');
        },
        (error) => {
          swal.fire('Error', 'Dato duplicado', 'error');
        }
      );
    }
  }

  fcancelar() {
    this._modalService.dismissAll();
  }

  fpuntosventas(id: number) {
    const _ruta = '/facturacion/parametros/sucursales/' + id;
    this._ruta.navigateByUrl(_ruta);
  }

  fborrar(id: number) {
    swal
    .fire({
      title: 'Estás seguro?',
      icon: 'warning',
      text: 'No podrás recuperar lo borrado!',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Borrar',
    })
    .then((result) => {
      if (result.value) {
        this._sucursalesService.borrar(id).subscribe(
          data=>{
            this.fdatos();
            swal.fire('Dato borrado', 'Dato borrado con exito', 'success');
          },
          error=>{
            swal.fire('Error', 'Tiene puntos de venta seleccionados', 'error');
          }
        );
      }
    });
  }

}
