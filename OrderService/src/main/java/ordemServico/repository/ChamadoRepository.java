package ordemServico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ordemServico.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
