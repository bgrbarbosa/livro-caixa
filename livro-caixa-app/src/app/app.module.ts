import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Para trabalhar com formulários no Angular 12
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

// Para realizar requisições HTTP
import { HttpClientModule } from '@angular/common/http';

// Imports para componentes do Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';


import { NavComponent } from './component/nav/nav.component';
import { HeaderComponent } from './component/header/header.component';
import { HomeComponent } from './component/home/home.component';
import { MovimentoListComponent } from './component/movimento/movimento-list/movimento-list.component';
import { LoginComponent } from './component/login/login.component';
import { ToastrModule } from 'ngx-toastr';
import { AuthInterceptorProvider } from './interceptor/auth.interceptor';
import { MovimentoCreateComponent } from './component/movimento/movimento-create/movimento-create.component';
import { LancamentoCreateComponent } from './component/lancamento/lancamento-create/lancamento-create.component';
import { ItemLancamentoComponent } from './component/lancamento/item-lancamento/item-lancamento.component';
import { ItemLancamentoUpdateComponent } from './component/lancamento/item-lancamento-update/item-lancamento-update.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HeaderComponent,
    HomeComponent,
    MovimentoListComponent,
    LoginComponent,
    MovimentoCreateComponent,
    LancamentoCreateComponent,
    ItemLancamentoComponent,
    ItemLancamentoUpdateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
     // Forms
     FormsModule,
     ReactiveFormsModule,
     // Requisições http
     HttpClientModule,
     // Angular Material
     MatFormFieldModule,
     MatPaginatorModule,
     MatCheckboxModule,
     MatSnackBarModule,
     MatToolbarModule,
     MatSidenavModule,
     MatButtonModule,
     MatSelectModule,
     MatInputModule,
     MatRadioModule,
     MatTableModule,
     MatIconModule,
     MatListModule,
     MatCardModule,
     ToastrModule.forRoot({
      timeOut:4000,
      closeButton:true,
      progressBar:true
     })
  ],
  providers: [AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
