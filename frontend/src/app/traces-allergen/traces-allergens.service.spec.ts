import { TestBed } from '@angular/core/testing';

import { TracesAllergensService } from './traces-allergens.service';

describe('TracesAllergensService', () => {
  let service: TracesAllergensService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TracesAllergensService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
