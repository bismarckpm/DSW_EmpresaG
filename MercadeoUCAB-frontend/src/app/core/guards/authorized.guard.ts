import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {SessionService} from '../services/auth/session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizedGuard implements CanActivate {
  constructor(private router: Router,
              private sessionService: SessionService) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean>|Promise<boolean>|boolean{
    if (!this.sessionService.isAuthenticated()){
      this.router.navigate(['login']);
    }
    return true;
  }

}
