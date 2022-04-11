import { BaseModel } from './base-model';
import { Product } from './product';

export interface Allergen extends BaseModel {

  name: string;
  product: Product;
}
