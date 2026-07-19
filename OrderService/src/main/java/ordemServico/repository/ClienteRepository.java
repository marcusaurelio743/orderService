package ordemServico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ordemServico.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
