import { Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index';
import { NosotrosComponent } from './pages/nosotros/nosotros';
import {DashboardComponent} from './pages/dashboard/dashboard';
import {LoginComponent} from './pages/login/login';
import {RegisterComponent} from './pages/register/register';
import {ServiciosComponent} from './pages/servicios/servicios';
import {ProductosComponent} from './pages/productos/productos';
import {ProcesoComponent} from './pages/proceso/proceso';


export const routes: Routes = [
  {path: '', component: IndexComponent},
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'servicios', component: ServiciosComponent },
  { path: 'productos', component: ProductosComponent },
  { path: 'proceso', component: ProcesoComponent },
];
