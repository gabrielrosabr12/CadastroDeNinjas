package dev.java10x.CadastroDeNinjas.Ninjas;

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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaCriado){
        return ninjaService.criarNinja(ninjaCriado);
    }

    // Listar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosNinjas() {
        return ninjaService.listarNinjas();
    }


    // Mostrar ninja por id  (READ)
    // @PathVariable é quando eu quero que o usuario me envie algo que vai estar na URL
    // essa variavel fica dentro da chaves {}
    @GetMapping("/listar/{id}")
    public NinjaModel listarPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    //PutMapping serve para atualizar no banco de dados
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinhjaPorId(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado) {
        return ninjaService.atualizarNinja(id,ninjaAtualizado);
    }


    // Deletar ninja (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deletaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaId(id);
    }


}
