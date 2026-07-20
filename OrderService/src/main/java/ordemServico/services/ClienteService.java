package ordemServico.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ordemServico.domain.Cliente;
import ordemServico.domain.Pessoa;
import ordemServico.domain.dto.ClienteDTO;
import ordemServico.exeption.DataIntegrityViolationException;
import ordemServico.exeption.ObjectNotFundException;
import ordemServico.repository.ClienteRepository;
import ordemServico.repository.PessoaRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() ->new ObjectNotFundException("Objeto não encontrado!! id: "+id));
	}

	public List<Cliente> buscaTodos() {
		
		return clienteRepository.findAll();
	}

	public ClienteDTO created(@Valid ClienteDTO clienteDTO) {
		validarCpfEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		cliente = clienteRepository.save(cliente);
		
		return new ClienteDTO(cliente);
	}
	
	private void validarCpfEmail(ClienteDTO clienteDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
		
		if((obj.isPresent()) && (obj.get().getId() != clienteDTO.getId())) {
			throw new DataIntegrityViolationException("CPF já Cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("E-Mail já Cadastrado no sistema");
		}
	}

	public ClienteDTO update(Long id, @Valid ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		validarCpfEmail(clienteDTO);
		
		Cliente cliente =new Cliente(clienteDTO);
		
		cliente = clienteRepository.save(cliente);
		
		return new ClienteDTO(cliente);
	}

	public void deletar(Long id) {
		Cliente cliente = findById(id);
		
		if(cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui Chamados em aberto e não pode ser Apagado!!");
		}else {
			clienteRepository.delete(cliente);
		}
	}
	
	

}
