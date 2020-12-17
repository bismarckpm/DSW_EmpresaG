import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {SessionService} from '../services/auth/session.service';

@Injectable({
  providedIn: 'root'
})
export class SurveyGuard implements CanActivate {
  constructor(private router: Router,
              private sessionService: SessionService) {
  }

  canActivate(){
    return this.sessionService.isPolled();
  }
}
