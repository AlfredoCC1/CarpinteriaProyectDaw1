import { Component } from '@angular/core';
import { AsideComponent } from './fragments/aside/aside';
import { TopbarComponent } from './fragments/topbar/topbar';
import { LineaComponent } from './fragments/linea/linea';
import {GraficosComponent} from './fragments/graficos/graficos';
import {CategoriaComponent} from './fragments/categoria/categoria';
import {ProductosComponent} from './fragments/productos/productos';


type ViewId = 'graficos' | 'linea' | 'categoria' | 'productos';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [AsideComponent, TopbarComponent, LineaComponent, GraficosComponent, CategoriaComponent, ProductosComponent],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'],
})
export class DashboardComponent {
  currentView: ViewId = 'graficos';

  // para títulos en topbar (puedes ampliar)
  titles: Record<ViewId, { title: string; subtitle: string }> = {
    graficos: { title: 'Dashboard', subtitle: 'Resumen general de la carpintería' },
    linea: { title: 'Líneas de Diseño', subtitle: 'Administra las líneas de diseño' },
    categoria: { title: 'Categorías', subtitle: 'Administra las categorías de productos' },
    productos: { title: 'Productos', subtitle: 'Productos de productos' },
  };

  switchView(view: ViewId) {
    this.currentView = view;
  }
}
