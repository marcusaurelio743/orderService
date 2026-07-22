package ordemServico.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> listaTodos(){
		List<Chamado> chamados = chamadoService.findAll();
		
		List<ChamadoDTO> chamadosDtos = chamados.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(chamadosDtos);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> salvar( @RequestBody ChamadoDTO obj){
		Chamado chamado = chamadoService.created(obj);
		
		return new ResponseEntity<ChamadoDTO>(new ChamadoDTO(chamado), HttpStatus.CREATED);
	}

}
