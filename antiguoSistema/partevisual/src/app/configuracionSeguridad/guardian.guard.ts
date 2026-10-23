import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccesoService } from '../servicio/acceso.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GuardianGuard implements CanActivate {

  ROLES: string = environment.ROLES;

  constructor(
    private _accesoService: AccesoService,
    private _ruta: Router
  ) {}

  canActivate(
    _ruta: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      const roles = sessionStorage.getItem(this.ROLES);
      const rol = _ruta.data.rol;
      const rpta = this._accesoService.estaLogeado();

      if (!rpta) {
        sessionStorage.clear();
        this._ruta.navigateByUrl('/acceso');
      } else {
        if (rol === 'ROLE_TODOS') {
          return true;
        }
        if (Array.isArray(rol)) {
          if (rol.some(r => roles.includes(r))) {
            return true;
          } else {
            this._ruta.navigateByUrl('/e401');
            return false;
          }
        } else {
          if (roles.includes(rol)) {
            return true;
          } else {
            this._ruta.navigateByUrl('/e401');
            return false;
          }
        }

      }
  }

}
