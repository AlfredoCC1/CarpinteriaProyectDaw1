import { Component } from '@angular/core';
import {NavbarComponent} from '../../core/navbar/navbar';
import {FooterComponent} from '../../core/footer/footer';

@Component({
  selector: 'app-detalle',
  standalone: true,
  imports: [
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './detalle.html',
  styleUrl: './detalle.css',
})
export class DetalleComponent {

}
