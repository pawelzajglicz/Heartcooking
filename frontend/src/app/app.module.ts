import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { ProductCardComponent } from './products/product-card/product-card.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { ProductsListComponent } from './products/products-list/products-list.component';
import { RecipesComponent } from './recipes/recipes.component';
import { RegisterComponent } from './register/register.component';
import { appRoutes } from './routes';
import { AuthService } from './services/auth.service';
import { EnvConfigService } from './services/env-config.service';
import { ErrorInterceptorProvider } from './services/error.interceptor';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { UnderConstructionComponent } from './under-construction/under-construction.component';
import { UsersComponent } from './users/users.component';

export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
   declarations: [
      AppComponent,
      HomeComponent,
      NavComponent,
      ProductCardComponent,
      ProductDetailsComponent,
      ProductsListComponent,
      RecipesComponent,
      RegisterComponent,
      ShoppingListComponent,
      UnderConstructionComponent,
      UsersComponent
   ],
   exports: [
    RouterModule
   ],
   imports: [
      BrowserAnimationsModule,
      BrowserModule,
      FormsModule,
      HttpClientModule,
      JwtModule.forRoot({
        config: {
          tokenGetter,
          allowedDomains: ['localhost:5000'],
          disallowedRoutes: ['localhost:5000/api/auth']
        }
      }),
      MatMenuModule,
      RouterModule.forRoot(appRoutes, { relativeLinkResolution: 'legacy' })
   ],
   providers: [
     AuthService,
     EnvConfigService,
     ErrorInterceptorProvider
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
