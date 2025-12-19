import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../core/services/Login/auth';
import { Login } from '../../../Model/Login';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';
  loading = false;

  constructor(private auth: Auth, private router: Router) {}

  login(): void {
    this.error = '';
    this.loading = true;

    this.auth.login(this.username, this.password).subscribe({
      next: (usuario: Login) => {
        this.loading = false;

        // ✅ valida estado
        if (usuario.estado !== 'ACTIVO') {
          this.error = 'Tu usuario está inactivo. Contacta al administrador.';
          return;
        }

        // ✅ redirige
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.loading = false;
        // ✅ si backend manda message, lo mostramos
        this.error = err?.error?.message || 'Usuario o contraseña incorrectos';
      },
    });
  }
}

