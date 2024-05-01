package br.com.fiap.pizzaria.domain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/site", ""})
public class SiteController {

    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Value("${site.id}")
    private Long idPizzaria;

    // Injetar o serviço da pizzaria, se necessário
    // @Autowired
    // private PizzariaService service;

    @GetMapping(value = {"", "/", "/index.html", "index"})
    public ModelAndView getSite() {
        // Verificar se o idPizzaria é nulo ou negativo
        var id = (idPizzaria != null && idPizzaria > 0) ? idPizzaria : 1;

        //  var pizzaria = service.findById(id);
        //  if (Objects.isNull(pizzaria)) return new ModelAndView("/404");

        log.info("Usuário acessando o site: {}", idPizzaria);

        ModelAndView mv = new ModelAndView("/index");
        // mv.addObject("pizzaria", pizzaria);
        return mv;
    }

    @GetMapping(value = {"/cardapio.html", "/cardapio"})
    public ModelAndView getCardapio() {
        var id = (idPizzaria != null && idPizzaria > 0) ? idPizzaria : 1;
        //   var pizzaria = service.findById( id );

        log.info("Usuário acessando o cardápio da pizzaria: {}", idPizzaria);

        ModelAndView mv = new ModelAndView("/cardapio");
        //   mv.addObject( "pizzaria", pizzaria );
        return mv;
    }

    @GetMapping(value = {"/carrinho.html", "/carrinho", "/cart"})
    public ModelAndView getCarrinho() {
        var id = (idPizzaria != null && idPizzaria > 0) ? idPizzaria : 1;
        // var pizzaria = service.findById( id );

        log.info("Usuário acessando o carrinho de compras da pizzaria: {}", idPizzaria);

        ModelAndView mv = new ModelAndView("/carrinho");
        // mv.addObject( "pizzaria", pizzaria );
        return mv;
    }
}
