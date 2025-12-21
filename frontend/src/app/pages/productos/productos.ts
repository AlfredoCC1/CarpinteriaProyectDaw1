import { Component, OnInit, ChangeDetectorRef, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser, CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from '../../core/navbar/navbar';
import { FooterComponent } from '../../core/footer/footer';

import { ProductoService } from '../../core/services/Producto/Producto.service';
import { Producto } from '../../../Model/Producto';

type Filtro = { label: string; value: string };

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, CommonModule, RouterModule],
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class ProductosComponent implements OnInit {

  filtros: Filtro[] = [
    { label: 'Todo', value: 'todo' },
    { label: 'Mesas', value: 'mesas' },
    { label: 'Sillas', value: 'sillas' },
    { label: 'Cocinas', value: 'cocinas' },
    { label: 'Closets', value: 'closets' },
    { label: 'Decoración', value: 'decoracion' },
  ];

  filtroActivo = 'todo';
  productos: Producto[] = [];
  productosFiltrados: Producto[] = [];

  cargando = false;
  error = '';

  constructor(
    private productoService: ProductoService,
    private cdr: ChangeDetectorRef,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    // ✅ IMPORTANTE: solo en navegador
    if (isPlatformBrowser(this.platformId)) {
      this.cargar();
    } else {
      // SSR: no cargar
      this.cargando = false;
    }
  }

  cargar(): void {
    this.cargando = true;
    this.error = '';

    this.productoService.listarPublico().subscribe({
      next: (data) => {
        this.productos = data ?? [];
        this.aplicarFiltro(this.filtroActivo);
        this.cargando = false;
        this.cdr.detectChanges(); // ✅ pinta sin esperar click
      },
      error: (err) => {
        console.error('ERROR catálogo', err);
        this.cargando = false;
        this.error = err?.error?.message || 'No se pudo cargar los productos.';
        this.cdr.detectChanges();
      },
    });
  }

  setFiltro(value: string): void {
    this.filtroActivo = value;
    this.aplicarFiltro(value);
  }

  private aplicarFiltro(value: string): void {
    if (value === 'todo') {
      this.productosFiltrados = [...this.productos];
      return;
    }

    this.productosFiltrados = this.productos.filter((p) => {
      const cat = this.norm(p.categoria?.nombre);
      return cat === value;
    });
  }

  img1(p: Producto): string {
    return p.imagen1?.trim() || 'assets/img/no-image.png';
  }

  descCard(p: Producto): string {
    return p.descripcionCard?.trim() || 'Producto artesanal a medida';
  }

  precioTexto(p: Producto): string {
    if (p.precio !== null && p.precio !== undefined) {
      const n = Number(p.precio);
      if (!Number.isNaN(n)) return `S/ ${n.toFixed(2)}`;
    }
    return p.etiquetaPrecio?.trim() || 'A cotizar';
  }

  private norm(s?: string): string {
    return (s || '')
      .trim()
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '');
  }
}
