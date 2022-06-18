import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { usuario } from '../model/usuario';
import { usuarioLogin } from '../model/usuarioLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  login(usarioLogin: usuarioLogin) : Observable<usuarioLogin>{
    return this.http.post<usuarioLogin>('http://localhost:8080/usuarios/logar', usuarioLogin)
  }

  cadastro(usuario: usuario): Observable<usuario>{
    return this.http.post<usuario>('http://localhost:8080/usuarios/cadastrar', usuario)
    
  }
}
