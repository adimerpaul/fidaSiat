import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccesoService } from '../servicio/acceso.service';
import * as jwt_decode from 'jwt-decode';
import swal from 'sweetalert2';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-acceso',
  templateUrl: './acceso.component.html',
  styleUrls: ['./acceso.component.css'],
})
export class AccesoComponent implements OnInit {

  sw: number = 1;

  private usuario: string='';
  private clave: string='';

  ROLES: string = environment.ROLES;
  NOMBRE: string = environment.NOMBRE;
  AREATRABAJO: string = environment.AREATRABAJO;
  DEPARTAMENTO: string = environment.DEPARTAMENTO;
  TOKEN: string = environment.TOKEN;

  gestion: number = new Date().getFullYear();
  nombreapp: string = environment.nombreapp;
  version: string = environment.version;

  formulariousuario: FormGroup;
  formularioclave: FormGroup;
  enviandousuario = false;
  enviandoclave = false;
  error = '';
  estadousuario: boolean = false;
  estadoclave: boolean = false;

  constructor(
    private _fb: FormBuilder,
    private _ruta: Router,
    private _accesoService: AccesoService,
    private _mensajes: ToastrService
  ) {}

  ngOnInit() {
    this.fformulariousuario();
    this.fformularioclave();
  }

  fformulariousuario() {
    this.formulariousuario = this._fb.group({
      usuario: ['', Validators.required]
    });
  }

  fformularioclave() {
    this.formularioclave = this._fb.group({
      clave: ['', Validators.required],
    });
  }

  get f() {
    return this.formulariousuario.controls;
  }

  get g() {
    return this.formularioclave.controls;
  }

  faceptarusuario() {
    this.estadousuario = true;
    this.enviandousuario = true;
    this.usuario = this.f.usuario.value
    this.estadousuario = false;
    this.sw = 2;
  }

  faceptarclave() {
    this.estadoclave = true;
    this.enviandoclave = true;
    this.clave = this.g.clave.value;
    this._accesoService.acceso(this.usuario, this.clave).subscribe(
        (data) => {
          if (data) {
            const token = JSON.stringify(data);
            var decoded = jwt_decode(token);
            sessionStorage.setItem(this.ROLES, decoded['authorities']);
            sessionStorage.setItem(this.NOMBRE, decoded['nombre']);
            sessionStorage.setItem(this.AREATRABAJO, decoded['areatrabajo']);
            sessionStorage.setItem(this.DEPARTAMENTO, decoded['departamento']);
            sessionStorage.setItem(this.TOKEN, token);
            this._mensajes.success(decoded['nombre'], 'Bienvenido');
            this._ruta.navigateByUrl('/escritorio');
            this.estadoclave = false;
          }
        },
        (err) => {
          if (err.status === 0) {
            this.error = 'Error de conexión';
            this.sw = 1;
            swal.fire('Verifica tu conexión!', 'Sin conexión al sistema', 'error');
          }
          if (err.status === 400) {
            this.error = 'Credenciales incorrectas';
            this.sw = 1;
            swal.fire('Verifica tus datos!', 'Tu usuario ó contraseña son incorrectos', 'warning');
          }
          this.f.usuario.setValue('');
          this.g.clave.setValue('');
          this.estadoclave = false;
        }
      );
  }
}

