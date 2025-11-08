package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.FilialDepartamentoDto;
import br.monitoramento.motu.service.FilialDepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/view/filiais")
public class FilialWebController {

    @Autowired
    private FilialDepartamentoService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("filiais", service.findAll());
        return "filial/lista";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("filial", new FilialDepartamentoDto(null, ""));
        model.addAttribute("modoEdicao", false);
        return "filial/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("filial", service.findById(id));
        model.addAttribute("modoEdicao", true);
        return "filial/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("filial") FilialDepartamentoDto dto,
                       BindingResult binding,
                       RedirectAttributes ra) {
        if (binding.hasErrors()) return "filial/form";

        if (dto.id() == null) {
            service.create(dto);
            ra.addFlashAttribute("msgSucesso", "view.filial.criada");
        } else {
            service.update(dto.id(), dto);
            ra.addFlashAttribute("msgSucesso", "view.filial.atualizada");
        }
        return "redirect:/view/filiais";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("msgSucesso", "view.filial.excluida");
        return "redirect:/view/filiais";
    }
}
