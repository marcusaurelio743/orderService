package ordemServico.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ordemServico.domain.Cliente;
import ordemServico.domain.dto.ClienteDTO;
import ordemServico.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<ClienteDTO> buscaPorId(@PathVariable Long id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscaTodos(){
		List<Cliente> clientes = clienteService.buscaTodos();
		List<ClienteDTO> clientesDto = clientes.stream().map(x-> new ClienteDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clientesDto);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO clienteDTO){
		ClienteDTO clienteSalvo = clienteService.created(clienteDTO);
		
		return new ResponseEntity<ClienteDTO>(clienteSalvo, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
		ClienteDTO cliente = clienteService.update(id,clienteDTO);
		
		return new ResponseEntity<ClienteDTO>(cliente, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		clienteService.deletar(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
