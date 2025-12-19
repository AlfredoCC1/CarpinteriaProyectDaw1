import { Component, EventEmitter, Input, Output } from '@angular/core';

type ViewId = 'dashboard' | 'linea' | 'categoria' | 'productos';

@Component({
  selector: 'app-aside',
  standalone: true,
  templateUrl: './aside.html',
  styleUrls: ['./aside.css'],
})
export class AsideComponent {
  @Input() activeView: 'graficos' | 'linea' | 'categoria' | 'productos' = 'graficos' ;
  @Output() viewChange = new EventEmitter<'graficos' | 'linea' | 'categoria'| 'productos'>();

  go(view: 'graficos' | 'linea'| 'categoria' | 'productos'  ) {
    this.viewChange.emit(view);
  }
}
