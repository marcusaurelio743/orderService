package ordemServico.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import ordemServico.domain.Chamado;
import ordemServico.domain.Cliente;
import ordemServico.domain.Tecnico;
import ordemServico.domain.dto.ChamadoDTO;
import ordemServico.domain.enums.Prioridade;
import ordemServico.domain.enums.Status;
import ordemServico.exeption.ObjectNotFundException;
import ordemServico.repository.ChamadoRepository;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Long id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		
		return chamado.orElseThrow(()-> new ObjectNotFundException("chamado não encontrado id: "+id));
	}
	@GetMapping
	public List<Chamado> findAll() {
		
		return chamadoRepository.findAll();
	}
	public Chamado created(ChamadoDTO obj) {
		
		return chamadoRepository.save(newChamado(obj));
	}
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
			
		}
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setPrioridade(Prioridade.toPerfil(obj.getPrioridade()));
		chamado.setStatus(Status.toPerfil(obj.getStatus()));
		chamado.setDescricao(obj.getDescricao());
		chamado.setTitulo(obj.getTitulo());
		
		return chamado;
	}
	public Chamado update(Long id, @Valid ChamadoDTO obj) {
		obj.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(obj);
		return chamadoRepository.save(oldObj);
	}
	
	
}
