import { Component, EventEmitter, Input, OnDestroy, Output } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";

@Component({
  selector: 'component-register-data',
  templateUrl: './register-data.component.html',
  styleUrls: ['./register-data.component.css']
})
export class RegisterDataComponent {

  @Input() form: FormGroup;

  @Output() next: EventEmitter<any> = new EventEmitter();

  constructor(
    private te: ToastEmitter,
  ) {}

  nextStep() {
    if(this.form.valid) {
      this.next.emit();
    } else {
      this.te.emitError("InvalidData");
    }
  }

}
