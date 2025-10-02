package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.FilialDepartamentoDTO;
import br.monitoramento.motu.service.FilialDepartamentoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/filiais")
public class FilialDepartamentoWebController {

    private final FilialDepartamentoService service;

    public FilialDepartamentoWebController(FilialDepartamentoService service) {
        this.service = service;
    }

    // LISTA
    @GetMapping
    public String list(Model model) {
        model.addAttribute("filiais", service.listarFiliais());
        return "filiais/list";
    }

    // FORM NOVO
    @GetMapping("/novo")
    public String createForm(Model model) {
        model.addAttribute("filial", new FilialDepartamentoDTO());
        return "filiais/form";
    }

    // SALVAR NOVO
    @PostMapping
    public String create(@Valid @ModelAttribute("filial") FilialDepartamentoDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra) {
        if (binding.hasErrors()) {
            return "filiais/form";
        }
        service.criarFilialDepartamento(dto);
        ra.addFlashAttribute("mensagemSucesso", "filial.salva.sucesso");
        return "redirect:/filiais";
    }

    // FORM EDITAR
    @GetMapping("/{id}/editar")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            FilialDepartamentoDTO dto = service.obterFilialDepartamento(id);
            model.addAttribute("filial", dto);
            return "filiais/form";
        } catch (RuntimeException e) {
            ra.addFlashAttribute("mensagemErro", "filial.nao.encontrada");
            return "redirect:/filiais";
        }
    }

    // ATUALIZAR
    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute("filial") FilialDepartamentoDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra) {
        if (binding.hasErrors()) {
            return "filiais/form";
        }
        service.atualizarFilialDepartamento(id, dto);
        ra.addFlashAttribute("mensagemSucesso", "filial.atualizada.sucesso");
        return "redirect:/filiais";
    }

    // EXCLUIR
    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        service.deletarFilialDepartamento(id);
        ra.addFlashAttribute("mensagemSucesso", "filial.excluida.sucesso");
        return "redirect:/filiais";
    }
}
