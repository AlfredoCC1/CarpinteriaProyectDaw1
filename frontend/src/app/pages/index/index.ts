import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NavbarComponent } from '../../core/navbar/navbar';
import { HeaderComponent } from '../../core/header/header';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [
    RouterLink,
    NavbarComponent,
    HeaderComponent
  ],
  templateUrl: './index.html',
  styleUrls: ['./index.css']
})
export class IndexComponent {}

