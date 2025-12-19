import {Component, Input} from '@angular/core';


@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [],
  templateUrl: './topbar.html',
  styleUrl: './topbar.css',
})
export class TopbarComponent {
  @Input() title = '';
  @Input() subtitle = '';
}
