package br.monitoramento.motu.web;

import br.monitoramento.motu.dto.SensorRfidDto;
import br.monitoramento.motu.service.SensorRfidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/view/sensores")
public class SensorWebController {

    @Autowired
    private SensorRfidService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sensores", service.findAll());
        return "sensor/lista";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("sensor", new SensorRfidDto(null, "", 1L, ""));
        model.addAttribute("modoEdicao", false);
        return "sensor/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("sensor", service.findById(id));
        model.addAttribute("modoEdicao", true);
        return "sensor/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("sensor") SensorRfidDto dto,
                       BindingResult binding,
                       RedirectAttributes ra) {
        if (binding.hasErrors()) return "sensor/form";

        if (dto.id() == null) {
            service.create(dto);
            ra.addFlashAttribute("msgSucesso", "view.sensor.criado");
        } else {
            service.update(dto.id(), dto);
            ra.addFlashAttribute("msgSucesso", "view.sensor.atualizado");
        }
        return "redirect:/view/sensores";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("msgSucesso", "view.sensor.excluido");
        return "redirect:/view/sensores";
    }
}

