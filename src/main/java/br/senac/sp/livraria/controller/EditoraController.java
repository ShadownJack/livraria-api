package br.senac.sp.livraria.controller;

import br.senac.sp.livraria.model.Editora;
import br.senac.sp.livraria.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/editoras")
public class EditoraController {

    private EditoraService editoraService;

    public EditoraController(EditoraService editoraService){
        this.editoraService = editoraService;
    }

    @PostMapping
    public ResponseEntity<Editora> salvar(@Valid @RequestBody Editora editora){
        System.out.println("Endpoint chamado...");
        Editora novaEditora = editoraService.salvar(editora);
        return ResponseEntity.status(201).body(novaEditora);
    }

    @GetMapping
    public ResponseEntity<List<Editora>> listarEditoras(){
        return ResponseEntity.ok(editoraService.listarEditoras());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Editora> buscarPorId(@PathVariable("codigo") int id){
        Optional<Editora> editoraEncontrada = editoraService.buscarPorId(id);

        if (editoraEncontrada.isPresent()){
            return ResponseEntity.ok(editoraEncontrada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") int id){
        if (editoraService.excluir(id)){
            return ResponseEntity.status(204).build();
        }

        Map<String, Object> erro = new HashMap<>();
        erro.put("status", 404);
        erro.put("mensagem", "A editora com ID " + id + " não foi encontrado!");
        erro.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(404).body(erro);

    }

    @PutMapping
    public ResponseEntity<Editora> atualizar(@RequestBody Editora editoraAtualizada){

        Editora novaEditora = editoraService.atualizar(editoraAtualizada);

        if (novaEditora != null){
            return ResponseEntity.status(200).body(novaEditora);
        }

        return ResponseEntity.status(404).build();

    }

}