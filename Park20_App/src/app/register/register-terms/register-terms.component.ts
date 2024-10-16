import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";

@Component({
  selector: 'component-register-terms',
  templateUrl: './register-terms.component.html',
  styleUrls: ['./register-terms.component.css']
})
export class RegisterTermsComponent {

  @Input() form: FormGroup;

  @Output() next: EventEmitter<any> = new EventEmitter();

  @Output() back: EventEmitter<any> = new EventEmitter();

  constructor(
    private te: ToastEmitter,
    private dialog: MatDialog
  ) {}

  nextStep() {
    // if(this.form.valid && this.form.controls.length > 0) {
    //   this.next.emit();
    // } else {
    //   this.te.emitError("InvalidData");
    // }

    this.next.emit();
  }

  backStep() {
    this.back.emit();
  }

}
