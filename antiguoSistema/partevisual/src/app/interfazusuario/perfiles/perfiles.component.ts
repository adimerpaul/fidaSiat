import { Usuarios } from '../../modelo/usuarios';
import { UsuariosService } from '../../servicio/usuarios.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Personas } from '../../modelo/personas';
import { PersonasService } from '../../servicio/personas.service';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { MustMatch } from 'src/app/configuracion/application';

@Component({
  selector: 'app-perfiles',
  templateUrl: './perfiles.component.html',
  styleUrls: ['./perfiles.component.css']
})
export class PerfilesComponent implements OnInit {

  gestion: number = new Date().getFullYear();

  perfil: Personas;
  estado: string;
  archivoseleccionado: File;
  imagen: any;

  formulario: FormGroup;
  submitted = false;
  usuario: Usuarios;

  constructor(
    private _personasService: PersonasService,
    private _usuariosService: UsuariosService,
    private _modalService: NgbModal,
    private _fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.fdato();
  }

  fdato() {
    this._personasService.perfil().subscribe(data=>{
      this.perfil = data;
    });
  }

  fcancelar() {
    this._modalService.dismissAll();
  }

  fcambiarclave(contenido: any){
    this.estado = 'Actualizar';
    this.crearformulario();
    this._modalService.open(contenido);
  }

  crearformulario() {
    this.formulario = this._fb.group ({
      cclave: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9]*'), Validators.minLength(8)]],
      clave: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9]*'), Validators.minLength(8)]]
    },
    {
      validator: MustMatch('clave', 'cclave')
    });
  }

  get f() {
    return this.formulario.controls;
  }

  faceptarcambiarclave() {
    this.submitted = true;
    this.usuario = new Usuarios();
    this.usuario.clave = this.formulario.value.clave;
    this._usuariosService.cambiarclave(this.usuario).subscribe( data=>{
      swal.fire(
        "Dato modificado",
        "Dato modificado con exito",
        "success"
      );
      this._modalService.dismissAll();
    })
  }

}
