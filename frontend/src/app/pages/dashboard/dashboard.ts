import { Component } from '@angular/core';
import {AsideComponent} from './fragments/aside/aside';
import {TopbarComponent} from './fragments/topbar/topbar';

@Component({
  selector: 'app-dashboard',
  imports: [
    AsideComponent,
    TopbarComponent
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class DashboardComponent {

}
