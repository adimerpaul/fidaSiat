import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Anulacionessin } from 'src/app/modelo/anulacionessin';
import { Facturas } from 'src/app/modelo/facturas';
import { AnulacionessinService } from 'src/app/servicio/anulacionessin.service';
import { FacturasService } from 'src/app/servicio/facturas.service';
import { PruebasSIATService } from 'src/app/servicio/pruebas-siat.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html',
  styleUrls: ['./facturas.component.css']
})
export class FacturasComponent implements OnInit {

  entidad: 'Facturas';

  datos: Facturas[];
  dato: Facturas;

  anulacionessin: Anulacionessin[];

  idfactura: number;
  idanulacionsin: number;
  motivoanulacion: string;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  formulario: FormGroup;
  enviando: boolean = false;

  fecha: string;

  constructor(
    private _facturasService: FacturasService,
    private _anulacionessinService: AnulacionessinService,
    private _pruebasSIATService: PruebasSIATService,
    private _fb: FormBuilder,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
    const hoy = new Date();
    this.fecha = hoy.toISOString().substring(0, 10);
  }

  ngOnInit(): void {
    this.fdatos();
    this.fanulacionessin();
  }

  fanulacionessin() {
    this._anulacionessinService.obtenerTodos().subscribe( data=> {
      this.anulacionessin = data;
    })
  }

  fcantidad() {
    this._facturasService.obtenerCantidadPorTermino(this.buscar).subscribe((data) => {
      this.total = data;
    });
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.pagina = 0;
    this.fdatos();
  }

  fdatos() {
    this._facturasService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fformulario() {
    this.formulario = this._fb.group({
      idanulacionsin: [1, [Validators.required]],
      motivoanulacion: ['', [Validators.required]],
    });
  }

  get f() {
    return this.formulario.controls;
  }

  fanular(id: number, content: any) {
    this.estado = 'Anular';
    this.idfactura = id;
    this.fformulario();
    this._modalService.open(content);
  };

  faceptar() {
    this.enviando = true;
    this.motivoanulacion = this.formulario.value.motivoanulacion.toUpperCase();
    this.idanulacionsin = this.formulario.value.idanulacionsin;

    swal
    .fire({
      title: 'Estás seguro?',
      icon: 'warning',
      text: 'de anular la Factura ...',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Anular',
    })
    .then((result) => {
      if (result.value) {
        this._pruebasSIATService.anularFactura(this.idfactura, this.idanulacionsin, this.motivoanulacion).subscribe( data=> {
          this.fdatos();
          this._modalService.dismissAll();
        });
      }
    });
  }

  fcancelar() {
    this._modalService.dismissAll();
  }

  freversionFactura(idfactura: number) {

    /* swal
    .fire({
      title: 'Estás seguro?',
      icon: 'warning',
      text: 'de revertir la Factura ...',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Anular',
    })
    .then((result) => {
      if (result.value) {
        this._pruebasSIATService.anularFactura(idfactura).subscribe( data=> {
          this.fdatos();
        });
      }
    }); */
  }

  ffacturaPDF(id: number) {
    this._facturasService.facturaPDF(id).subscribe( data=> {
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

  fverificarfacturaPDF(id: number) {
    this._facturasService.obtenerPorId(id).subscribe( data=> {
      const _ruta = 'https://siat.impuestos.gob.bo/consulta/QR?nit=120987022&cuf='+data.cuf+'&numero='+data.numerofactura+'&t=1';
      window.open(_ruta, '_blank');
    })

  }

  freporteValidasPDF(fecha: Date){
    this._facturasService.reporteValidosPDF(fecha).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reporte.pdf";
      a.click();
      return url;
    })
  }

  freporteAnuladosPDF(fecha: Date){
    this._facturasService.reporteAnuladosPDF(fecha).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reporte.pdf";
      a.click();
      return url;
    })
  }

  freporteRechazadasAnuladosPDF(fecha: Date){
    this._facturasService.reporteREchazadosAnuladosPDF(fecha).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reporte.pdf";
      a.click();
      return url;
    })
  }

  freporteOtrosPDF(fecha: Date){
    this._facturasService.reporteOtrosPDF(fecha).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reporte.pdf";
      a.click();
      return url;
    })
  }

  freporteGeneralPDF(fecha: Date){
    this._facturasService.reporteGeneralPDF(fecha).subscribe( data=> {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement("a");
      a.setAttribute("style", "display:none;");
      document.body.appendChild(a);
      a.href = url;
      a.download = "Reporte.pdf";
      a.click();
      return url;
    })
  }


}
