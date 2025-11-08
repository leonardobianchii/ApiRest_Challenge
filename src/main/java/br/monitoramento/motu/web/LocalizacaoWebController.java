package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.LocalizacaoMotoRfidDto;
import br.monitoramento.motu.service.LocalizacaoMotoRfidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/view/localizacoes")
public class LocalizacaoWebController {

    @Autowired
    private LocalizacaoMotoRfidService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("localizacoes", service.findAll());
        return "localizacao/lista";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("localizacao", new LocalizacaoMotoRfidDto(null, null, null, null));
        model.addAttribute("modoEdicao", false);
        return "localizacao/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("localizacao", service.findById(id));
        model.addAttribute("modoEdicao", true);
        return "localizacao/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("localizacao") LocalizacaoMotoRfidDto dto,
                       BindingResult binding,
                       RedirectAttributes ra) {
        if (binding.hasErrors()) return "localizacao/form";

        if (dto.id() == null) {
            service.create(dto);
            ra.addFlashAttribute("msgSucesso", "view.localizacao.criada");
        } else {
            service.update(dto.id(), dto);
            ra.addFlashAttribute("msgSucesso", "view.localizacao.atualizada");
        }
        return "redirect:/view/localizacoes";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("msgSucesso", "view.localizacao.excluida");
        return "redirect:/view/localizacoes";
    }
}
