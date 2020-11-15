import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProcessHttpMessageService {

  constructor() { }

  public handleError(error: HttpErrorResponse | any){
    let errorMsg: string;

    if (error.error instanceof ErrorEvent){
      errorMsg = error.error.message
    }
    else {
      errorMsg = `${error.status} - ${error.statusText || ''}: `
    }

    // TODO: Cambiar esto para manejar los errores desde el servidor Java EE
    return throwError(errorMsg)
  }
}
