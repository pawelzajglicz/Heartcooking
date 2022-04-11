import { Allergen } from './allergen';
import { BaseModel } from './base-model';
import { Photo } from './photo';

export interface Product extends BaseModel {

  name: string;
  description: string;
  carbohydrates: number;
  fat: number;
  fiber: number;
  kilocalories: number;
  protein: number;
  salt: number;
  saturatedFat: number;
  suger: number;
  isVegan: boolean;
  photoUrl: string;
  photos?: Photo[];
  allergens?: Allergen[];
}
