import {MatDialog} from "@angular/material/dialog";
import {ShowListParkService} from "../_services/show-list-park.service";
import {GetCustomerLoggedService} from "../_services/get-customer-logged.service";
import {ToastEmitter} from "../_services/toast-emitter.service";
import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable, Subscription} from "rxjs";
import {ParkWithDistanceDTO} from "../_dtos/ParkWithDistance.dto";
import * as L from 'leaflet';
import {CustomerDTO} from "../_dtos/Customer.dto";


@Component({
  selector: 'component-showListPark',
  templateUrl: './showListPark.component.html',
  styleUrls: ['./showListPark.component.css']
})
export class ShowListParkComponent implements OnInit {

  public listParks: ParkWithDistanceDTO[] = [];
  nearbyPark: FormGroup;
  private subscriptions: Subscription[] = [];

  constructor(private te: ToastEmitter, private dialog: MatDialog, private showListParkService: ShowListParkService,
              private getCustomerLoggedService: GetCustomerLoggedService) {
    this.nearbyPark = new FormGroup({
      designation: new FormControl('', [Validators.required]),
      street: new FormControl('', [Validators.required]),
      distance: new FormControl('', [Validators.required]),
      spotHandicap: new FormControl('', [Validators.required]),
      spotEletric: new FormControl('', [Validators.required]),
      spotGPL: new FormControl('', [Validators.required]),
      spotGas: new FormControl('', [Validators.required]),
      spotMotorcycle: new FormControl('', [Validators.required]),
    })
  }

  ngOnInit(): void {
    this.subscriptions.push(this.getPosition().subscribe(pos => {
      console.log(pos);
      this.showListOfParks(pos.coords.latitude, pos.coords.longitude);
    }))
  }

  showListOfParks(lat, long) {
    const map = L.map('map').setView([lat, long], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    //TODO: get vehicles and then check the spots for the type of vehicle
    this.getCustomerLoggedService.get().subscribe((response: CustomerDTO) => {
      console.log(response);
    });

    this.subscriptions.push(this.showListParkService.getParks(lat, long).subscribe((response: ParkWithDistanceDTO[]) => {
      this.listParks = response;

      this.listParks.forEach(park => {
        const parkIcon = L.icon({
          iconUrl: '../../assets/icons/car-park.png', iconSize: [64, 64], iconAnchor: [32, 64], popupAnchor: [0, -64]
        });

        const marker = L.marker([parseFloat(String(park.address.latitude)), parseFloat(String(park.address.longitude))], {icon: parkIcon}).addTo(map);
        let markerPopup = `<b>${park.designation}</b><br><br>
                Street: ${park.address.street}<br>
                Distance: ${park.distance}km<br>
                Number of Spots: ${park.spotGas}`;
        marker.bindPopup(markerPopup);
      })
      map.invalidateSize();
    }))
  }


  getPosition(): Observable<any> {
    return Observable.create(observer => {
      window.navigator.geolocation.getCurrentPosition(position => {
        observer.next(position);
        observer.complete();
      }, error => observer.error(error));
    });
  }
}
