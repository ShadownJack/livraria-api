package br.senac.sp.livraria.service;

import br.senac.sp.livraria.model.Editora;
import br.senac.sp.livraria.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    private EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public Editora salvar(Editora editora){
        return editoraRepository.save(editora);
    }
    public List<Editora> listarEditoras(){
        return editoraRepository.findAll();
    }

    public Optional<Editora> buscarPorId(int id){
        return editoraRepository.findById(id);
    }

    public boolean excluir(int id){
        // Verificar se o livro existe no banco de dados
        Optional<Editora> editoraExcluir = editoraRepository.findById(id);

        // Se a editora existir, ele será apagado, caso contrário, retornamos false
        if (editoraExcluir.isPresent()){
            editoraRepository.deleteById(id);
            return true;
        }

        return false;

    }

    public Editora atualizar(Editora novaEditora){

        Optional<Editora> livroAtual = editoraRepository.findById(novaEditora.getId());

        if (livroAtual.isPresent()){
            return editoraRepository.save(novaEditora);
        }

        return null;

    }
}