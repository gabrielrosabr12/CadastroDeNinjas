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
    @PostMapping("/criar")
    public String criarNinja(){ return "Ninja criado";}

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
    @PutMapping("/alterarid")
    public String alterarNinhjaPorId() {
        return "Alterando ninja por ID";
    }


    // Deletar ninja (DELETE)
    @DeleteMapping("/deleteid")
    public String deletandoNinjaEspecifico() {
        return "Deletando o ninja por ID";
    }


}
