import { AddPhoto } from './add-photo';

export interface AddProduct {
    name: string;
    description: string;
    carbohydrates: number;
    fat: number;
    fiber: number;
    kilocalories: number;
    protein: number;
    saturatedFat: number;
    sugar: number;
    isAllergen: number;
    isVegan: number;
    photos: AddPhoto[];
    tracesAllergensIds: number[];
}
