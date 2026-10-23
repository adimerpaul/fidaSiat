import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { Clientes } from 'src/app/modelo/clientes';
import { Detalles } from 'src/app/modelo/detalles';
import { Documentosidentidadessin } from 'src/app/modelo/documentosidentidadessin';
import { Facturas } from 'src/app/modelo/facturas';
import { Grupos } from 'src/app/modelo/grupos';
import { Metodospagossin } from 'src/app/modelo/metodospagossin';
import { Productos } from 'src/app/modelo/productos';
import { ClientesService } from 'src/app/servicio/clientes.service';
import { DocumentosidentidadessinService } from 'src/app/servicio/documentosidentidadessin.service';
import { FacturasService } from 'src/app/servicio/facturas.service';
import { GruposService } from 'src/app/servicio/grupos.service';
import { MetodospagossinService } from 'src/app/servicio/metodospagossin.service';
import { ProductosService } from 'src/app/servicio/productos.service';
import { environment } from 'src/environments/environment';
import swal from 'sweetalert2';

@Component({
  selector: 'app-ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css']
})
export class VentasComponent implements OnInit {

  rutaProducto = `${environment.RUTA}/api/productos/descargarProducto/`;

  grupos: Grupos[];
  documentosidentidadessin: Documentosidentidadessin[];
  metodospagossin: Metodospagossin[];

  productospanel1: Productos[];

  productospanel2: Productos[];

  formularioventa: FormGroup;
  enviado: boolean = false;

  formulariocliente: FormGroup;
  enviadocliente: boolean = false;

  formulariocantidad: FormGroup;
  enviadocantidad: boolean = false;

  facturas: Facturas[];
  factura: Facturas;
  detalle: Detalles;
  producto: Productos;
  idindex: number;
  cantidad: number;
  descuento: number;

  estadoclientes: String;

  datocliente: Clientes;

  datoproducto: Productos;

  descuentosgalletas: number = -1;
  descuentosfideos: number = -1;
  descuentosbalanceados: number = -1;
  descuentossurtidas: number = -1;

  seleccionDescuentoGalleta = 0;
  seleccionDescuentoFideo = 0;
  seleccionDescuentoBalanceado = 0;
  seleccionDescuentoSurtidas = 0;

  numerosgalletas: number[];
  numerosfideos: number[];
  numerosbalanceados: number[];
  numerossurtidas: number[];

  constructor(
    private _gruposService: GruposService,
    private _productosService: ProductosService,
    private _documentosidentidadessinService: DocumentosidentidadessinService,
    private _metodospagossinService: MetodospagossinService,
    private _clientesService: ClientesService,
    private _facturasServicio: FacturasService,
    private _fb: FormBuilder,
    private _mensajes: ToastrService,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'sm';
  }

  obtenerRutaImagen(enlaceProducto: string): string {
    return this.rutaProducto + enlaceProducto;
  }

  ffacturas(){
    this._facturasServicio.obtenerPorTermino('', 0, 3).subscribe( data=> {
      this.facturas = data;
    })
  }

  ngOnInit(): void {
    this.ffacturas();
    this.fgrupos();
    this.fdocumentosidentidadessin();
    this.fmetodospagossin();
    this.fnuevopedido();
  }

  fdocumentosidentidadessin(){
    this._documentosidentidadessinService.obtenerTodos().subscribe( data=>{
      this.documentosidentidadessin = data;
    })
  }

  fmetodospagossin(){
    this._metodospagossinService.obtenerTodos().subscribe( data=>{
      this.metodospagossin = data;
    })
  }

  fgrupos() {
    this._gruposService.obtenerTodosPorTipo(1).subscribe((data) => {
      this.grupos = data;
      this.fseleccionar(this.grupos[0].idgrupo);
    });
  }

  fcantidad(id: number) {
    this._productosService.cantidadPorIdgrupo(id).subscribe( data=> {
      this.cantidad = data;
      if (this.cantidad%2==0) {
        this.fseleccionarpanel1(id, this.cantidad/2);
        this.fseleccionarpanel2(id, this.cantidad/2);
      } else {
        this.fseleccionarpanel1(id, Math.round(this.cantidad/2));
        this.fseleccionarpanel2(id, Math.round(this.cantidad/2));
      }
    })
  }

  fseleccionar(id: number) {
    this.fcantidad(id);
  }

  fseleccionarpanel1(id: number, cantidad: number) {
    this._productosService.obtenerPorIdgrupoConPaginacion(id, 1, cantidad).subscribe((data) => {
      this.productospanel1 = data;
    });
  }

  fseleccionarpanel2(id: number, cantidad: number) {
    this._productosService.obtenerPorIdgrupoConPaginacion(id, 2, cantidad).subscribe((data) => {
      this.productospanel2 = data;
    });
  }

  fformularioclientes(dato: Clientes) {
    this.formulariocliente = this._fb.group({
      iddocumentoidentidadsin: [dato.iddocumentoidentidadsin, [Validators.required]],
      cliente: [dato.cliente, [Validators.required]],
      numerodocumento: [dato.numerodocumento, [Validators.required]],
      complemento: [dato.complemento, []],
      correoelectronico: [dato.correoelectronico, []],
      celular: [dato.celular, []],
    });
  }

  get g() {
    return this.formulariocliente.controls;
  }

  fnuevocliente(contenido: any) {
    this.estadoclientes = 'Adicionar';
    this.datocliente = new Clientes();
    this.datocliente.complemento = '';
    this.datocliente.correoelectronico = '';
    this.datocliente.celular = '';
    this.fformularioclientes(this.datocliente);
    this._modalService.open(contenido, {size: 'modal-lg'});
  }

  faceptarcliente(){
    this.enviadocliente = true;
    this.datocliente.iddocumentoidentidadsin = this.formulariocliente.value.iddocumentoidentidadsin;
    this.datocliente.cliente = this.formulariocliente.value.cliente.toUpperCase();
    this.datocliente.numerodocumento = this.formulariocliente.value.numerodocumento;
    this.datocliente.complemento = this.formulariocliente.value.complemento;
    this.datocliente.correoelectronico = this.formulariocliente.value.correoelectronico;
    this.datocliente.celular = this.formulariocliente.value.celular;
    this._clientesService.adicionar(this.datocliente).subscribe(
      (data) => {
        this._modalService.dismissAll();
        swal.fire('Dato adicionado', 'Dato adicionado con exito', 'success');
        this.fbuscarCliente(this.datocliente.numerodocumento);
      },
      (error) => {
        swal.fire('Error', 'Dato duplicado', 'error');
      }
    );
  }

  fformularioventa(dato: Facturas) {
    this.formularioventa = this._fb.group({
      cliente: [dato.cliente, [Validators.required]],
      numerodocumento: [dato.numerodocumento, [Validators.required]],
      complemento: [dato.complemento, []],
      iddocumentoidentidadsin: [dato.iddocumentoidentidadsin, [Validators.required]],
      idmetodopagosin: [dato.idmetodopagosin, [Validators.required]],
      total: [dato.total, [Validators.required]],
      descuento: [dato.descuento, [Validators.required]],
      efectivo: [dato.efectivo, [Validators.required]],
      cambio: [dato.cambio, [Validators.required]],
      formapago: [dato.formapago, [Validators.required]],
      distribucion: [dato.distribucion, [Validators.required]],
    });
  }

  get f() {
    return this.formularioventa.controls;
  }

  fnuevopedido() {
    if (this.factura) {
      swal
    .fire({
      title: 'Estás seguro?',
      icon: 'warning',
      text: 'No podrás recuperar lo seleccionado!',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Nuevo pedido',
    })
    .then((result) => {
      if (result.value) {
        this.factura = new Facturas();
        this.factura.detalles = [];
        this.factura.formapago = true;
        this.factura.iddocumentoidentidadsin = 1;
        this.factura.idmetodopagosin = 67;
        this.factura.distribucion = true;
        this.factura.total = 0;
        this.factura.descuento = 0;
        this.factura.efectivo = 0;
        this.factura.cambio = 0;
        this.datocliente = null;
        this.fformularioventa(this.factura);
      }
    });
    } else {
      this.factura = new Facturas();
      this.factura.detalles = [];
      this.factura.formapago = true;
      this.factura.iddocumentoidentidadsin = 1;
      this.factura.idmetodopagosin = 67;
      this.factura.distribucion = true;
      this.factura.total = 0;
      this.factura.efectivo = 0;
      this.factura.descuento = 0;
      this.factura.cambio = 0;
      this.datocliente = null;
      this.fformularioventa(this.factura);
    }

  }

  fproductos(contenido: any) {
    if (this.datocliente==null) {
      this._mensajes.warning('Debe registrar primero al Cliente', 'Cliente no registrado !!!');
    } else {
      this._modalService.open(contenido,  {size: 'modal-md'});
    }
  }

  fbuscarproducto(codigocorto: number) {
    this._productosService.obtenerPorCodigocorto(codigocorto).subscribe( data=> {
      this.datoproducto = data;
    })
  }

  fprocesardescuentosseleccionados(galleta: string, fideo: string, balanceado: string, surtidas: string) {

    if (galleta=='') { galleta='0'};
    if (fideo=='') { fideo='0'};
    if (balanceado=='') { balanceado='0'};
    if (surtidas=='') { surtidas='0'};

    const observables = this.factura.detalles.map((datoproud, index) => {
      return this._facturasServicio.obtenerDescuentos(datoproud.idproducto, this.factura.detalles, this.datocliente.idcliente).pipe(
        map(descuen => {
          let detalle = {
            preciounitario: datoproud.preciounitario,
            cantidad: datoproud.cantidad,
            descuento: 0,
            subtotal: 0,
          };

          if (datoproud.idgrupo==1) {
            descuen = parseInt(galleta);
          }

          if (datoproud.idgrupo==2) {
            descuen = parseInt(fideo);
          }

          if (datoproud.idgrupo==3) {
            descuen = parseInt(balanceado);
          }

          if (datoproud.idgrupo==6) {
            descuen = parseInt(surtidas);
          }

          detalle.descuento = parseFloat((detalle.preciounitario * detalle.cantidad).toFixed(2)) * parseFloat((descuen / 100).toFixed(2));
          detalle.subtotal = parseFloat((detalle.preciounitario * detalle.cantidad).toFixed(2));

          this.factura.detalles[index] = {...datoproud, descuento: detalle.descuento,subtotal: detalle.subtotal};
          return this.factura.detalles[index];
        })
      );
    });
    forkJoin(observables).subscribe(detallesActualizadosArray => {
      this.factura.total = this.factura.detalles.reduce((sum, current) => sum + current.subtotal, 0);
      this.factura.descuento = this.factura.detalles.reduce((sum, current) => sum + current.descuento, 0);
    });
  }

  fprocesardescuentos() {
    if (this.factura.detalles.length==0) {
      this.factura.total = 0;
      this.factura.descuento = 0;
    }
    const observables = this.factura.detalles.map((datoproud, index) => {
      this.numerosgalletas=[];
      this.descuentosgalletas = -1;
      this.numerosfideos=[];
      this.descuentosfideos=-1;
      this.numerosbalanceados=[];
      this.descuentosbalanceados=-1;
      this.numerossurtidas=[];
      this.descuentossurtidas=-1;
      return this._facturasServicio.obtenerDescuentos(datoproud.idproducto, this.factura.detalles, this.datocliente.idcliente).pipe(
        map(descuen => {
          let detalle = {
            preciounitario: datoproud.preciounitario,
            cantidad: datoproud.cantidad,
            descuento: 0,
            subtotal: 0,
          };

          if (descuen>0 && datoproud.idgrupo==1) {
            this.descuentosgalletas = descuen;
            this.numerosgalletas = Array.from({ length: this.descuentosgalletas + 1 }, (_, index) => index);
            this.seleccionDescuentoGalleta = descuen;
          }

          if (descuen>0 && datoproud.idgrupo==2) {
            this.descuentosfideos = descuen;
            this.numerosfideos = Array.from({ length: this.descuentosfideos + 1 }, (_, index) => index);
            this.seleccionDescuentoFideo = descuen;
          }

          if (descuen>0 && datoproud.idgrupo==3) {
            this.descuentosbalanceados = descuen;
            this.numerosbalanceados = Array.from({ length: this.descuentosbalanceados + 1 }, (_, index) => index);
            this.seleccionDescuentoBalanceado = descuen;
          }

          if (descuen>0 && datoproud.idgrupo==6) {
            this.descuentossurtidas = descuen;
            this.numerossurtidas = Array.from({ length: this.descuentossurtidas + 1 }, (_, index) => index);
            this.seleccionDescuentoSurtidas = descuen;
          }

          detalle.descuento = parseFloat((detalle.preciounitario * detalle.cantidad).toFixed(2)) * parseFloat((descuen / 100).toFixed(2));
          detalle.subtotal = parseFloat((detalle.preciounitario * detalle.cantidad).toFixed(2));

          this.factura.detalles[index] = {...datoproud, descuento: detalle.descuento,subtotal: detalle.subtotal};
          return this.factura.detalles[index];
        })
      );
    });
    forkJoin(observables).subscribe(detallesActualizadosArray => {
      this.factura.total = this.factura.detalles.reduce((sum, current) => sum + current.subtotal, 0);
      this.factura.descuento = this.factura.detalles.reduce((sum, current) => sum + current.descuento, 0);
    });
  }

  fadicionar(id: number) {
    this._productosService.obtenerPorId(id).subscribe((data) => {
      if (this.datocliente==null) {
        this._mensajes.warning('Debe registrar primero al Cliente', 'Cliente no registrado !!!');
      } else {
        this.detalle = new Detalles();
        this.detalle.idgrupo = data.idgrupo;
        this.detalle.idproducto = data.idproducto;
        this.detalle.producto = data.producto;
        this.detalle.preciounitario = data.preciounitario;
        this.detalle.cantidad = 1;
        let idindex = this.factura.detalles.findIndex( x => x.idproducto === data.idproducto );
        if (idindex>-1) {
          this.fincrementar(idindex);
        } else {
          this.factura.detalles.splice(this.idindex, 0, this.detalle);
        }
        this.fprocesardescuentos();
      }
    });
  }

  fadicionarproducto(id: number, cantidadpaquete: string, cantidadunidad: string) {
    this._productosService.obtenerPorId(id).subscribe((data) => {
      if (this.datocliente==null) {
        this._mensajes.warning('Debe registrar primero al Cliente', 'Cliente no registrado !!!');
      } else {
        let idindex = this.factura.detalles.findIndex( x => x.idproducto === data.idproducto );
        if (idindex>-1) {
          this.detalle = new Detalles();
          this.detalle = this.factura.detalles[idindex];
          let _cantidadpaquete:number = parseInt(cantidadpaquete);
          let _cantidadunidad:number = parseInt(cantidadunidad);
          let _cantidad:number = _cantidadpaquete*data.cantidadcaja+_cantidadunidad;
          this.detalle.cantidad = _cantidad;
          this.detalle.subtotal = this.detalle.preciounitario * this.detalle.cantidad;
          this.factura.detalles.splice(idindex, 1, this.detalle);
        } else {
          this.detalle = new Detalles();
          this.detalle.idgrupo = data.idgrupo;
          this.detalle.idproducto = data.idproducto;
          this.detalle.producto = data.producto;
          this.detalle.preciounitario = data.preciounitario;
          let _cantidadpaquete:number = parseInt(cantidadpaquete);
          let _cantidadunidad:number = parseInt(cantidadunidad);
          let _cantidad:number = _cantidadpaquete*data.cantidadcaja+_cantidadunidad;
          this.detalle.cantidad = _cantidad;
          this.factura.detalles.push(this.detalle);
        }
        this.fprocesardescuentos();
      }
    });
  }

  feliminarproducto(id: number){
    this.factura.detalles.splice(id, 1);
    this.fprocesardescuentos();
  }

  fincrementar(id: number){
    const aumentar: number = 1;
    this.detalle = new Detalles();
    this.detalle = this.factura.detalles[id];
    this.detalle.cantidad = this.detalle.cantidad + aumentar;
    this.factura.detalles.splice(id, 1, this.detalle);
    this.fprocesardescuentos();
  }

  fingresoCantidad(id: number, contenido: any){
    this.detalle = new Detalles();
    this.detalle = this.factura.detalles[id];
    this.idindex = id;
    this._modalService.open(contenido);
  }

  faceptarcantidad(cantidad: string) {
    let num: number = Number(cantidad);
    this.detalle = new Detalles();
    this.detalle = this.factura.detalles[this.idindex];
    this.detalle.cantidad = num;
    this.factura.detalles.splice(this.idindex, 1, this.detalle);
    this.fprocesardescuentos();
    this._modalService.dismissAll();
  }

  fdecrementar(id: number){
    const disminuir: number = 1;
    this.detalle = new Detalles();
    this.detalle = this.factura.detalles[id];
    this.detalle.cantidad = this.detalle.cantidad - disminuir;

    if (this.detalle.cantidad === 0) {
      this.factura.detalles.splice(id, 1);
    } else {
      this.factura.detalles.splice(id, 1, this.detalle);
    }
    this.fprocesardescuentos();
  }

  fcambioefectivo(dato: any) {
    let efectivo: number = parseFloat(dato.target.value);
    if (efectivo>this.factura.total-this.factura.descuento) {
      this.factura.cambio=efectivo-(this.factura.total-this.factura.descuento);
      this.factura.efectivo=efectivo;
    } else {
      this.factura.cambio=0;
      this.factura.efectivo=efectivo;
    }
  }

  fcambiodescuento(dato: any) {
    let descuento: number = parseFloat(dato.target.value);
    this.factura.descuento=descuento;
  }

  fbuscarCliente(buscar: string){
    this._clientesService.buscarPorDocumentoResgistradoImpuestos(buscar).subscribe(
      data=> {
        this.datocliente = data;
        if (data!=null) {
          this.formularioventa.controls['cliente'].setValue(data.cliente);
          this.formularioventa.controls['numerodocumento'].setValue(data.numerodocumento);
          this.formularioventa.controls['complemento'].setValue(data.complemento);
          this.formularioventa.controls['iddocumentoidentidadsin'].setValue(data.iddocumentoidentidadsin);

          this._mensajes.success('Se encuentra registrado el cliente: '+data.cliente, 'Encontrado !!!');
          if (data.codigoexcepcion==1) {
            swal.fire('NIT no encontrado en el SIAT', 'Ok para continuar, sino utilice la opcion de Clientes para actualizar', 'warning');
          }
        } else {
          this.formularioventa.controls['cliente'].setValue('');
          this.formularioventa.controls['numerodocumento'].setValue('');
          this.formularioventa.controls['complemento'].setValue('');
          this.formularioventa.controls['iddocumentoidentidadsin'].setValue(1);
          this._mensajes.warning('No se encuentra registrado el cliente', 'No encontrado !!!');
        }
      }
    );
  }

  fpagar() {
    this.enviado = true;
    this.factura.idcliente = this.datocliente.idcliente;
    this.factura.numerodocumento = this.formularioventa.value.numerodocumento;
    this.factura.complemento = this.formularioventa.value.complemento;
    this.factura.iddocumentoidentidadsin = this.formularioventa.value.iddocumentoidentidadsin;
    this.factura.formapago = this.formularioventa.value.formapago;
    this.factura.distribucion = this.formularioventa.value.distribucion;
    this.factura.total = this.factura.detalles.reduce((sum, current) => sum+current.subtotal, 0);
    this.factura.descuento = this.factura.detalles.reduce((sum, current) => sum+(current.descuento), 0);
    this.factura.efectivo = this.formularioventa.value.efectivo;
    this.factura.cambio = this.formularioventa.value.efectivo-(this.factura.total-this.factura.descuento);
    this._facturasServicio.adicionar(this.factura).subscribe(
      data=> {
        this._facturasServicio.obtenerPorId(data).subscribe( datatito=> {
          if (datatito.codigo===908 || datatito.codigo===9081) {
            this.ffacturaPDF(data);
            this.ffacturas();
            swal.fire('Pagos', 'Se realizo el pago con exito', 'success');
          } else {
            swal.fire('Factura', 'Factura incorrecta', 'warning');
          }
        });

      },
      error=>{
        this.ffacturas();
        swal.fire('Error', 'Sin Conexion', 'warning');
      }
    );
    this.factura = null;
    this.fnuevopedido();
  }

  fcancelar() {
    this._modalService.dismissAll();
  }

  ffacturaPDF(id: number) {
    this._facturasServicio.facturaPDF(id).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "factura.pdf";
      a.click();
      return url;
    })
  }

}
