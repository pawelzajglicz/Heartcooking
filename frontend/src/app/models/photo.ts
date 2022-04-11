import { BaseModel } from './base-model';
import { Product } from './product';

export interface Photo extends BaseModel {

  description: string;
  url: string;
  dateAdded: Date;
  isMain: boolean;
  product: Product;
}

