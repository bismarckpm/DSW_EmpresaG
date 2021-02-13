import { Injectable } from '@angular/core';
import { Study } from '../../../classes/study/study';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { StudyWithFilter } from '../../../classes/study/study_with_filter';
import { QuestionCategorySubcategory } from '../../../classes/study/question_category_subcategory';
import { Question } from '../../../classes/study/question';
import { StudyQuestion } from '../../../classes/study/study_question';
import { Respuesta } from 'src/app/core/classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class StudiesService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getStudies(): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'studies/existing')
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getStudy(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'studies/filters/' + study_id)
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getStudyQuestions(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'studies/questions/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getStudyQuestionsWithOptions(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'studies/questions-with-options/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  putStudy(study): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'studies/update/' + study._id, study, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  linkCreatedQuestionToStudy(study_id, question_id): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Respuesta>(serverURL + 'studies/questions/' + study_id + '/link/' + question_id, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getSimilarStudies(category_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'studies/similar/' + category_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  cloneStudy(cloned_id, request_id): Observable<Respuesta>{
    return this.http.post<Respuesta>(serverURL + 'studies/assign/' + cloned_id + '/' + request_id, 0)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
