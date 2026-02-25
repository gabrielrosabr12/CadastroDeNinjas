package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
