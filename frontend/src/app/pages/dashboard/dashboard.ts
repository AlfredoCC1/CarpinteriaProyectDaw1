import {AfterViewInit, Component, Inject, PLATFORM_ID} from '@angular/core';
import {AsideComponent} from './fragments/aside/aside';
import {TopbarComponent} from './fragments/topbar/topbar';
import {isPlatformBrowser} from '@angular/common';

type ViewId =
  | 'view-dashboard'
  | 'view-lineas'
  | 'view-categorias'
  | 'view-productos'
  | 'view-empleados'
  | 'view-pedidos'
  | 'view-usuarios'
  | 'view-configuracion';


@Component({
  selector: 'app-dashboard',
  imports: [
    AsideComponent,
    TopbarComponent
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class DashboardComponent implements AfterViewInit {

  // ✅ vista actual
  currentView: ViewId = 'view-dashboard';

  // ✅ títulos del topbar
  readonly viewTitles: Record<ViewId, { title: string; subtitle: string }> = {
    'view-dashboard': { title: 'Dashboard', subtitle: 'Resumen general de la carpintería' },
    'view-lineas': { title: 'Líneas de Diseño', subtitle: 'Administra las líneas de diseño' },
    'view-categorias': { title: 'Categorías', subtitle: 'Administra las categorías de productos' },
    'view-productos': { title: 'Productos', subtitle: 'Administra tu catálogo de productos' },
    'view-empleados': { title: 'Empleados', subtitle: 'Gestión de Empleados' },
    'view-pedidos': { title: 'Pedidos', subtitle: 'Seguimiento de pedidos' },
    'view-usuarios': { title: 'Usuarios', subtitle: 'Administración de usuarios del sistema' },
    'view-configuracion': { title: 'Configuración', subtitle: 'Configuración del sistema' },
  };

  // ✅ sidebar móvil
  sidebarOpen = false;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  // si quieres inicializar algo al cargar dashboard
  ngAfterViewInit(): void {
    if (!isPlatformBrowser(this.platformId)) return;

    // Si usas AOS global (CDN), refresca
    this.refreshAos();

    // Si dashboard necesita charts al inicio
    this.initChartsIfNeeded(this.currentView);
  }

  // ✅ Cambiar vista (reemplazo de window.switchView)
  switchView(viewId: ViewId): void {
    this.currentView = viewId;

    // cerrar sidebar en móvil
    if (isPlatformBrowser(this.platformId) && window.innerWidth < 992) {
      this.sidebarOpen = false;
    }

    // refrescar AOS (si existe)
    this.refreshAos();

    // inicializar charts si corresponde
    this.initChartsIfNeeded(viewId);
  }

  // ✅ Helpers para mostrar topbar
  get pageTitle(): string {
    return this.viewTitles[this.currentView].title;
  }

  get pageSubtitle(): string {
    return this.viewTitles[this.currentView].subtitle;
  }

  toggleSidebar(): void {
    this.sidebarOpen = !this.sidebarOpen;
  }

  private refreshAos(): void {
    if (!isPlatformBrowser(this.platformId)) return;

    const w = window as any;
    if (w.AOS && typeof w.AOS.refresh === 'function') {
      w.AOS.refresh();
    }
  }

  private initChartsIfNeeded(viewId: ViewId): void {
    if (!isPlatformBrowser(this.platformId)) return;

    // si tienes una función global initializeCharts()
    if (viewId === 'view-dashboard') {
      const w = window as any;
      if (typeof w.initializeCharts === 'function') {
        setTimeout(() => w.initializeCharts(), 100);
      }
    }
  }
}
