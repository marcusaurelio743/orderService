package ordemServico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ordemServico.domain.Chamado;
import ordemServico.domain.Cliente;
import ordemServico.domain.Tecnico;
import ordemServico.domain.enums.Prioridade;
import ordemServico.domain.enums.Status;
import ordemServico.repository.ChamadoRepository;
import ordemServico.repository.ClienteRepository;
import ordemServico.repository.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		Cliente cliente = new Cliente(null, "Jose", "123", "jose@email.com", encoder.encode("admin"));
		Tecnico tecnico = new Tecnico(null, "Maria", "5555", "Maria@email.com", encoder.encode("123"));
		
		Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Reparo em pc", "desmontar e consertar placa mae", tecnico, cliente);
		clienteRepository.save(cliente);
		tecnicoRepository.save(tecnico);
		chamadoRepository.save(chamado);
		
	}

}
