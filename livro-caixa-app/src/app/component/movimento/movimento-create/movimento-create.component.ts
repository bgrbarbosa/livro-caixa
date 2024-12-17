import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Movimento } from 'src/app/models/movimento';
import { MovimentoService } from 'src/app/services/movimento.service';

@Component({
  selector: 'app-movimento-create',
  templateUrl: './movimento-create.component.html',
  styleUrls: ['./movimento-create.component.css']
})
export class MovimentoCreateComponent implements OnInit {

  id: Number;

  movimento: Movimento = {
    idMovimento:'',
    data:'',
    obs:'',
    status:'',
    lancamentos:[]
  }

  data: FormControl =  new FormControl(null, Validators.required);
  obs: FormControl = new FormControl(null, Validators.minLength(3));
  status: FormControl = new FormControl(null, Validators.required);
  
  constructor(
    private service: MovimentoService,
    private toast: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {}

  // openLancamento() {
  //   this.dialog.open(DialogDataExampleDialog, {
  //     data: {
  //       animal: 'panda'
  //     }
  //   });
  // }

  create(): void {
      this.service.create(this.movimento).subscribe((response) => {
      this.toast.success('Movimento cadastrado com sucesso', 'Cadastro');
      this.id = response.idMovimento;
      this.router.navigate([`lancamento/itens/${this.id}`])
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
    this.router.navigate([`movimento`])
  }

  

}
