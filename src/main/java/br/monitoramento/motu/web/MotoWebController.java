package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.MotoDTO;
import br.monitoramento.motu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motos")
public class MotoWebController {

    private final MotoService motoService;

    public MotoWebController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("motos", motoService.listarMotos());
        return "motos/list";
    }

    @GetMapping("/novo")
    public String createForm(Model model) {
        model.addAttribute("moto", new MotoDTO());
        // TODO (opcional): popular selects de modelo/filial quando você enviar os services
        // model.addAttribute("modelos", modeloService.listarModelos());
        // model.addAttribute("filiais", filialService.listarFiliais());
        return "motos/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("moto") MotoDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra,
                         Model model) {
        if (binding.hasErrors()) {

        }
        motoService.criarMoto(dto);
        ra.addFlashAttribute("mensagemSucesso", "moto.salva.sucesso");
        return "redirect:/motos";
    }


    @GetMapping("/{id}/editar")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            MotoDTO dto = motoService.obterMoto(id);
            model.addAttribute("moto", dto);
            return "motos/form";
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("mensagemErro", "moto.nao.encontrada");
            return "redirect:/motos";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute("moto") MotoDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra,
                         Model model) {
        if (binding.hasErrors()) {
            // re-popular selects se você usar
            return "motos/form";
        }
        motoService.atualizarMoto(id, dto);
        ra.addFlashAttribute("mensagemSucesso", "moto.atualizada.sucesso");
        return "redirect:/motos";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        motoService.deletarMoto(id);
        ra.addFlashAttribute("mensagemSucesso", "moto.excluida.sucesso");
        return "redirect:/motos";
    }
}
