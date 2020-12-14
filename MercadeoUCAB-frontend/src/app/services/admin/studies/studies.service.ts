import { Injectable } from '@angular/core';
import { Study } from '../../../classes/study';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../../../constants/baseURL';
import { serverURL } from '../../../constants/serverURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { StudyWithFilter } from '../../../classes/study_with_filter';
import { QuestionCategorySubcategory } from '../../../classes/question_category_subcategory';
import { Question } from '../../../classes/question';
import { StudyQuestion } from '../../../classes/study_question';

@Injectable({
  providedIn: 'root'
})
export class StudiesService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getStudies(): Observable<StudyWithFilter[]> {
    return this.http.get<StudyWithFilter[]>(serverURL + 'studies/existing')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getStudy(study_id): Observable<StudyWithFilter>{
    return this.http.get<StudyWithFilter>(serverURL + 'studies/filters/' + study_id)
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getStudyQuestions(study_id): Observable<QuestionCategorySubcategory[]>{
    return this.http.get<QuestionCategorySubcategory[]>(serverURL + 'studies/questions/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getStudyQuestionsWithOptions(study_id): Observable<StudyQuestion[]>{
    return this.http.get<StudyQuestion[]>(serverURL + 'studies/questions-with-options/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putStudy(study): Observable<StudyWithFilter>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<StudyWithFilter>(serverURL + 'studies/update/' + study._id, study, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  linkCreatedQuestionToStudy(study_id, question_id): Observable<Question>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Question>(serverURL + 'studies/questions/' + study_id + '/link/' + question_id, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getSimilarStudies(category_id): Observable<StudyWithFilter[]>{
    return this.http.get<StudyWithFilter[]>(serverURL + 'studies/similar/' + category_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  cloneStudy(cloned_id, request_id): Observable<StudyWithFilter>{
    return this.http.post<StudyWithFilter>(serverURL + 'studies/assign/' + cloned_id + '/' + request_id, 0)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getInProgressStudies(): Observable<StudyWithFilter[]> {
    return this.http.get<StudyWithFilter[]>(serverURL + 'studies/active')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getFinishedStudies(): Observable<Study[]> {
    return this.http.get<Study[]>(baseURL + 'studies', {params: {
      id_estado: "3"
    }})
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postStudy(study): Observable<Study>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Study>(baseURL + 'studies', study, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
