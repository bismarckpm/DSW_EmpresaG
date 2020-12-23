import { Injectable } from '@angular/core';
import { Session } from '../../classes/auth/session';
import {Router} from '@angular/router';
import {Users} from '../../classes/auth/users';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private localStorageService;
  private currentSession: Session = null;

  constructor(private router: Router) {
    this.localStorageService = localStorage;
    this.currentSession = this.loadSessionData();
  }

  getCurrentSession(): Session {
    return this.currentSession;
  }

  setCurrentSession(session: Session): void{
    this.currentSession = session;
    this.localStorageService.setItem('currentUser', JSON.stringify(session));
  }

  removeCurrentSession(): void {
    this.localStorageService.removeItem('currentUser');
    this.currentSession = null;
  }

  getCurrentUser(): number {
    const session: Session = this.getCurrentSession();
    return (session && session._id) ? session._id : null;
  }

  isAuthenticated(): boolean {
    return (this.getCurrentToken() != null) ? true : false;
  }

  isAdmin(): boolean {
    return this.getCurrentSession().fkRol._id === 1;
  }

  isAnalyst(): boolean {
    return this.getCurrentSession().fkRol._id === 2;
  }

  isClient(): boolean {
    return this.getCurrentSession().fkRol._id === 3;
  }

  isPolled(): boolean {
    return this.getCurrentSession().fkRol._id === 4;
  }

  getCurrentToken(): string {
    const session = this.getCurrentSession();
    return (session && session.tokenLogin) ? session.tokenLogin : null;
  }

  logout(): void{
    this.removeCurrentSession();
    this.router.navigate(['/login']);
  }

  loadSessionData(): Session{
    const sessionStr = this.localStorageService.getItem('currentUser');
    return (sessionStr) ? JSON.parse(sessionStr) as Session : null;
  }
}
