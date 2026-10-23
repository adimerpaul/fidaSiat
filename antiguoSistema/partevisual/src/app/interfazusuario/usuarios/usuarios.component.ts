import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Personas } from 'src/app/modelo/personas';
import { Roles } from 'src/app/modelo/roles';
import { Usuarios } from 'src/app/modelo/usuarios';
import { PersonasService } from 'src/app/servicio/personas.service';
import { RolesService } from 'src/app/servicio/roles.service';
import { UsuariosService } from 'src/app/servicio/usuarios.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  entidad: 'Usuarios';

  datos: Usuarios[];
  dato: Usuarios;
  roles: Roles[];
  persona: Personas;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  submitted: boolean = false;

  constructor(
    private _ruta: Router,
    private _usuariosService: UsuariosService,
    private _personasService: PersonasService,
    private _rolesService: RolesService,
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
    this.froles();
  }

  fbuscarpersona(termino: string) {
    this._personasService.buscarPersona(termino).subscribe( data=> {
      this.persona = data;
      this.formulario.controls['primerapellido'].setValue(data.primerapellido);
      this.formulario.controls['segundoapellido'].setValue(data.segundoapellido);
      this.formulario.controls['primernombre'].setValue(data.primernombre);
      this.formulario.controls['segundonombre'].setValue(data.segundonombre);
      this.formulario.controls['dip'].setValue(data.dip);
    })
  }


  froles() {
    this._rolesService.obtenerTodos().subscribe((data) => {
      this.roles = data;
    });
  }

  fcantidad() {
    this._usuariosService.cantidadBuscarPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._usuariosService.buscarPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fformulario(dato: Usuarios) {
    this.formulario = this._fb.group({
      primerapellido: [dato.primerapellido, [Validators.required]],
      segundoapellido: [dato.segundoapellido, []],
      primernombre: [dato.primernombre, [Validators.required]],
      segundonombre: [dato.segundonombre, []],
      dip: [dato.dip, [Validators.required]],
      usuario: [dato.usuario, [Validators.required]],
      idroles: [dato.idroles, [Validators.required]],
      habilitado: [dato.habilitado, [Validators.required]],
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fadicionar(content: any) {
    this.persona = null;
    this.estado = 'Adicionar';
    this.dato = new Usuarios();
    this.dato.segundoapellido=' ';
    this.dato.segundonombre=' ';
    this.fformulario(this.dato);
    this._modalService.open(content);
  }

  fmodificar(id: number, content: any) {
    this.estado = 'Modificar';
    this._usuariosService.buscarPorId(id).subscribe((data) => {
      this.dato = data;
      this.fbuscarpersona(data.dip);
      this.fformulario(this.dato);
      this._modalService.open(content);
    });
  }

  faceptar(): void {
    this.submitted = true;
    this.dato.primerapellido = this.formulario.value.primerapellido.toUpperCase();
    this.dato.segundoapellido = this.formulario.value.segundoapellido.toUpperCase();
    this.dato.primernombre = this.formulario.value.primernombre.toUpperCase();
    this.dato.segundonombre = this.formulario.value.segundonombre.toUpperCase();
    this.dato.dip = this.formulario.value.dip;
    this.dato.usuario = this.formulario.value.usuario;
    this.dato.idroles = this.formulario.value.idroles;
    this.dato.habilitado = this.formulario.value.habilitado;
    if (this.estado === 'Modificar') {
      this._usuariosService.modificar(this.dato).subscribe(
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
      this._usuariosService.adicionar(this.dato).subscribe(
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

  fgeneraPDF() {
    this._usuariosService.generaPDF(this.buscar).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reportes.pdf";
      a.click();
      return url;
    })
  }

  fgeneraXLS() {
    this._usuariosService.generaXLS(this.buscar).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reportes.xlsx";
      a.click();
      return url;
    })
  }

  finicializarclave(id: number) {
    this._usuariosService.inicializaclave(id).subscribe( data=> {
      swal.fire('Dato inicializado', 'Clave inicializado con exito', 'success');
    })
  }


}
