import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarConfig } from "@angular/material/snack-bar";
import { TranslationService } from "./translation.service";

@Injectable({
  providedIn: 'root',
})
export class ToastEmitter {

  constructor(private snackBar: MatSnackBar, private transServ: TranslationService) {}

  emitWarning(message: string, duration: number = 3000): void {
    const config: MatSnackBarConfig = {
      duration: duration,
      panelClass: 'warning-toast', // Add custom CSS class
    };

    this.snackBar.open(this.transServ.translate(message), 'Close', config);
  }

  emitNotification(message: string, duration: number = 3000): void {
    const config: MatSnackBarConfig = {
      duration: duration,
      panelClass: 'notification-toast', // Add custom CSS class
    };

    this.snackBar.open(this.transServ.translate(message), 'Close', config);
  }

  emitError(message: string, duration: number = 3000): void {
    const config: MatSnackBarConfig = {
      duration: duration,
      panelClass: 'error-toast', // Add custom CSS class
    };

    this.snackBar.open(this.transServ.translate(message), 'Close', config);
  }

}
