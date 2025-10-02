package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.SensorRFIDDTO;
import br.monitoramento.motu.dto.FilialDepartamentoDTO;
import br.monitoramento.motu.service.SensorRFIDService;
import br.monitoramento.motu.service.FilialDepartamentoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sensores")
public class SensorRFIDWebController {

    private final SensorRFIDService sensorService;
    private final FilialDepartamentoService filialService;

    public SensorRFIDWebController(SensorRFIDService sensorService,
                                   FilialDepartamentoService filialService) {
        this.sensorService = sensorService;
        this.filialService = filialService;
    }

    private void carregarCombos(Model model) {
        List<FilialDepartamentoDTO> filiais = filialService.listarFiliais();
        model.addAttribute("filiais", filiais);
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sensores", sensorService.listarSensores());
        return "sensores/list";
    }

    @GetMapping("/novo")
    public String createForm(Model model) {
        model.addAttribute("sensor", new SensorRFIDDTO());
        carregarCombos(model);
        return "sensores/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("sensor") SensorRFIDDTO dto,
                         BindingResult binding, Model model,
                         RedirectAttributes ra) {
        if (binding.hasErrors()) {
            carregarCombos(model);
            return "sensores/form";
        }
        sensorService.criarSensor(dto);
        ra.addFlashAttribute("mensagemSucesso", "sensor.salvo.sucesso");
        return "redirect:/sensores";
    }

    @GetMapping("/{id}/editar")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            SensorRFIDDTO dto = sensorService.obterSensor(id);
            model.addAttribute("sensor", dto);
            carregarCombos(model);
            return "sensores/form";
        } catch (RuntimeException e) {
            ra.addFlashAttribute("mensagemErro", "sensor.nao.encontrado");
            return "redirect:/sensores";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute("sensor") SensorRFIDDTO dto,
                         BindingResult binding, Model model,
                         RedirectAttributes ra) {
        if (binding.hasErrors()) {
            carregarCombos(model);
            return "sensores/form";
        }
        sensorService.atualizarSensor(id, dto);
        ra.addFlashAttribute("mensagemSucesso", "sensor.atualizado.sucesso");
        return "redirect:/sensores";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        sensorService.deletarSensor(id);
        ra.addFlashAttribute("mensagemSucesso", "sensor.excluido.sucesso");
        return "redirect:/sensores";
    }
}
