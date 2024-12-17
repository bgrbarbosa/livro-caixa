package br.com.bgrbarbosa.livro_caixa_api.service;

import br.com.bgrbarbosa.livro_caixa_api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

	@Autowired
	private LancamentoRepository repository;

	public void instanciaDB() {

/*		Movimento m1 = new Movimento(1L, new Date("23/11/2024"), "Abertura de Caixa", );

		Lancamentos l1 = new Lancamentos(1L, Tipo.ENTRADA, "Entrada de Caixa", 50.00), new Movimento();
		Lancamentos l2 = new Lancamentos(2L, Tipo.SAIDA,   "Compra de produtos de higiene", -15.00);
		Lancamentos l3 = new Lancamentos(3L, Tipo.SAIDA,   "Pagamento de fornecedor", -500.00);
		Lancamentos l4 = new Lancamentos(4L, Tipo.SAIDA,   "Pagamento de contador", -350.00);
		Lancamentos l5 = new Lancamentos(5L, Tipo.ENTRADA,  "Recebimento de clientes", 1500.00);
		Lancamentos l6 = new Lancamentos(6L, Tipo.ENTRADA,  "Recebimento de serviços prestados", 5000.00);
		Lancamentos l7 = new Lancamentos(7L, Tipo.ENTRADA,  "Reforço de troco", 50.00);
		Lancamentos l8 = new Lancamentos(8L, Tipo.SAIDA,   "Sangria", -1000.00);
		repository.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8));*/
	}
}
