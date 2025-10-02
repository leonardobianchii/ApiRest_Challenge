package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.LocalizacaoMotoRFIDDTO;
import br.monitoramento.motu.dto.MotoDTO;
import br.monitoramento.motu.dto.SensorRFIDDTO;
import br.monitoramento.motu.service.LocalizacaoMotoRFIDService;
import br.monitoramento.motu.service.MotoService;
import br.monitoramento.motu.service.SensorRFIDService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/localizacoes")
public class LocalizacaoMotoRFIDWebController {

    private final LocalizacaoMotoRFIDService localizacaoService;
    private final MotoService motoService;
    private final SensorRFIDService sensorService;

    public LocalizacaoMotoRFIDWebController(LocalizacaoMotoRFIDService localizacaoService,
                                            MotoService motoService,
                                            SensorRFIDService sensorService) {
        this.localizacaoService = localizacaoService;
        this.motoService = motoService;
        this.sensorService = sensorService;
    }

    private void carregarCombos(Model model) {
        List<MotoDTO> motos = motoService.listarMotos();
        List<SensorRFIDDTO> sensores = sensorService.listarSensores(); // nome conforme seu service
        model.addAttribute("motos", motos);
        model.addAttribute("sensores", sensores);
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("localizacoes", localizacaoService.listarLocalizacoes());
        return "localizacoes/list";
    }

    @GetMapping("/novo")
    public String createForm(Model model) {
        model.addAttribute("localizacao", new LocalizacaoMotoRFIDDTO());
        carregarCombos(model);
        return "localizacoes/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("localizacao") LocalizacaoMotoRFIDDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra,
                         Model model) {
        if (binding.hasErrors()) {
            carregarCombos(model);
            return "localizacoes/form";
        }
        localizacaoService.adicionarLocalizacao(dto);
        ra.addFlashAttribute("mensagemSucesso", "localizacao.salva.sucesso");
        return "redirect:/localizacoes";
    }

    @GetMapping("/{id}/editar")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            LocalizacaoMotoRFIDDTO dto = localizacaoService.obterLocalizacao(id);
            model.addAttribute("localizacao", dto);
            carregarCombos(model);
            return "localizacoes/form";
        } catch (RuntimeException e) {
            ra.addFlashAttribute("mensagemErro", "localizacao.nao.encontrada");
            return "redirect:/localizacoes";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute("localizacao") LocalizacaoMotoRFIDDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra,
                         Model model) {
        if (binding.hasErrors()) {
            carregarCombos(model);
            return "localizacoes/form";
        }
        localizacaoService.atualizarLocalizacao(id, dto);
        ra.addFlashAttribute("mensagemSucesso", "localizacao.atualizada.sucesso");
        return "redirect:/localizacoes";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        localizacaoService.deletarLocalizacao(id);
        ra.addFlashAttribute("mensagemSucesso", "localizacao.excluida.sucesso");
        return "redirect:/localizacoes";
    }
}
