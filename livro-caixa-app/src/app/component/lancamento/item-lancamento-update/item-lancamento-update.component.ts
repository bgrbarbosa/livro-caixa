import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Lancamentos } from 'src/app/models/lancamentos';
import { LancamentoService } from 'src/app/services/lancamento.service';

@Component({
  selector: 'app-item-lancamento-update',
  templateUrl: './item-lancamento-update.component.html',
  styleUrls: ['./item-lancamento-update.component.css']
})
export class ItemLancamentoUpdateComponent implements OnInit {

  id: Number;
  
  lancamento: Lancamentos = {
    idLancamento: 0,
    tipo:         '',
    descricao:    '',
    valor:        0,
    movimento:    null,
  }


  constructor(
    private service: LancamentoService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.findLancamentoById();
    console.log("Lancamento: ", this.lancamento)
  }

  findLancamentoById(): void {
    this.service.findById(this.id).subscribe(resposta => {
      this.lancamento = resposta;
    })
  }

  update(): void {
    console.log('Lancamento', this.lancamento)
    this.service.update(this.lancamento).subscribe(() => {
      this.toast.success('Lancamento atualizado com sucesso', 'Update');
      this.router.navigate([`movimento/update/${this.lancamento.movimento.idMovimento}`])
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
    this.router.navigate([`movimento/update/${this.lancamento.movimento.idMovimento}`])
  }

}
