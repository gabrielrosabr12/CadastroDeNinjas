package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/boasvindas")
    public String boasvindas(){
        return "Olá mundo";
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarMissoes(){
        List<MissoesModel> missoes = missoesService.listarMissoes();

        if (!missoes.isEmpty()){
            return ResponseEntity.ok(missoes);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista de missoes se encontra vazia");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoesId(@PathVariable Long id){
        MissoesModel missoesModel = missoesService.listarMissoesId(id);

        if (missoesModel != null){
            return ResponseEntity.ok(missoesModel);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarMissao(@PathVariable Long id){
        MissoesModel missoesModel = missoesService.listarMissoesId(id);

        if (missoesModel == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id da missão não encontrado nos registros");
        }

        if (missoesModel.getNinjas().isEmpty()){
            missoesService.deletarMissoes(missoesModel);
            return ResponseEntity.ok("Missão deletada com sucesso!");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível deletar pois ainda há ninjas vinculados a está missão!");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id,@RequestBody MissoesModel missaoAtualizada){
        MissoesModel missaoAnterior = missoesService.listarMissoesId(id);

        if (missaoAnterior != null){
            missoesService.atualizarMissoes(missaoAnterior,missaoAtualizada);
            return ResponseEntity.ok("Missão Atualizada com sucesso!");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível atualizar pois esta missão não foi encontrada nos registros!");
        }
    }


}
