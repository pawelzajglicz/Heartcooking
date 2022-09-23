
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '../product.service';
import { Product } from './../../models/product';
import { AlertifyService } from './../../services/alertify.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  product: Product;

  constructor(private alertify: AlertifyService,
              private producService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.loadProduct();
  }

  loadProduct() {
    this.producService.getProduct(+this.route.snapshot.params['id']).subscribe((product: Product) => {
      this.product = product;
    }, error => {
      this.alertify.error(error);
    });
  }

}
