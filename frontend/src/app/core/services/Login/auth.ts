import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Login } from '../../../../Model/Login';

@Injectable({ providedIn: 'root' })
export class Auth {
  private apiUrl = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<Login> {
    return this.http.post<Login>(this.apiUrl, { username, password }).pipe(
      tap((user) => {
        // SSR-safe: solo navegador
        if (typeof window !== 'undefined' && window.localStorage) {
          localStorage.setItem('user', JSON.stringify(user));
        }
      })
    );
  }

  logout(): void {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.removeItem('user');
    }
  }

  getUser(): Login | null {
    if (typeof window === 'undefined' || !window.localStorage) return null;
    const raw = localStorage.getItem('user');
    return raw ? (JSON.parse(raw) as Login) : null;
  }

  isLoggedIn(): boolean {
    return !!this.getUser();
  }
}
