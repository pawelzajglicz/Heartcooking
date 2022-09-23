import { ChangeDetectionStrategy, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { TracesAllergen } from 'src/app/models/traces-allergen';
import { TracesAllergensService } from 'src/app/traces-allergen/traces-allergens.service';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { AlertifyService } from 'src/app/services/alertify.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class NewProductComponent implements OnInit {

  productForm: FormGroup;
  mainPhoto: any;

  separatorKeysCodes: number[] = [ENTER, COMMA];
  tracesAllergens$: Observable<TracesAllergen[]>;

  @ViewChild("traceAllergenInput") traceAllergenInput: ElementRef;
  @ViewChildren("photoRadiobutton") photoRadiobuttons: QueryList<ElementRef>;

  constructor(private alertifyService: AlertifyService,
              private fb: FormBuilder,
              private productService: ProductService,
              private router: Router,
              private tracesAllergensService: TracesAllergensService) { }

  ngOnInit(): void {
    this.initializeNewProductForm();
    this.tracesAllergens$ = this.tracesAllergensService.getTracesAllergens();
  }

  onSubmit(productForm) {
    console.log(productForm);
  }

  initializeNewProductForm() {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      carbohydrates: ['', Validators.required],
      fat: ['', Validators.required],
      fiber: ['', Validators.required],
      kilocalories: ['', Validators.required],
      protein: ['', Validators.required],
      salt: ['', Validators.required],
      saturatedFat: ['', Validators.required],
      sugar: ['', Validators.required],
      isAllergen: [false, Validators.required],
      tracesAllergens: this.fb.array([]),
      isVegan: [false, Validators.required],
    //  photos: this.fb.array([], Validators.required),
    });
  }

  addTracesAllergen(tracesAllergen: TracesAllergen) {
    console.log('addTracesAllergen', tracesAllergen);
  }

  removeTracesAllergen(tracesAllergen: TracesAllergen) {
    console.log('removeTracesAllergen', tracesAllergen);
    const index = this.productForm.controls.tracesAllergens.value.indexOf(tracesAllergen);
    this.productForm.controls.tracesAllergens.value.splice(index, 1);
  }

  selectedTracesAllergen(tracesAllergenSelectedEvent: MatAutocompleteSelectedEvent) {
    console.log('selectedTracesAllergen', tracesAllergenSelectedEvent, this.productForm['tracesAllergens'], this.productForm.controls['tracesAllergens']);
    console.log(tracesAllergenSelectedEvent.option.value);
    this.productForm.controls.tracesAllergens.value.push(tracesAllergenSelectedEvent.option.value);
    this.traceAllergenInput.nativeElement.value = '';
  }

  addPhoto() {
    this.productForm.controls.photos.value.push(this.fb.group({
      url: ['', Validators.required],
      description: [''],
    }));

    if (this.productForm.controls.photos.value.length === 1) {
     this.setFirstPhotoAsMainAfterSomeTime();
     }
  }

  private setFirstPhotoAsMainAfterSomeTime() {
    setTimeout(() => {
      this.photoRadiobuttons.first.nativeElement.checked = true;
      this.mainPhoto = this.productForm.controls.photos.value[0];
    });
  }

  selectedMainPhoto(photo: any) {
    console.log('selectedMainPhoto', photo);
    this.mainPhoto = photo;
  }

  removePhoto(photoForm: FormGroup, index: number) {
    const photoToRemove = this.productForm.controls.photos.value[index];
    console.log({photoToRemove});
    this.productForm.controls.photos.value.splice(index, 1);
    if (photoForm === this.mainPhoto) {
      this.setFirstPhotoAsMainAfterSomeTime();
      }
  }

  addProduct() {
    console.log('addProduct');
    const product = Object.assign({}, this.productForm.value);
    console.log(product);
    /*const photo1 = Object.assign({}, product.photos.value);
    product.photos = product.photos.map(photoForm => {
      const photo = photoForm.value;
      photo.isMain = false;
      return photo;
    })*/
    console.log(product);
    this.productService.addProduct(product).subscribe(() => {
      this.alertifyService.success('Pomyślnie dodano produkt');
   //   this.router.navigate(['/home']);
    }, error => {
      this.alertifyService.error(error);
    });
  }
}
