import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { API_CONFIG } from 'src/app/config/api.config';
import { Lancamentos } from 'src/app/models/lancamentos';
import { Movimento } from 'src/app/models/movimento';
import { LancamentoService } from 'src/app/services/lancamento.service';
import { MovimentoService } from 'src/app/services/movimento.service';

@Component({
  selector: 'app-item-lancamento',
  templateUrl: './item-lancamento.component.html',
  styleUrls: ['./item-lancamento.component.css']
})
export class ItemLancamentoComponent implements OnInit {

  id: Number;
  movimento?: Movimento;
  
  lancamento: Lancamentos = {
    idLancamento:         '',
    tipo:       '',
    descricao:  '',
    valor:      0,
    movimento: null,
  }

  

  // tipo: FormControl =  new FormControl(null, Validators.required);
  // descricao: FormControl = new FormControl(null, Validators.minLength(3));
  // valor: FormControl = new FormControl(null, Validators.required);

  constructor(
    private service: LancamentoService,
    private movimentoservice: MovimentoService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {    
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.findMovimentoById();
  }

  findMovimentoById(): void {
    this.movimentoservice.findById(this.id).subscribe(resposta => {
      this.movimento = resposta;
    })
  }

  create(): void {
      this.lancamento.movimento = this.movimento;
      this.service.create(this.lancamento).subscribe((response) => {
      this.toast.success('Movimento cadastrado com sucesso', 'Cadastro');
      this.cleanItemLancamento();
      this.router.navigate([`movimento/update/${this.id}`])
    }, ex => {
      if(ex.error.errors) {
        ex.error.errors.forEach(element => {
          this.toast.error(element.message);
        });
      } else {
        this.toast.error(ex.error.message);
      }
    })
    
  }

  cancel(){
    this.router.navigate([`movimento/update/${this.id}`])
  }

  cleanItemLancamento():void {
    this.lancamento.idLancamento = '',
    this.lancamento.tipo =       '',
    this.lancamento.descricao =  '',
    this.lancamento.valor = 0,
    this.lancamento.movimento = null
  }

}
