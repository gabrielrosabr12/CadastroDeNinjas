package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    // PostMapping metodo post para enviar dados ao banco de dados
    // mandamos em forma de json serializado para o banco
    // @RequestBody significa que no corpo da requisição vai ter os dados do json
    @PostMapping("/criar")
    public ResponseEntity<?> criarNinja(@RequestBody NinjaDTO ninjaCriado){
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninjaCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja criado "+ninjaDTO.getNome()+" com o id: "+ninjaDTO.getId());
    }

    // Listar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<?> mostrarTodosNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        if (!ninjas.isEmpty()){
            return ResponseEntity.ok(ninjas);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não possuimos nenhum registro de ninjas.");
    }


    // Mostrar ninja por id  (READ)
    // @PathVariable é quando eu quero que o usuario me envie algo que vai estar na URL
    // essa variavel fica dentro da chaves {}
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        NinjaDTO ninjaDTO = ninjaService.listarNinjasPorId(id);

        if (ninjaDTO != null) {
            return ResponseEntity.ok(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id "+id+" não encontrado. :(");
    }

    // Alterar dados dos ninjas (UPDATE)
    //PutMapping serve para atualizar no banco de dados
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinhjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
        if (ninjaService.listarNinjasPorId(id) != null){
            NinjaDTO ninja = ninjaService.atualizarNinja(id,ninjaAtualizado);
            return ResponseEntity.ok("O ninja "+ninja.getNome()+" foi atualizado, seu (ID) é: "+id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja do (ID): "+id+" não foi encontrado.");
    }


    // Deletar ninja (DELETE)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletaPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaId(id);
            return ResponseEntity.ok("Ninja do id "+id+" deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");

    }


}
