package org.example.lab2_20210795.Controllers;

import org.example.lab2_20210795.Models.Entities.Receta;
import org.example.lab2_20210795.Models.Repositories.RecetaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/receta")
public class RecetaController {
    final RecetaRepository recetaRepository;


    public RecetaController(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    @GetMapping("/crear")
    public String crearReceta() {
        return "nuevaReceta";
    }

    @GetMapping("/listar")
    public String showRecetas(Model model) {
        List<Receta> listaRecetas = recetaRepository.findAll();
        model.addAttribute("listaRecetas", listaRecetas);
        return "listaRecetas";
    }

    @GetMapping("/borrar")
    public String borrarReceta(Model model, @RequestParam("idReceta") int id, RedirectAttributes redirectAttributes) {
        try {
            recetaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Se borró la receta");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "No se pudo borrar la receta");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/receta/listar";
    }

    @GetMapping("/ver_detalles")
    public String VerReceta(Model model,
                                  @RequestParam("idReceta") int id) {
        Optional<Receta> optinalReceta = recetaRepository.findById(id);

        if(optinalReceta.isPresent()) {
            Receta receta = optinalReceta.get();
            model.addAttribute("receta", receta);
            return "VerDetallesReceta";
        }else{
            return "redirect:/receta/listar";
        }
    }
    @PostMapping("/guardar")
    public String GuardarReceta(Receta receta) {
        recetaRepository.save(receta);
        return "redirect:/receta/listar";
    }
}
