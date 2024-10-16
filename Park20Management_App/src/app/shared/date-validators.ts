import { AbstractControl, ValidatorFn, ValidationErrors } from "@angular/forms";

export class DateValidators {
    static greaterThanHours(startControl: AbstractControl): ValidatorFn {
      return (endControl: AbstractControl): ValidationErrors | null => {
        const [ h1, m1 ] = startControl.value.split(":");
        const ms1 = new Date().setHours(h1,m1);
        const startDate: Date = new Date(ms1);
        const [ h2, m2 ] = endControl.value.split(":");
        const ms2 = new Date().setHours(h2,m2);
        const endDate: Date =  new Date(ms2);
        if (!startDate || !endDate) {
          return null;
        }
        if (startDate.getHours() > endDate.getHours()) {
          return { greaterThanHours: true };
        }
        if (startDate.getHours() == endDate.getHours() && startDate.getMinutes()>= endDate.getMinutes()) {
            return { greaterThanHours: true };
          }
        return null;
      };
    }
  }