package school.danceSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entity.PassStatus;
import school.danceSite.dao.entityservice.PassService;
import school.danceSite.service.PaymentService;

import javax.validation.Valid;

@Controller
@RequestMapping("passes")
public class PassesController {

    private final PaymentService paymentService;
    private final PassService passService;

    public PassesController(PaymentService paymentService, PassService passService) {
        this.paymentService = paymentService;
        this.passService = passService;
    }

    @GetMapping
    public String buypasspage(Model model) {
        model.addAttribute("passes", passService.getPasses());
        return "passes";
    }

    @PostMapping
    public String buypass(@ModelAttribute("client") @Valid Client client,
                          @ModelAttribute("passstatus") PassStatus passStatus,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signingup";
        }
        try {
            paymentService.pay(passStatus);
        } catch (Exception e) {
            // всплывающее окно "покупка не удалась"
        }
        return "mainpage";
    }
}
