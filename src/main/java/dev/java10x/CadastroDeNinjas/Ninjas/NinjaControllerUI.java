package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Rota ficaria http://localhost:8080/ninjas/ui/listar
    @GetMapping("/listar")
    public String mostrarTodosNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        model.addAttribute("ninjas",ninjas);
        return "listarninjas";
    }

    @GetMapping("/listar/{id}")
    public String listarPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninjaDTO = ninjaService.listarNinjasPorId(id);

        if (ninjaDTO != null) {
            model.addAttribute("ninja",ninjaDTO);

            return "listarninjaid";
        } else{
            model.addAttribute("mensagem","Ninja não encontrado");
            return "listarninjas";
        }

    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model){
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem","Ninja criado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }


    @GetMapping("/alterar/{id}")
    public String alterarNinhjaPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            // Podemos reaproveitar a mesma tela de adicionar!
            return "adicionarNinja";
        } else {
            return "redirect:/ninjas/ui/listar";
        }
    }

    // 2. ADICIONE ESTE AQUI (usado para RECEBER o clique do botão "Salvar" do formulário)
    @PostMapping("/alterar/{id}")
    public String salvarNinjaAlterado(@PathVariable Long id, @ModelAttribute NinjaDTO ninjaAtualizado, RedirectAttributes redirectAttributes) {

        // Chama o serviço para atualizar o ninja no banco de dados
        ninjaService.atualizarNinja(id, ninjaAtualizado);

        // Envia a mensagem verde de sucesso para a tela de listagem
        redirectAttributes.addFlashAttribute("mensagem", "Ninja " + ninjaAtualizado.getNome() + " atualizado com sucesso!");

        // Redireciona de volta para a tabela
        return "redirect:/ninjas/ui/listar";
    }


    @GetMapping("/delete/{id}")
    public String deletaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaId(id);
        return "redirect:/ninjas/ui/listar";
    }
}
