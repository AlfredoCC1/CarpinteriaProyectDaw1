import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rol } from '../../../../Model/Rol';

@Injectable({ providedIn: 'root' })
export class RolService {
  private baseUrl = 'http://localhost:8080/api/roles';

  constructor(private http: HttpClient) {}

  private authHeaders(): { headers: HttpHeaders } {
    const auth =
      typeof window !== 'undefined' ? localStorage.getItem('auth') : null;

    return {
      headers: new HttpHeaders({
        Authorization: auth ? `Basic ${auth}` : '',
      }),
    };
  }

  listar(): Observable<Rol[]> {
    return this.http.get<Rol[]>(this.baseUrl, this.authHeaders());
  }
}
