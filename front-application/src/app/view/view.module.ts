import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { FormFieldComponent } from './form-field/form-field.component';
import { ErrorComponent } from './error/error.component';



@NgModule({
  declarations: [
    CardComponent,
    FormFieldComponent,
    ErrorComponent,
  ],
  exports: [
    CardComponent,
    FormFieldComponent,
    ErrorComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class ViewModule { }
