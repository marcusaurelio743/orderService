package ordemServico.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ordemServico.domain.Chamado;
import ordemServico.exeption.ObjectNotFundException;
import ordemServico.repository.ChamadoRepository;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado findById(Long id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		
		return chamado.orElseThrow(()-> new ObjectNotFundException("chamado não encontrado id: "+id));
	}
}
