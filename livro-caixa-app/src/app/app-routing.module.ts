import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './component/nav/nav.component';
import { HomeComponent } from './component/home/home.component';
import { MovimentoListComponent } from './component/movimento/movimento-list/movimento-list.component';
import { LoginComponent } from './component/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { MovimentoCreateComponent } from './component/movimento/movimento-create/movimento-create.component';
import { LancamentoCreateComponent } from './component/lancamento/lancamento-create/lancamento-create.component';
import { ItemLancamentoComponent } from './component/lancamento/item-lancamento/item-lancamento.component';
import { ItemLancamentoUpdateComponent } from './component/lancamento/item-lancamento-update/item-lancamento-update.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: '', component: NavComponent, canActivate: [AuthGuard], children:[
      {path: 'home', component: HomeComponent},
      {path: 'movimento', component: MovimentoListComponent},
      {path: 'movimento/create', component:MovimentoCreateComponent},
      { path:'movimento/update/:id', component: LancamentoCreateComponent },
      { path:'lancamento/itens/:id', component: ItemLancamentoComponent },
      { path:'lancamento/update/:id', component: ItemLancamentoUpdateComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
