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

    // Procurar ninja por id (READ)
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosNinjas() {
        return ninjaService.listarNinjas();
    }


    // Mostrar ninja por id  (READ)
    @GetMapping("/procurarid")
    public String procurarPorId() {
        return "Esse é o ninja especifico";
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
