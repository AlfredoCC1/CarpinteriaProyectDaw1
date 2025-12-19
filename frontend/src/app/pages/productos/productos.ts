import { Component } from '@angular/core';
import {NavbarComponent} from '../../core/navbar/navbar';
import {FooterComponent} from '../../core/footer/footer';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-productos',
  imports: [
    NavbarComponent,
    FooterComponent,
    RouterLink
  ],
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class ProductosComponent {

}
