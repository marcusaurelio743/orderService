package ordemServico.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ordemServico.domain.Tecnico;
import ordemServico.domain.dto.TecnicoDTO;
import ordemServico.services.TecnicoService;

@RestController
@RequestMapping(value="/tecnicos")
public class TecnicoResource {
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value="{id}")
	public ResponseEntity<TecnicoDTO> buscaPorId(@PathVariable Long id){
		Tecnico obj = tecnicoService.findById(id);	
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> listaTodos(){
		List<Tecnico> tecnicos = tecnicoService.buscaTodos();
		List<TecnicoDTO> tecnicosDto = tecnicos.stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tecnicosDto);
	}
	
	
}
