package br.senac.sp.livraria.repository;

import br.senac.sp.livraria.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    public List<Livro> findByAnoEdicaoLessThan(int anoEdicao);
    public List<Livro> findByAutorStartingWith(String autor);

    @Query("SELECT l FROM Livro l WHERE l.preco BETWEEN :valorInicial AND :valorFinal ORDER BY l.preco DESC")
    List<Livro> listarPrecoEntre(@Param("valorIncial") double valorInicial,@Param("valorFinal") double valorFinal);

    @Query("SELECT l FROM Livro l WHERE l.id = :id")
    Optional<Livro> buscarPorId(@Param("id") int id);
}