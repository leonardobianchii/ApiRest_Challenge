package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.MotoDto;
import br.monitoramento.motu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/view/motos")
public class MotoWebController {

    @Autowired
    private MotoService motoService;

    // LISTA
    @GetMapping
    public String list(Model model) {
        model.addAttribute("motos", motoService.findAll());
        return "moto/lista";
    }

    // NOVO
    @GetMapping("/new")
    public String createForm(Model model) {
        // Agora o ID não é forçado como null
        model.addAttribute("moto", new MotoDto(null, null, null, "", "", 0));
        model.addAttribute("modoEdicao", false);
        return "moto/form";
    }

    // EDITAR
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("moto", motoService.findById(id));
        model.addAttribute("modoEdicao", true);
        return "moto/form";
    }

    // SALVAR
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("moto") MotoDto moto,
                       BindingResult binding,
                       RedirectAttributes ra) {

        if (binding.hasErrors()) {
            return "moto/form";
        }

        // se o ID já existir, faz update; caso contrário, cria nova
        if (moto.id() == null || motoService.findAll().stream().noneMatch(m -> m.id().equals(moto.id()))) {
            motoService.create(moto);
            ra.addFlashAttribute("msgSucesso", "view.moto.criada");
        } else {
            motoService.update(moto.id(), moto);
            ra.addFlashAttribute("msgSucesso", "view.moto.atualizada");
        }

        return "redirect:/view/motos";
    }

    // DELETAR
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        motoService.delete(id);
        ra.addFlashAttribute("msgSucesso", "view.moto.excluida");
        return "redirect:/view/motos";
    }
}
