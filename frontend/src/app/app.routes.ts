import { Routes } from '@angular/router';
import {IndexComponent} from './pages/index';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/index/index').then(m => m.IndexComponent)
  }
];
