import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ArtefactosModule } from '../artefactos/artefactos.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModulosRoutingModule } from './modulos-routing.module';
import { EscritorioComponent } from './escritorio/escritorio.component';
import { PerfilesComponent } from './perfiles/perfiles.component';
import { E401Component } from './e401/e401.component';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { NgbPopoverModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { ParametrosfacComponent } from './facturacion/parametrosfac/parametrosfac.component';
import { EtapaunoComponent } from './facturacion/etapauno/etapauno.component';
import { EtapadosComponent } from './facturacion/etapados/etapados.component';
import { EtapatresComponent } from './facturacion/etapatres/etapatres.component';
import { EtapacuatroComponent } from './facturacion/etapacuatro/etapacuatro.component';
import { EtapacincoComponent } from './facturacion/etapacinco/etapacinco.component';
import { EtapaseisComponent } from './facturacion/etapaseis/etapaseis.component';
import { EtapasieteComponent } from './facturacion/etapasiete/etapasiete.component';
import { SucursalesComponent } from './facturacion/parametrosfac/sucursales/sucursales.component';
import { PuntosventasComponent } from './facturacion/parametrosfac/sucursales/puntosventas/puntosventas.component';
import { GruposComponent } from './grupos/grupos.component';
import { ProductosComponent } from './productos/productos.component';
import { EtapaochoComponent } from './facturacion/etapaocho/etapaocho.component';
import { DescuentosComponent } from './descuentos/descuentos.component';
import { VentasComponent } from './ventas/ventas.component';
import { ActividadessinComponent } from './actividadessin/actividadessin.component';
import { LeyendassinComponent } from './leyendassin/leyendassin.component';
import { ProductossinComponent } from './productossin/productossin.component';
import { EventossignificataivossinComponent } from './eventossignificataivossin/eventossignificataivossin.component';
import { DocumentosidentidadessinComponent } from './documentosidentidadessin/documentosidentidadessin.component';
import { ClientesComponent } from './clientes/clientes.component';
import { MetodospagossinComponent } from './metodospagossin/metodospagossin.component';
import { FacturasComponent } from './facturas/facturas.component';
import { EventossignificativosComponent } from './eventossignificativos/eventossignificativos.component';
import { BitacorasComponent } from './bitacoras/bitacoras.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { NotasentregaComponent } from './notasentrega/notasentrega.component';

@NgModule({
  declarations: [EscritorioComponent, PerfilesComponent, E401Component, ParametrosfacComponent, EtapaunoComponent, EtapadosComponent, EtapatresComponent, EtapacuatroComponent, EtapacincoComponent, EtapaseisComponent, EtapasieteComponent, SucursalesComponent, PuntosventasComponent, GruposComponent, ProductosComponent, EtapaochoComponent, DescuentosComponent, VentasComponent, ActividadessinComponent, LeyendassinComponent, ProductossinComponent, EventossignificataivossinComponent, DocumentosidentidadessinComponent, ClientesComponent, MetodospagossinComponent, FacturasComponent, EventossignificativosComponent, BitacorasComponent, UsuariosComponent, NotasentregaComponent],
  imports: [
    CommonModule,
    ModulosRoutingModule,
    ArtefactosModule,
    NgbPaginationModule,
    ReactiveFormsModule,
    CKEditorModule,
    FormsModule,
    NgbPopoverModule,
    NgSelectModule
  ]
})
export class ModulosModule { }
