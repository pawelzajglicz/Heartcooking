import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { AddProduct } from '../models/add-product';
import { Product } from '../models/product';
import { EnvConfigService } from '../services/env-config.service';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl: string;

  constructor(private envConfigService: EnvConfigService,
              private http: HttpClient) {

    this.envConfigService.getApiUrls$().subscribe((apiUrl: string) => {
      this.baseUrl = apiUrl;
    });
  }

  addProduct(addProduct: AddProduct): Observable<unknown> {
    return this.http.post<unknown>(this.baseUrl + 'products', addProduct);
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + 'products');
  }

  getProduct(id: number | string): Observable<Product> {
    return this.http.get<Product>(this.baseUrl + 'products/' + id);
  }
}
