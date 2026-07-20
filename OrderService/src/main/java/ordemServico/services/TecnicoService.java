package ordemServico.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ordemServico.domain.Tecnico;
import ordemServico.exeption.ObjectNotFundException;
import ordemServico.repository.TecnicoRepository;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(() ->new ObjectNotFundException("Objeto não encontrado!! id: "+id));
	}
}
