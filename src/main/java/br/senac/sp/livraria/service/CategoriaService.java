package br.senac.sp.livraria.service;

import br.senac.sp.livraria.model.Categoria;
import br.senac.sp.livraria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(int id){
        return categoriaRepository.findById(id);
    }

    public boolean excluir(int id){
        // Verificar se o livro existe no banco de dados
        Optional<Categoria> categoriaExcluir = categoriaRepository.findById(id);

        // Se a editora existir, ele será apagado, caso contrário, retornamos false
        if (categoriaExcluir.isPresent()){
            categoriaRepository.deleteById(id);
            return true;
        }

        return false;

    }

    public Categoria atualizar(Categoria novaCategoria){
        Optional<Categoria> categoriaAtual = categoriaRepository.findById(novaCategoria.getId());

        if (categoriaAtual.isPresent()){
            return categoriaRepository.save(novaCategoria);
        }

        return null;

    }

}
