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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("formulario/index");
        mav.addObject("pessoas", pessoas.todos());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/listar/{id}")
    public ModelAndView listar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario/listar");
        for(Pessoa pessoa : pessoas.todos()) {
            if(pessoa.getId().equals(id)) {
                mav.addObject("pessoa", pessoa);
                break;
            }
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/adicionar")
    public ModelAndView adicionar() {
        ModelAndView mav = new ModelAndView("formulario/adicionar");
        mav.addObject("pessoa", new Pessoa());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/adicionar", params = {"addTelefone"})
    public ModelAndView novoTelefone(Pessoa pessoa) {
        ModelAndView mav = new ModelAndView("formulario/adicionar");
        pessoa.getTelefones().add(new Telefone());
        mav.addObject("pessoa", pessoa);
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/adicionar")
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult) {
        ModelAndView mav;
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("formulario/adicionar");
            mav.addObject("pessoa", pessoa);
            mav.addObject("org.springframework.validation.BindingResult.pessoa", bindingResult);
        } else {
            mav = new ModelAndView("redirect:/formulario");
            pessoas.adicionar(pessoa);
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario/editar");
        mav.addObject("pessoa", new Pessoa());
        for(Pessoa pessoa : pessoas.todos()) {
            if(pessoa.getId().equals(id)) {
                mav.addObject("pessoa", pessoa);
                break;
            }
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editar", params = {"addTelefone"})
    public ModelAndView novoTelefoneEditar(Pessoa pessoa) {
        ModelAndView mav = new ModelAndView("formulario/editar");
        mav.addObject("id", pessoa.getId());
        pessoa.getTelefones().add(new Telefone());
        mav.addObject("pessoa", pessoa);
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editar")
    public ModelAndView salvarEdição(@Valid Pessoa pessoa, BindingResult bindingResult) {
        ModelAndView mav;
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("formulario/editar/"+pessoa.getId());
            mav.addObject("pessoa", pessoa);
            mav.addObject("org.springframework.validation.BindingResult.pessoa", bindingResult);
        } else {
            mav = new ModelAndView("redirect:/formulario");
            int id = 0;
            for (int i=0; i<pessoas.todos().size(); i++) {
                if (pessoas.todos().get(i).getId().equals(pessoa.getId())) {
                    id = i;
                    break;
                }
            }
            pessoas.todos().set(id, pessoa);
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("redirect:/formulario");
        Pessoa p = new Pessoa();
        for(Pessoa pessoa : pessoas.todos()) {
            if(pessoa.getId().equals(id)) {
                p = pessoa;
                break;
            }
        }
        pessoas.todos().remove(p);
        return mav;
    }
    
}