# Frontend
AIGFEC

#Etapa Uno
#Componente
entidad: 'Cuis';

  datos: Cuis[];
  dato: Cuis;

  pagina: number = 0;
  numPaginas: number = 0;
  cantidad: number = 10;
  buscar: string = '';
  total: number = 0;
  estado: string = '';

  constructor(
    private _cuisService: CuisService,
    private _pruebasSIATService: PruebasSIATService,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
    config.size = 'md';
  }

  ngOnInit(): void {
    this.fdatos();
  }

  fcantidad() {
    this._cuisService
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
    this._cuisService.obtenerPorTermino(this.buscar, this.pagina, this.cantidad).subscribe((data) => {
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

  fadicionarPuntosventas() {
    this._cuisService.adicionarPuntosventas().subscribe( data=> {
      swal.fire('Dato adicionado', 'Se adicionaron los puntos de venta con exito!!!', 'success');
      this.fdatos();
    })
  }

  fobtenerCui(id: number) {
    this._cuisService.obtenerCui(id).subscribe( data=> {
      let mensajes: string = '';
      for (let index = 0; index < data.length; index++) {
        mensajes = '<b>'+data[index].codigo+'</b> - '+data[index].descripcion+'<br>'
      }
      swal.fire('Prueba', 'Prueba realizada con los siguientes mensajes: <br>'+mensajes, 'success');
      this.fdatos();
    })
  }

  fadicionarCui(id: number) {
    this._cuisService.adicionarCui(id).subscribe( data=> {
      let mensajes: string = '';
      for (let index = 0; index < data.length; index++) {
        mensajes = '<b>'+data[index].codigo+'</b> - '+data[index].descripcion+'<br>'
      }
      swal.fire('Registro CUI', 'Prueba realizada con los siguientes mensajes: <br>'+mensajes, 'success');
      this.fdatos();
    })
  }

  fregistrarPuntoventa(id: number) {
    this._cuisService.registrarPuntoventa(id).subscribe( data=> {
      let mensajes: string = '';
      for (let index = 0; index < data.length; index++) {
        mensajes = '<b>'+data[index].codigo+'</b> - '+data[index].descripcion+'<br>'
      }
      if (mensajes==='') {
        swal.fire('Registro punto de venta', 'Se registro correctamente el Punto de venta !!!', 'success');
      } else {
        swal.fire('Registro punto de venta', 'Prueba realizada con los siguientes mensajes: <br>'+mensajes, 'success');
      }

      this.fdatos();
    })
  }

  fverificarConexion() {
    this._pruebasSIATService.verificarConexion().subscribe( data=> {
      this.respuestas =  data;
    })
  }
#Visual
<app-cabeceratitulos [titulo]="'Etapa uno'" [descripcion]="'Etapa uno de Facturación'" [icono]="'cogs'" [opcion]="'Etapa uno'">
</app-cabeceratitulos>

<div class="row">
    <div class="col-md-12">
        <div class="tile text-center">

            <div class="row">
                <div class="col-md-12 mb-2">
                    <div class="input-group justify-content-end">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary mr-2" type="button" (click)="fverificarConexion()" data-toggle="tooltip" data-placement="top" title="Agregar dato"><i class="fa fa-plus"></i> Verificar Conexion </button>
                            <button class="btn btn-primary mr-2" type="button" (click)="fadicionarPuntosventas()" data-toggle="tooltip" data-placement="top" title="Agregar dato"><i class="fa fa-plus"></i> Agregar Puntos de Venta </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-md-8">
                    <div class="input-group">
                        <input type="text" class="form-control mr-1" #buscar placeholder="Ingrese rol a buscar..." (keyup.enter)="fbuscar(buscar.value)"/>
                        <span class="input-group-prepend">
                            <button class="btn btn-primary mr-1" (click)="fbuscar(buscar.value)" data-toggle="tooltip" data-placement="top" title="Buscar dato">
                                <i class="fa fa-search"></i> Buscar
                            </button>
                            <button class="btn btn-dark" (click)="limpiar()" (click)="buscar.value=''" data-toggle="tooltip" data-placement="top" title="Limpiar todo">
                                <i class="fa fa-list"></i> Limpiar
                            </button>
                        </span>
                    </div>
                </div>
                <div class="col-md-4 mt-1" *ngIf="total > cantidad">
                    <ngb-pagination class="d-flex justify-content-end align-items-baseline pagination-rounded" [collectionSize]="total" [(page)]="pagina" [maxSize]="2" [rotate]="false" [ellipses]="false" [pageSize]="cantidad" [boundaryLinks]="true" (pageChange)="mostrarMas($event)" size="sm"></ngb-pagination>
                </div>
            </div>

            <div class="table-responsive" *ngIf="datos?.length">
                <p class="text-right" *ngIf="total==1">Total: <b>{{total}} registro</b>&nbsp;
                    <select #cantidad (change)="fcambiarcantidad(cantidad.value)">
                        <option [value]="5">5</option>
                        <option [value]="10" selected>10</option>
                        <option [value]="20">20</option>
                    </select>
                </p>
                <p class="text-right" *ngIf="total>1">Total: <b>{{total}} registros</b>&nbsp;
                    <select #cantidad (change)="fcambiarcantidad(cantidad.value)">
                        <option [value]="5">5</option>
                        <option [value]="10" selected>10</option>
                        <option [value]="20">20</option>
                    </select>
                </p>
                <table class="table table-sm table-hover">
                    <thead>
                        <tr>
                            <th class="text-center align-middle">Nro.</th>
                            <th class="text-center align-middle">Razon Social</th>
                            <th class="text-center align-middle">Sucursal</th>
                            <th class="text-center align-middle">Punto de venta</th>
                            <th class="text-center align-middle">CUI</th>
                            <th class="text-center align-middle">Validez</th>
                            <th class="text-center align-middle">Estado</th>
                            <th class="text-center align-middle">Operaciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr *ngFor="let _item of datos; let i = index ">
                            <td class="text-center">{{i+1}}</td>
                            <td class="text-left">
                                <b>{{_item.nit}}</b><br>
                                <small>{{_item.razonsocial}}</small>
                            </td>
                            <td class="text-center">
                                <b>{{_item.sucursal}}</b><br>
                                <small>{{_item.nombresucursal}}</small>
                            </td>
                            <td class="text-center">
                                <b>{{_item.puntoventa}}</b><br>
                                <small>{{_item.nombrepuntoventa}}</small>
                            </td>
                            <td class="text-left">
                                <span class="badge badge-success" *ngIf="_item.estado">{{_item.cui}}</span>
                                <span class="badge badge-danger" *ngIf="_item.estado==false">No Asignado</span>
                            </td>
                            <td class="text-center">{{_item.fechavigencia}}</td>
                            <td class="text-center">
                                <span class="badge badge-success" *ngIf="_item.estado">VIGENTE</span>
                                <span class="badge badge-danger" *ngIf="_item.estado==false">NO VIGENTE</span>
                            </td>
                            <td class="text-center">
                                <button class="btn btn-info btn-sm mr-1 mb-1" (click)="fadicionarCui(_item.idcui)" data-toggle="tooltip" data-placement="top" title="Obtener CUI"><i class="fa fa-plus"></i> Obtener Cui</button>
                                <button class="btn btn-info btn-sm mr-1 mb-1" (click)="fobtenerCui(_item.idcui)" data-toggle="tooltip" data-placement="top" title="Generar CUI"><i class="fa fa-cog"></i> Generar</button>
                                <button class="btn btn-info btn-sm mr-1 mb-1" (click)="fregistrarPuntoventa(_item.idcui)" data-toggle="tooltip" data-placement="top" title="Registrar punto de venta" *ngIf="_item.idcui!=1"><i class="fa fa-cog"></i> Registrar punto de venta</button>
                            </td>
                        </tr>

                        <tr *ngIf="total > cantidad">
                            <td colspan="3">
                                <ngb-pagination class="d-flex justify-content-center pagination-rounded" [collectionSize]="total" [(page)]="pagina" [maxSize]="10" [rotate]="false" [ellipses]="false" [pageSize]="cantidad" [boundaryLinks]="true" (pageChange)="mostrarMas($event)"></ngb-pagination>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive" *ngIf="!datos?.length">
                <h1 class="text-center"><b>Sin datos</b></h1>
            </div>
        </div>
    </div>
</div>
