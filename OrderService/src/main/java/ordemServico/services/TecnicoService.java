package ordemServico.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ordemServico.domain.Pessoa;
import ordemServico.domain.Tecnico;
import ordemServico.domain.dto.TecnicoDTO;
import ordemServico.exeption.DataIntegrityViolationException;
import ordemServico.exeption.ObjectNotFundException;
import ordemServico.repository.PessoaRepository;
import ordemServico.repository.TecnicoRepository;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(() ->new ObjectNotFundException("Objeto não encontrado!! id: "+id));
	}
	
	public List<Tecnico> buscaTodos(){
		return repository.findAll();
	}

	public TecnicoDTO created(TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		validarCpfEmail(tecnicoDTO);
		tecnico = repository.save(tecnico);
		
		return new TecnicoDTO(tecnico);
	}

	private void validarCpfEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já Cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("E-Mail já Cadastrado no sistema");
		}
	}

	public TecnicoDTO update(Long id, @Valid TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = findById(id);
		validarCpfEmail(tecnicoDTO);
		tecnico = new Tecnico(tecnicoDTO);
		tecnico.setId(id);
		tecnico = repository.save(tecnico);
		return new TecnicoDTO(tecnico);
	}
}
