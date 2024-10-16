import { Component, Input } from "@angular/core";

@Component({
  selector: 'component-text-collumn',
  templateUrl: './text-collumn.component.html',
  styleUrls: ['./text-collumn.component.css']
})
export class TextCollumnComponent {

@Input() label;
@Input() content;


}
