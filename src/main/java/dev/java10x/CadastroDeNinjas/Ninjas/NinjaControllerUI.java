package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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



    @GetMapping("/delete/{id}")
    public String deletaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaId(id);
        return "redirect:/ninjas/ui/listar";
    }
}
