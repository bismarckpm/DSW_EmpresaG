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
      errorMsg = error.error.message;
    }
    else {
      // console.log(error)
      errorMsg = `${error.status} - ${error.statusText || ''}: ${error.error}`;
    }

    // TODO: Cambiar esto para manejar los errores desde el servidor Java EE
    return throwError(errorMsg);
  }
}
