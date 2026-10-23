import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Grupos } from 'src/app/modelo/grupos';
import { Productos } from 'src/app/modelo/productos';
import { Productossin } from 'src/app/modelo/productossin';
import { Unidadesmedidas } from 'src/app/modelo/unidadesmedidas';
import { GruposService } from 'src/app/servicio/grupos.service';
import { ProductosService } from 'src/app/servicio/productos.service';
import { ProductossinService } from 'src/app/servicio/productossin.service';
import { UnidadesmedidasService } from 'src/app/servicio/unidadesmedidas.service';
import { environment } from 'src/environments/environment';
import swal from 'sweetalert2';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  rutaProducto = `${environment.RUTA}/api/productos/descargarProducto/`;

  archivoSeleccionado: File;
  refmodal1: any;
  imageSrc: string;
  id_producto: number;
  enlaceproducto: string;


  entidad: 'Productos';

  datos: Productos[];
  dato: Productos;

  grupos: Grupos[];
  unidadesmedidas: Unidadesmedidas[];
  productossin: Productossin[];

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  enviando: boolean = false;

  constructor(
    private _gruposService: GruposService,
    private _unidadesmedidasService: UnidadesmedidasService,
    private _productossinService: ProductossinService,
    private _productosService: ProductosService,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'lg';
  }

  ngOnInit(): void {
    this.fgrupos();
    this.funidadesmedidas();
    this.fproductossin();
    this.fdatos();
  }

  fgrupos() {
    this._gruposService.obtenerTodos().subscribe((data) => {
        this.grupos = data;
      });
  }

  fproductossin() {
    this._productossinService.obtenerTodos().subscribe((data) => {
        this.productossin = data;
      });
  }

  funidadesmedidas() {
    this._unidadesmedidasService.obtenerTodos().subscribe((data) => {
        this.unidadesmedidas = data;
      });
  }

  fcantidad() {
    this._productosService
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
    this._productosService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fformulario(dato: Productos) {
    this.formulario = this._fb.group({
      idproductosin: [dato.idproductosin, [Validators.required]],
      idgrupo: [dato.idgrupo, [Validators.required]],
      idunidadmedida: [dato.idunidadmedida, [Validators.required]],
      producto: [dato.producto, [Validators.required]],
      cantidadcaja: [dato.cantidadcaja, [Validators.required]],
      preciounitario: [dato.preciounitario, [Validators.required]],
      habilitado: [dato.habilitado, [Validators.required]],
      codigocorto: [dato.codigocorto, [Validators.required]],
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fadicionar(content: any) {
    this.estado = 'Adicionar';
    this.dato = new Productos();
    this.fformulario(this.dato);
    this._modalService.open(content);
  }

  fmodificar(id: number, content: any) {
    this.estado = 'Modificar';
    this._productosService.obtenerPorId(id).subscribe((data) => {
      this.dato = data;
      console.log(this.dato);

      this.fformulario(this.dato);
      this._modalService.open(content);
    });
  }

  faceptar(): void {
    this.enviando = true;
    this.dato.idproductosin = this.formulario.value.idproductosin;
    this.dato.idgrupo = this.formulario.value.idgrupo;
    this.dato.idunidadmedida = this.formulario.value.idunidadmedida;
    this.dato.producto = this.formulario.value.producto;
    this.dato.cantidadcaja = this.formulario.value.cantidadcaja;
    this.dato.preciounitario = this.formulario.value.preciounitario;
    this.dato.habilitado = this.formulario.value.habilitado;
    this.dato.codigocorto = this.formulario.value.codigocorto;

    if (this.estado === 'Modificar') {
      this._productosService.modificar(this.dato).subscribe(
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
      this._productosService.adicionar(this.dato).subscribe(
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

  fseleccionarArchivo(event) {
    const reader = new FileReader();
    this.archivoSeleccionado = event.target.files[0];
    if (event.target.files[0] && event.target.files.length) {
      const file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageSrc = reader.result as string;
      };
    }

  }

  ffotografia(id: number, enlaceproducto: string, contenido: any) {
    this.imageSrc = '';
    this.id_producto = id;
    this.enlaceproducto = enlaceproducto;
    this._modalService.open(contenido);
  }

  fsubirArchivo(id: number) {
    this._productosService.subirImagen(this.archivoSeleccionado, id).subscribe( data => {
      this.fdatos();
      this._modalService.dismissAll();
      swal.fire('Imagen', 'Imagen grabada con exito', 'success');
    })
  }

}
