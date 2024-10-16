import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";
import { VehicleDialog } from "src/app/vehicle/vehicle-dialog/vehicle-dialog.component";
import { SaveVehicleListService } from "../_services/save-vehicle.service";
import { VehicleDTO } from "../_dtos/Vehicle.dto";
import { HttpErrorResponse } from "@angular/common/http";
import { ErrorResponse } from "../_dtos/ErrorResponse.dto";
import { Subscription } from "rxjs";

@Component({
  selector: 'component-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent {

  private subscriptions: Subscription[] = [];

  form: FormArray;

  constructor(
    private te: ToastEmitter,
    private dialog: MatDialog,
    private saveVehicleListService: SaveVehicleListService,
  ) {
    this.form = new FormArray([], [Validators.required,]);
  }

  addVehicle() {
    if(this.form.controls.length >= 3) {
      this.te.emitError("MaximumReached");
    } else {
      const dialogRef = this.dialog.open(VehicleDialog);

      dialogRef.afterClosed().subscribe(result => {
        if(result) {
          const alreadyAdded = this.form.value.find(elem => elem.licensePlate === result.get('licensePlate').value);
          if(alreadyAdded === undefined) {
            this.form.push(result);
          } else {
            this.te.emitWarning("CannotAddSameVehicle");
          }
        }
      });
    }
  }

  confirm() {
    if(this.form.valid && this.form.controls.length > 0) {
      const body = {vehicles: this.getVehicleDtos()};


      this.subscriptions.push(
        this.saveVehicleListService.save(body).subscribe(
            (response) => {
              this.te.emitNotification("SaveSuccessful");
            },
            (error : HttpErrorResponse) => {
              const err : ErrorResponse = error.error;
              this.te.emitError(err.message);
            }
      ));
    } else {
      this.te.emitError("Minimum1Vehicle");
    }
  }

  private getVehicleDtos() : VehicleDTO[] {
    const vehicles = [];

    for(let control of this.form.controls) {
      const dto = {
        plate: control.get('licensePlate').value,
        brand: control.get('brand').value,
        model: control.get('model').value,
        category: control.get('category').value,
      };

      vehicles.push(dto);
    }
    return vehicles;
  }

}
