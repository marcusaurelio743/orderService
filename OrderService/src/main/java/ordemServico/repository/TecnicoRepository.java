package ordemServico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ordemServico.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
