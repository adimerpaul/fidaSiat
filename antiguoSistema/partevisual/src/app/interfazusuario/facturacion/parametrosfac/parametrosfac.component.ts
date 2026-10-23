import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Empresas } from 'src/app/modelo/empresas';
import { EmpresasService } from 'src/app/servicio/empresas.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-parametrosfac',
  templateUrl: './parametrosfac.component.html',
  styleUrls: ['./parametrosfac.component.css']
})
export class ParametrosfacComponent implements OnInit {

  entidad: 'Empresas';

  datos: Empresas[];
  dato: Empresas;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  enviando: boolean = false;

  constructor(
    private _empresasService: EmpresasService,
    private _ruta: Router,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'lg';
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._empresasService
      .obtenerCantidadPorTermino(this.buscar)
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
    this._empresasService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fformulario(dato: Empresas) {
    this.formulario = this._fb.group({
      nit: [dato.nit, [Validators.required]],
      razonsocial: [dato.razonsocial, [Validators.required]],
      codigosistema: [dato.codigosistema, [Validators.required]],
      token: [dato.token, [Validators.required]],
      fechahora: [dato.fechahora, [Validators.required]],
      ambiente: [dato.ambiente, [Validators.required]],
      modalidad: [dato.modalidad, [Validators.required]],
      ruta: [dato.ruta, [Validators.required]],
      /* cafc: [dato.cafc, [Validators.required]], */
      estado: [dato.estado, [Validators.required]],
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fmodificar(id: number, content: any) {
    this.estado = 'Modificar';
    this._empresasService.obtenerPorId(id).subscribe((data) => {
      this.dato = data;
      this.fformulario(this.dato);
      this._modalService.open(content);
    });
  }

  faceptar(): void {
    this.enviando = true;
    this.dato.nit = this.formulario.value.nit;
    this.dato.razonsocial = this.formulario.value.razonsocial;
    this.dato.codigosistema = this.formulario.value.codigosistema;
    this.dato.token = this.formulario.value.token;
    this.dato.fechahora = this.formulario.value.fechahora;
    this.dato.ambiente = this.formulario.value.ambiente;
    this.dato.modalidad = this.formulario.value.modalidad;
    this.dato.ruta = this.formulario.value.ruta;
    /* this.dato.cafc = this.formulario.value.cafc; */
    this.dato.estado = this.formulario.value.estado;
    if (this.estado === 'Modificar') {
      this._empresasService.modificar(this.dato).subscribe(
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
      this._empresasService.adicionar(this.dato).subscribe(
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

  fsucursales(id: number) {
    const _ruta = '/facturacion/parametros/' + id;
    this._ruta.navigateByUrl(_ruta);
  }

}
