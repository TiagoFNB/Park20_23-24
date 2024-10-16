import { ChangeDetectorRef, Component, OnDestroy, ViewChild } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'component-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnDestroy {

  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;
  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  isCollapsed = true;

  items = [{name:'Nearby parks', icon: 'fa-solid fa-map', selected: false},{name:'Parky Wallet', icon: 'fa-solid fa-bag-shopping', selected: false},
  {name:'Payments', icon: 'fa-solid fa-wallet', selected: false}, {name:'Vehicles', icon: "fa-solid fa-car", selected: false},{name:'Subscriptions', icon: "fa-brands fa-rev", selected: false}, {name: 'Payment Methods', icon: "fa-solid fa-credit-card", selected: false}]

  //change this to one of the items in array items if you want a default selection
  selected = {name: null};

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  onSelect(item) {
    this.selected=item;
  }

  toggleMenu() {
    if(this.mobileQuery.matches){
      this.sidenav.toggle();
      this.isCollapsed = false;
    } else {
      this.sidenav.open();
      this.isCollapsed = !this.isCollapsed;
    }
  }

}
