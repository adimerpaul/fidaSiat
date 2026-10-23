import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Cufds } from 'src/app/modelo/cufds';
import { Descuentos } from 'src/app/modelo/descuentos';
import { Puntosventas } from 'src/app/modelo/puntosventas';
import { Respuestas } from 'src/app/modelo/respuestas';
import { Usuarios } from 'src/app/modelo/usuarios';
import { CufdsService } from 'src/app/servicio/cufds.service';
import { DescuentosService } from 'src/app/servicio/descuentos.service';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';
import { PuntosventasService } from 'src/app/servicio/puntosventas.service';
import { UsuariosService } from 'src/app/servicio/usuarios.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-puntosventas',
  templateUrl: './puntosventas.component.html',
  styleUrls: ['./puntosventas.component.css']
})
export class PuntosventasComponent implements OnInit {

  entidad: 'Puntos ventas';

  datos: Puntosventas[];
  dato: Puntosventas;

  registro: Respuestas[];

  usuarios: Usuarios[];

  descuentos: Descuentos[];

  cufds: Cufds[];

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
    private _puntosventasService: PuntosventasService,
    private _usuariosService: UsuariosService,
    private _cufdsService: CufdsService,
    private _descuentosService: DescuentosService,
    private _pruebasSIATService: PruebasSIATService,
    private _route: ActivatedRoute,
    private _ruta: Router,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
  }

  fsucursales() {
    const _ruta = '/facturacion/parametros/1';
    this._ruta.navigateByUrl(_ruta);
  }

  ngOnInit(): void {
    this.id = this._route.snapshot.params['id'];
    this.fdatos();
    this.fusuarios();
    this.fdescuentos();
  }

  fusuarios() {
    this._usuariosService.obtenerTodosRolVendedor().subscribe((data) => {
        this.usuarios = data;
    });
  }

  fdescuentos(){
    this._descuentosService.obtenerTodos().subscribe( data=> {
      this.descuentos = data;
    })
  }


  fcantidad() {
    this._puntosventasService
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
    this._puntosventasService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad, this.id).subscribe((data) => {
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

  fformulario(dato: Puntosventas) {
    this.formulario = this._fb.group({
      idusuario: [dato.idusuario, [Validators.required]],
      numerofactura: [dato.numerofactura, [Validators.required]],
      puntoventa: [dato.puntoventa, [Validators.required]],
      estadosistema: [dato.estadosistema, [Validators.required]],
      cafc: [dato.cafc],
      idcufd: [dato.idcufd],
      nombrepuntoventa: [dato.nombrepuntoventa, [Validators.required]],
      iddescuentos: [dato.iddescuentos, []]
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fadicionar(content: any) {
    this.estado = 'Adicionar';
    this.dato = new Puntosventas();
    this.dato.estadosistema = 1;
    this.fformulario(this.dato);
    this._modalService.open(content);
  }

  fmodificar(id: number, content: any) {
    this.estado = 'Modificar';
    this._puntosventasService.obtenerPorId(id).subscribe((data) => {
      this._cufdsService.obtenerUltimos(data.idpuntoventa).subscribe(datitos=> {
        this.cufds = datitos;
      })
      this.dato = data;
      this.fformulario(this.dato);
      this._modalService.open(content);
    });
  }

  faceptar(): void {
    this.enviando = true;
    this.dato.puntoventa = this.formulario.value.puntoventa;
    this.dato.nombrepuntoventa = this.formulario.value.nombrepuntoventa;
    this.dato.idusuario = this.formulario.value.idusuario;
    this.dato.numerofactura = this.formulario.value.numerofactura;
    this.dato.cafc = this.formulario.value.cafc;
    this.dato.estadosistema = this.formulario.value.estadosistema;
    this.dato.idcufd = this.formulario.value.idcufd;
    this.dato.idsucursal = this.id;
    if (this.estado === 'Modificar') {
      this._puntosventasService.modificar(this.dato).subscribe(
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
      this._puntosventasService.adicionar(this.dato).subscribe(
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
        this._puntosventasService.borrar(id).subscribe(
          data=>{
            this.fdatos();
            swal.fire('Dato borrado', 'Dato borrado con exito', 'success');
          },
          error=>{
            swal.fire('Error', 'Tiene descuentos seleccionados', 'error');
          }
        );
      }
    });
  }

  fregistracui(punto: number) {
    console.log(punto);
    this._pruebasSIATService.registrarCUIS(punto).subscribe(
      data=> {
        this.fdatos();
        swal.fire('CUI', 'CUI obtenido con exito para el punto '+punto, 'success');
      },
      error=> {
        swal.fire('Error', 'Sucursal no registrada', 'error');
      }
    );
  }

  fregistracufd(punto: number) {
    console.log(punto);
    this._pruebasSIATService.registrarCUFDS(punto).subscribe(
      data=> {
        this.fdatos();
        swal.fire('CUFD', 'CUFD obtenido con exito para el punto '+punto, 'success');
      },
      error=>{
        swal.fire('Error', 'CUI no registrado', 'error');
      }
    );
  }

}
