/*
 * Copyright (C) 2016 João
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.joaops.controller;

import br.com.joaops.model.Pessoa;
import br.com.joaops.model.Telefone;
import br.com.joaops.repository.Pessoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author João
 */
@Controller
@RequestMapping(value = "/formulario")
public class FormularioController {
    
    @Autowired
    private Pessoas pessoas;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView mav = new ModelAndView("formulario/index");
        mav.addObject("pessoa", new Pessoa());
        mav.addObject("pessoas", pessoas.todos());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, params = {"addTelefone"})
    public ModelAndView addTelefone(Pessoa pessoa) {
        ModelAndView mav = new ModelAndView("formulario/index");
        pessoa.getTelefones().add(new Telefone());
        mav.addObject("pessoa", pessoa);
        mav.addObject("pessoas", pessoas.todos());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String salvar(Pessoa pessoa) {
        pessoas.adicionar(pessoa);
        return "redirect:/formulario";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/pessoa/{nome}")
    public ModelAndView mostrarPessoa(@PathVariable String nome) {
        ModelAndView mav = new ModelAndView("formulario/pessoa/index");
        for(Pessoa pessoa : pessoas.todos()) {
            if(pessoa.getNome().equalsIgnoreCase(nome)) {
                mav.addObject("pessoa", pessoa);
                break;
            }
        }
        return mav;
    }
}