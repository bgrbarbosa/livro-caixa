import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Movimento } from 'src/app/models/movimento';
import { MovimentoService } from 'src/app/services/movimento.service';

@Component({
  selector: 'app-movimento-list',
  templateUrl: './movimento-list.component.html',
  styleUrls: ['./movimento-list.component.css']
})
export class MovimentoListComponent implements OnInit {

  id: Number;
  ELEMENT_DATA: Movimento[] = []
  FILTERED_DATA: Movimento[] = []
  
  displayedColumns: string[] = ['Id', 'Data', 'Obs', 'Status', 'Total Caixa', 'Ações'];
  dataSource = new MatTableDataSource<Movimento>(this.ELEMENT_DATA);


  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: MovimentoService,
    private router: Router,
    private route: ActivatedRoute,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.initFindAll()    
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
  }

  initFindAll() {
    this.service.findAll().subscribe(resposta => {
      this.ELEMENT_DATA = resposta
      this.dataSource = new MatTableDataSource<Movimento>(resposta);
      this.dataSource.paginator = this.paginator;
      this.orderByStatus('ABERTO')
    })
  }

  findAll() {
    this.service.findAll().subscribe(resposta => {
      this.ELEMENT_DATA = resposta
      this.dataSource = new MatTableDataSource<Movimento>(resposta);
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  orderByStatus(status: any): void{
    let list: Movimento[] = []
    this.ELEMENT_DATA.forEach(element => {
      if(element.status == status){
        list.push(element)
      }
    });
    this.FILTERED_DATA = list;
    this.dataSource = new MatTableDataSource<Movimento>(list);
    this.dataSource.paginator = this.paginator;
  }

  editMovimento(id: number): void {
    this.router.navigate(['/movimento/update', id]);
  }

  deleteMovimento(id: number): void {
    if (confirm('Tem certeza que deseja deletar?')) {
      this.service.delete(id).subscribe(() =>{
        this.router.navigate(['/movimento']);
      }
      );
    }

  }


}


