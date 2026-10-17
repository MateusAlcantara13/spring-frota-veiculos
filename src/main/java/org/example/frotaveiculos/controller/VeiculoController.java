package org.example.frotaveiculos.controller;

import jakarta.validation.Valid;
import org.example.frotaveiculos.model.TipoVeiculo;
import org.example.frotaveiculos.model.Veiculo;
import org.example.frotaveiculos.repository.VeiculoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository repository;
    public VeiculoController(VeiculoRepository repository) {
        this.repository = repository;
    }

    // 1 - Listar tudo
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veiculos", repository.getTodos());
        return "veiculos";
    }

    // 2 - Mostrar formulário
    @GetMapping("/novo")
    public String mostrar(Model model) {
        model.addAttribute("postForm", new Veiculo());
        model.addAttribute("categorias", TipoVeiculo.values());
        return "form";
    }

    // 3 - Atualizar lista
    @PostMapping
    public String atualizar(@Valid @ModelAttribute("postForm") Veiculo veiculo, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("categorias", TipoVeiculo.values());
            return "form";
        }
        repository.adicionar(veiculo);
        return "redirect:/veiculos";
    }

    // 4 - buscar por id
    @GetMapping("/{id}")
    public String buscar(@PathVariable Long id, Model model){
        model.addAttribute("detalhes", repository.buscarPorId(id));
        return "detalhes";
    }

    // 5 - retornar livres
    @GetMapping("/motos")
    public String buscarMoto(Model model){
        model.addAttribute("motos", repository.listarMotos());
        return "motos";
    }

    // 6 - usar
    @PostMapping("/{id}/usar")
    public String usar(@PathVariable Long id){
        repository.buscarPorId(id).usar();
        return "redirect:/veiculos";
    }

    // 7 - devolver
    @PostMapping("/{id}/devolver")
    public String devolver(@PathVariable Long id){
        repository.buscarPorId(id).devolver();
        return "redirect:/veiculos";
    }

    // 8 - manutenção
    @PostMapping("/{id}/enviarManutencao")
    public String manutencao(@PathVariable Long id){
        repository.buscarPorId(id).enviarManutencao();
        return "redirect:/veiculos";
    }
}
