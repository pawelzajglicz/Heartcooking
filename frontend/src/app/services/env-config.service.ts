import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReplaySubject, Observable, throwError } from 'rxjs';

import { environment } from './../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class EnvConfigService {

  private apiUrlSubjects: ReplaySubject<string> = new ReplaySubject<string>(1);

  constructor(private httpClient: HttpClient) { }

  public init() {

    if (environment.production) {
      this.httpClient.get('assets/env.json').subscribe((envFile: any) => {
        this.apiUrlSubjects.next(envFile.baseUrl);
      });
    } else {
      this.apiUrlSubjects.next(environment.baseUrl);
    }
  }

  public getApiUrls$(): Observable<string> {
    return this.apiUrlSubjects.asObservable();
  }
}
