import { Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index';
import { NosotrosComponent } from './pages/nosotros/nosotros';
import {DashboardComponent} from './pages/dashboard/dashboard';


export const routes: Routes = [
  {path: '', component: IndexComponent},
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'dashboard', component: DashboardComponent }
];
