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
  isAllergen: boolean;
  isVegan: boolean;
  photoUrl: string;
  allergenNames: string[],
  photos: Photo[];
}
