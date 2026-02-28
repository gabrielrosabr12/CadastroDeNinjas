package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoesService;

    public MissoesControllerUI(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes",missoes);
        return "listarmissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarMissoesId(@PathVariable Long id, RedirectAttributes redirectAttributes,Model model){
        MissoesDTO missoesDTO = missoesService.listarMissoesId(id);

        if (missoesDTO != null){
            model.addAttribute("missao",missoesDTO);
            return "listarmissoesid";
        }
        else {
            redirectAttributes.addFlashAttribute("mensagem","Ninja não encontrado!");
            return "redirect:/missoes/ui/listar";
        }
    }

    @GetMapping("/criar")
    public String criarMissoes(Model model){
        model.addAttribute("missao",new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissoes(@ModelAttribute MissoesDTO modelDTO,RedirectAttributes redirectAttributes){
        missoesService.criarMissao(modelDTO);
        redirectAttributes.addFlashAttribute("mensagem","A missão: ("+modelDTO.getNome()+") foi salva com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizarMissoes(@PathVariable Long id,Model model,RedirectAttributes redirectAttributes){
        MissoesDTO missao = missoesService.listarMissoesId(id);

        if (missao != null){
            model.addAttribute("missao",missao);
            return "adicionarMissao";
        }
        else{
            redirectAttributes.addFlashAttribute("mensagem","Missao do id ("+id+") Não encontrada!");
            return "redirect:/missoes/ui/listar";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMissoes(@ModelAttribute MissoesDTO missoesDTO,@PathVariable Long id,Model model,RedirectAttributes redirectAttributes){
        missoesService.atualizarMissoes(id,missoesDTO);
        redirectAttributes.addFlashAttribute("mensagem", "A Missão: ("+missoesDTO.getNome()+") Foi atualizada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @PostMapping("/deletar/{id}")
    public String atualizarMissoes(@PathVariable Long id,RedirectAttributes redirectAttributes){
        missoesService.deletarMissoes(id);
        redirectAttributes.addFlashAttribute("mensagem", "A Missão do id ("+id+") Foi deletada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

}
