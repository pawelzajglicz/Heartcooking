import { Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { NewProductComponent } from './products/new-product/new-product.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { ProductsListComponent } from './products/products-list/products-list.component';
import { RecipesComponent } from './recipes/recipes.component';
import { RegisterComponent } from './register/register.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { UsersComponent } from './users/users.component';



export const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'new-product', component: NewProductComponent },
  { path: 'products', component: ProductsListComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'recipes', component: RecipesComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'shopping-list', component: ShoppingListComponent },
  { path: 'users', component: UsersComponent },
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];
