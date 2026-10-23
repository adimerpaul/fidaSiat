import { Time } from "@angular/common";
import { Detalles } from "./detalles";

export class Facturas {

    idtransaccion: number;
    idusuario: number;
    usuario: string;
    idcliente: number;
    iddocumentoidentidadsin: number;
    idmetodopagosin: number;
    cliente: string;
    numerodocumento: string;
    complemento: string;
    numerofactura: number;
    total: number;
    descuento: number;
    efectivo: number;
    cambio: number;
    fecha: Date;
    hora: Time;
    formapago: Boolean;
    distribucion: Boolean;
    tipotransaccion: string;
    justificacion: string;
    anulado: Boolean;
    codigo: number;
    estado: string;
    cufd: string;
    cuf: string;
    detalles: Detalles[];

}
