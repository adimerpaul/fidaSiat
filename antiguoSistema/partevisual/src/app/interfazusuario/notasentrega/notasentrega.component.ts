import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Facturas } from 'src/app/modelo/facturas';
import { Kardex } from 'src/app/modelo/kardex';
import { Notasentrega } from 'src/app/modelo/notasentrega';
import { FacturasService } from 'src/app/servicio/facturas.service';
import { KardexService } from 'src/app/servicio/kardex.service';
import { NotasentregaService } from 'src/app/servicio/notasentrega.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-notasentrega',
  templateUrl: './notasentrega.component.html',
  styleUrls: ['./notasentrega.component.css']
})
export class NotasentregaComponent implements OnInit {

  entidad: 'Notasentrega';

  datosfacturas: Facturas[];

  datos: Notasentrega[];
  dato: Notasentrega;
  kardexes: Kardex[];

  buscar: string = '';

  constructor(
    private _notasentregaService: NotasentregaService,
    private _kardexService: KardexService,
    private _facturasServicio: FacturasService,
    private _modalService: NgbModal,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'lg';
  }

  ngOnInit(): void {
    this.fdatos();
    this.fNotasfacturas();
  }

  fbuscar(buscar: string) {
    this.buscar = buscar;
    this.fdatos();
  }

  limpiar() {
    this.buscar = '';
    this.fdatos();
  }

  fdatos() {
    this._notasentregaService.obtenerNotasentrega(this.buscar).subscribe((data) => {
      this.datos = data;
    });
  }

  fNotasfacturas() {
    this._facturasServicio.obtenerPorNotasPedido().subscribe((data) => {
      this.datosfacturas = data;
    });
  }

  fmigrarFactura(id_pedido: number) {

    swal
    .fire({
      title: 'Estás seguro emitir de la Factura?',
      icon: 'warning',
      text: 'No podras revertir este proceso!',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Emitir Factura',
    })
    .then((result) => {
      if (result.isConfirmed) {
        this._notasentregaService.migrarFactura(id_pedido).subscribe( data=>{
          this._facturasServicio.obtenerPorId(data).subscribe( datatito=> {
            if (datatito.codigo===908 || datatito.codigo===0 || datatito.codigo===-1) {
              this.ffacturaPDF(data);
              swal.fire('Pagos', 'Se realizo el pago con exito', 'success');
            } else {
              swal.fire('Factura', 'Factura incorrecta', 'warning');
            }
            this.fNotasfacturas();
            this.fdatos();
          },
          error=>{
            swal.fire('Error', 'Sin Conexion o ya se ha emitido la factura', 'warning');
          });
        });
      }
    });
  }

  ffacturaPDF(id: number) {
    this._notasentregaService.facturaPDF(id).subscribe( data=> {
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
    this._facturasServicio.obtenerPorId(id).subscribe( data=> {
      const _ruta = 'https://siat.impuestos.gob.bo/consulta/QR?nit=120987022&cuf='+data.cuf+'&numero='+data.numerofactura+'&t=2';
      window.open(_ruta, '_blank');
    })

  }

  fmostrarNotaentrega(id: number, contenido: any) {
    this._notasentregaService.obtenerPorId(id).subscribe( dato=> {
      this.dato = dato;
      this._kardexService.obtenerporpedido(dato.id_pedido).subscribe( kardexcito => {
        this.kardexes = kardexcito;
        this._modalService.open(contenido);
      })
    });
  }

  fcancelar() {
    this._modalService.dismissAll();
  }


}
