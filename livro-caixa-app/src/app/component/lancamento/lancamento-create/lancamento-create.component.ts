import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { Lancamentos } from 'src/app/models/lancamentos';
import { Movimento } from 'src/app/models/movimento';
import { LancamentoService } from 'src/app/services/lancamento.service';
import { MovimentoService } from 'src/app/services/movimento.service';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-lancamento-create',
  templateUrl: './lancamento-create.component.html',
  styleUrls: ['./lancamento-create.component.css']
})
export class LancamentoCreateComponent implements OnInit {
  
  id: Number;
  movimento?: Movimento;
  lancamento: Lancamentos;
  totalCaixa:Number;
  controle: Boolean;
  status: Boolean;  

  ELEMENT_DATA: Lancamentos[] = [];
  FILTERED_DATA: Lancamentos[] = [];
   
  displayedColumns: string[] = ['Id', 'Tipo', 'Descrição', 'Valor', 'Ações'];  
  dataSource = new MatTableDataSource<Lancamentos>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: MovimentoService,
    private lancamentoService: LancamentoService,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.findById();
    this.controle = false;
  }

  toggleControle(): void {
    this.controle = !this.controle;
  }

  getTotalCaixa(){
    this.totalCaixa = 0;
    if (this.movimento && this.movimento.lancamentos) {
        this.movimento.lancamentos.forEach(x => {
            this.totalCaixa = Number(this.totalCaixa) + Number(x.valor);
        });
    }
  }

  findById(): void {
    this.service.findById(this.id).subscribe(resposta => {
        this.movimento = resposta;
        this.status = (this.movimento.status === 'FECHADO') ? true : false
        this.getTotalCaixa(); 
        this.ELEMENT_DATA = resposta.lancamentos;
        this.dataSource = new MatTableDataSource<Lancamentos>(resposta.lancamentos);
        this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  insertNew(): void {
    this.router.navigate([`/lancamento/itens/${this.movimento.idMovimento}`])
  }

  deleteLancamento(aux: number): void {
    if (confirm('Tem certeza que deseja deletar?')) {
      this.lancamentoService.delete(aux).subscribe(() =>{
        this.reloadCurrentRoute();
        this.toast.success('Lancamento excluido com sucesso', 'Update');
      });
    }
  }

  reloadCurrentRoute() {
    const currentUrl = this.router.url; // Obtém a URL atual
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]); // Navega para a rota atual
    });
  }

  updateMovimento(): void {
    this.service.update(this.movimento).subscribe(response => {
      this.toggleControle();
      this.reloadCurrentRoute();
    }, error => {
      this.toast.error('Não foi possível atualizar o registro')
    });
  }

  updateLancamento(id: number): void {
    this.router.navigate([`lancamento/update/${id}`]);
  }

  cancel(): void {
    this.router.navigate([`movimento`]);
  }




}
