import { E401Component } from './e401/e401.component';
import { GuardianGuard } from '../configuracionSeguridad/guardian.guard';
import { PerfilesComponent } from './perfiles/perfiles.component';
import { EscritorioComponent } from './escritorio/escritorio.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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

const routes: Routes = [
  {
    path: '',
    redirectTo: 'escritorio',
  },
  {
    path: 'escritorio',
    component: EscritorioComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: 'ROLE_TODOS',
    },
  },
  {
    path: 'e401',
    component: E401Component,
    canActivate: [GuardianGuard],
    data: {
      rol: 'ROLE_TODOS',
    },
  },
  {
    path: 'perfiles',
    component: PerfilesComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: 'ROLE_TODOS',
    },
  },
  {
    path: 'facturacion/parametros',
    component: ParametrosfacComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'facturacion/etapauno',
    component: EtapaunoComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapados',
    component: EtapadosComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapatres',
    component: EtapatresComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapacuatro',
    component: EtapacuatroComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapacinco',
    component: EtapacincoComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapaseis',
    component: EtapaseisComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapasiete',
    component: EtapasieteComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'facturacion/etapaocho',
    component: EtapaochoComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '17',
    },
  },
  {
    path: 'bitacoras',
    component: BitacorasComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'usuarios',
    component: UsuariosComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'facturacion/parametros/:id',
    component: SucursalesComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'facturacion/parametros/sucursales/:id',
    component: PuntosventasComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'grupos',
    component: GruposComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'descuentos',
    component: DescuentosComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'productos',
    component: ProductosComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'actividadessin',
    component: ActividadessinComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'leyendassin',
    component: LeyendassinComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'productossin',
    component: ProductossinComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'eventossignificativossin',
    component: EventossignificataivossinComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'documentosidentidadessin',
    component: DocumentosidentidadessinComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'metodospagossin',
    component: MetodospagossinComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '20',
    },
  },
  {
    path: 'ventas',
    component: VentasComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '21',
    },
  },
  {
    path: 'facturas',
    component: FacturasComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '21',
    },
  },
  {
    path: 'clientes',
    component: ClientesComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: ['20', '21', '22'],
    },
  },
  {
    path: 'eventossignificativos',
    component: EventossignificativosComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '21',
    },
  },
  {
    path: 'notasentrega',
    component: NotasentregaComponent,
    canActivate: [GuardianGuard],
    data: {
      rol: '22',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ModulosRoutingModule {}
