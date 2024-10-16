import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { VehicleDialog } from "src/app/vehicle/vehicle-dialog/vehicle-dialog.component";

@Component({
  selector: 'component-register-vehicles',
  templateUrl: './register-vehicles.component.html',
  styleUrls: ['./register-vehicles.component.css']
})
export class RegisterVehiclesComponent {

  @Input() form: FormArray;

  @Output() next: EventEmitter<any> = new EventEmitter();

  @Output() back: EventEmitter<any> = new EventEmitter();

  constructor(
    private te: ToastEmitter,
    private dialog: MatDialog
  ) {}

  addVehicle() {
    if(this.form.controls.length >= 3) {
      this.te.emitError("MaximumReached");
    } else {
      const dialogRef = this.dialog.open(VehicleDialog);

      dialogRef.afterClosed().subscribe(result => {
        if(result) {
          this.form.push(result);
        }
      });
    }
  }

  nextStep() {
    if(this.form.valid && this.form.controls.length > 0) {
      this.next.emit();
    } else {
      this.te.emitError("Minimum1Vehicle");
    }
  }

  backStep() {
    this.back.emit();
  }

}
