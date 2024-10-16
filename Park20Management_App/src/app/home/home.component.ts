import { ChangeDetectorRef, Component, OnDestroy, ViewChild } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

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

  items = [{name:'Managers Configuration', icon: 'fa-solid fa-people-roof', selected: false},
  {name:'Reports and analytics', icon: 'fa-solid fa-chart-line', selected: false},
  {name:'Park Management', icon: 'fa-solid fa-square-parking', selected: false},
  {name:'Parky Wallet', icon: 'fa-solid fa-bag-shopping', selected: false}, 
  {name:'PriceTables', icon: 'fa-solid fa-money-check-dollar', selected: false},
  {name:'Rank customers usage', icon: 'fa-solid fa-ranking-star', selected: false},]

  //change this to one of the items in array items if you want a default selection
  selected = {name: null};

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private router: Router,
    private route: ActivatedRoute,
    ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.selected = {name: "Routed"};
    });
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  onSelect(item) {
    this.router.navigate(["/home"], { skipLocationChange: false });
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
