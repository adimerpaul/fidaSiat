import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Clientes } from 'src/app/modelo/clientes';
import { Documentosidentidadessin } from 'src/app/modelo/documentosidentidadessin';
import { ClientesService } from 'src/app/servicio/clientes.service';
import { DocumentosidentidadessinService } from 'src/app/servicio/documentosidentidadessin.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  entidad: 'Clientes';

  datos: Clientes[];
  dato: Clientes;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  enviando: boolean = false;

  documentosidentidadessin: Documentosidentidadessin[];

  constructor(
    private _clientesService: ClientesService,
    private _documentosidentidadessinService: DocumentosidentidadessinService,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
  }

  ngOnInit(): void {
    this.fdatos();
    this.fdocumentosidentidadessin();
  }

  fdocumentosidentidadessin(){
    this._documentosidentidadessinService.obtenerTodos().subscribe( data=>{
      this.documentosidentidadessin = data;
    })
  }

  fcantidad() {
    this._clientesService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._clientesService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fformulario(dato: Clientes) {
    this.formulario = this._fb.group({
      iddocumentoidentidadsin: [dato.iddocumentoidentidadsin, [Validators.required]],
      cliente: [dato.cliente, [Validators.required]],
      numerodocumento: [dato.numerodocumento, [Validators.required]],
      complemento: [dato.complemento, []],
      correoelectronico: [dato.correoelectronico, []],
      celular: [dato.celular, []],
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fadicionar(contenido: any) {
    this.estado = 'Adicionar';
    this.dato = new Clientes();
    this.fformulario(this.dato);
    this._modalService.open(contenido);
  }

  fmodificar(id: number, contenido: any) {
    this.estado = 'Modificar';
    this._clientesService.obtenerPorId(id).subscribe((data) => {
      this.dato = data;
      this.fformulario(this.dato);
      this._modalService.open(contenido);
    });
  }

  faceptar(): void {
    this.enviando = true;
    this.dato.iddocumentoidentidadsin = this.formulario.value.iddocumentoidentidadsin;
    this.dato.cliente = this.formulario.value.cliente.toUpperCase();
    this.dato.numerodocumento = this.formulario.value.numerodocumento;
    this.dato.complemento = this.formulario.value.complemento;
    this.dato.correoelectronico = this.formulario.value.correoelectronico;
    this.dato.celular = this.formulario.value.celular;
    if (this.estado === 'Modificar') {
      this._clientesService.modificar(this.dato).subscribe(
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
      this._clientesService.adicionar(this.dato).subscribe(
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
      title: 'Estás seguro borrar al cliente?',
      icon: 'warning',
      text: 'No podras revertir este proceso!',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Borrar Cliente',
    })
    .then((result) => {
      if (result.isConfirmed) {
        this._clientesService.borrarPorId(id).subscribe(
          data=> {
            this.fdatos();
            swal.fire('Borrado', 'Se realizo el borrado con exito', 'success');
          },
          error=> {
            swal.fire('Error', 'Sin Conexion', 'warning');
          }
        );
      }
    });
  }
}
