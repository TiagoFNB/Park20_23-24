import { HttpErrorResponse } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { Subscription } from "rxjs";
import { ErrorResponse } from "src/app/_dtos/ErrorResponse.dto";
import { VehicleDTO } from "src/app/_dtos/Vehicle.dto";
import { DetectVehicleCharacteristicsService } from "src/app/_services/detect-vehicle-characteristics.service";
import { ToastEmitter } from "src/app/_services/toast-emitter.service";

@Component({
  selector: 'dialog-vehicle',
  templateUrl: 'vehicle-dialog.component.html',
  styleUrls: ['./vehicle-dialog.component.css'],
})
export class VehicleDialog {

  vehicleForm : FormGroup;

  private subscriptions: Subscription[] = [];

  licensePlatePattern = /^(([A-Z]{2}-\d{2}-(\d{2}|[A-Z]{2}))|(\d{2}-(\d{2}-[A-Z]{2}|[A-Z]{2}-\d{2})))$/;

  constructor(
    public dialogRef: MatDialogRef<VehicleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private detectVehicleCharacteristicsService: DetectVehicleCharacteristicsService,
    private te: ToastEmitter,
  ) {
    this.vehicleForm = new FormGroup({
      licensePlate: new FormControl('', [Validators.required, Validators.pattern(this.licensePlatePattern)]),
      brand: new FormControl('', [Validators.required]),
      model: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required]),
    })

    this.onValueChange();
  }

  detectVehicleCharacteristics() {
    this.subscriptions.push(
      this.detectVehicleCharacteristicsService
        .detect(this.vehicleForm.get('licensePlate').value).subscribe(
          (response : VehicleDTO) => {

            this.vehicleForm.patchValue({brand: response.brand, category: response.category, model: response.model});
          },
          (error : HttpErrorResponse) => {
            const err : ErrorResponse = error.error;
            this.te.emitError(err.message);
          }
    ));

  }

  onValueChange() {
    this.vehicleForm.get('licensePlate').valueChanges.subscribe(newValue => {
      if (this.vehicleForm.get('licensePlate').valid) {
        this.detectVehicleCharacteristics();
      }
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onAddClick(): void {
    this.dialogRef.close(this.vehicleForm);
  }
}
