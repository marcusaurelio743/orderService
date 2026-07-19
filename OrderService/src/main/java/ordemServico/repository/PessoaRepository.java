package ordemServico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ordemServico.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
