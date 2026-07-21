package ordemServico.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ordemServico.domain.Chamado;
import ordemServico.domain.dto.ChamadoDTO;
import ordemServico.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<ChamadoDTO> buscaPorId(@PathVariable Long id){
		Chamado chamado = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}

}
