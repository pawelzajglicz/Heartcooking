/* tslint:disable:no-unused-variable */

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed, inject, waitForAsync } from '@angular/core/testing';
import { EnvConfigService } from './env-config.service';

describe('Service: EnvConfig', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
       ],
      providers: [EnvConfigService]
    });
  });

  it('should ...', inject([EnvConfigService], (service: EnvConfigService) => {
    expect(service).toBeTruthy();
  }));
});
