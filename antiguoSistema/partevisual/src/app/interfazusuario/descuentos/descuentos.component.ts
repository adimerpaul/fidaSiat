import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Clientes } from 'src/app/modelo/clientes';
import { Descuentos } from 'src/app/modelo/descuentos';
import { Grupos } from 'src/app/modelo/grupos';
import { Productos } from 'src/app/modelo/productos';
import { ClientesService } from 'src/app/servicio/clientes.service';
import { DescuentosService } from 'src/app/servicio/descuentos.service';
import { GruposService } from 'src/app/servicio/grupos.service';
import { ProductosService } from 'src/app/servicio/productos.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-descuentos',
  templateUrl: './descuentos.component.html',
  styleUrls: ['./descuentos.component.css']
})
export class DescuentosComponent implements OnInit {

  entidad: 'Descuentos';

  datos: Descuentos[];
  dato: Descuentos;

  productos: Productos[];
  grupos: Grupos[];
  cliente: Clientes = new Clientes();

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  enviando: boolean = false;

  constructor(
    private _descuentosService: DescuentosService,
    private _gruposService: GruposService,
    private _productosService: ProductosService,
    private _clientesService: ClientesService,
    private _mensajes: ToastrService,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
  }

  ngOnInit(): void {
    this.fgrupos();
    this.fproductos();
    this.fdatos();
  }

  fcantidad() {
    this._descuentosService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._descuentosService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
      this.fcantidad();
      this.datos = data;
    });
  }

  fgrupos() {
    this._gruposService.obtenerTodos().subscribe((data) => {
      this.grupos = data;
    });
  }

  fproductos() {
    this._productosService.obtenerTodos().subscribe((data) => {
      this.productos = data;
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

  fformulario(dato: Descuentos) {
    this.formulario = this._fb.group({
      idcliente: [dato.idcliente, [Validators.required]],
      idgrupo: [dato.idgrupo, [Validators.required]],
      idproducto: [dato.idproducto, [Validators.required]],
      descuento: [dato.descuento, [Validators.required]],
      fecha: [dato.fecha, [Validators.required]],
      cantidadinicial: [dato.cantidadinicial, [Validators.required]],
      cantidadfinal: [dato.cantidadfinal, [Validators.required]],
      porcentaje: [dato.porcentaje, [Validators.required, Validators.min(0.01), Validators.max(0.20)]],
      opcion: [dato.opcion, [Validators.required]]
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fadicionar(content: any) {
    this.estado = 'Adicionar';
    this.dato = new Descuentos();
    this.fformulario(this.dato);
    this._modalService.open(content);
  }

  fmodificar(id: number, content: any) {
    this.estado = 'Modificar';
    this._descuentosService.obtenerPorId(id).subscribe((data) => {
      this.dato = data;
      this.fbuscarCliente(this.dato.numerodocumento);
      this.fformulario(this.dato);
      this._modalService.open(content);
    });
  }

  faceptar(): void {
    this.enviando = true;
    this.dato.descuento = this.formulario.value.descuento.toUpperCase();
    this.dato.fecha = this.formulario.value.fecha;
    this.dato.cantidadinicial = this.formulario.value.cantidadinicial;
    this.dato.cantidadfinal = this.formulario.value.cantidadfinal;
    this.dato.porcentaje = this.formulario.value.porcentaje;
    this.dato.idgrupo = this.formulario.value.idgrupo;
    this.dato.idproducto = this.formulario.value.idproducto;
    this.dato.idcliente = this.formulario.value.idcliente;
    this.dato.opcion = this.formulario.value.opcion;
    if (this.estado === 'Modificar') {
      this._descuentosService.modificar(this.dato).subscribe(
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
      this._descuentosService.adicionar(this.dato).subscribe(
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
        text: 'No podrás revertir el borrado de este dato!',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Borrar',
      })
      .then((result) => {
        if (result.value) {
          this._descuentosService.borrar(id).subscribe( data=> {
            this.fdatos();
            swal.fire('Dato borrado', 'Dato borrado con exito', 'success');
          },
          (error) => {
            swal.fire('Error', 'Existen una dependecia', 'error');
          })
        }
      });
  }

  fbuscarCliente(buscar: string){
    this._clientesService.buscarPorDocumentoResgistradoImpuestos(buscar).subscribe(
      data=> {
        this.cliente = data;
        if (data!=null) {
          this.formulario.controls['idcliente'].setValue(data.idcliente);
          this._mensajes.success('Se encuentra registrado el cliente: '+data.cliente, 'Encontrado !!!');
        } else {
          this.formulario.controls['idcliente'].setValue('');
          this._mensajes.warning('No se encuentra registrado el cliente', 'No encontrado !!!');
        }
      }
    );
  }

}
