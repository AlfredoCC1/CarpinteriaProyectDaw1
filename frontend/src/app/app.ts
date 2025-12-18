import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './core/navbar/navbar';
import { FooterComponent } from './core/footer/footer';
import {AsideComponent} from './pages/dashboard/fragments/aside/aside';
import {TopbarComponent} from './pages/dashboard/fragments/topbar/topbar';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, FooterComponent,AsideComponent,TopbarComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {}
