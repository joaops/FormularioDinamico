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

import br.com.joaops.repository.ProdutoRepository;
import br.com.joaops.model.Produto;
import javax.transaction.Transactional;
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
@Transactional
@RequestMapping(value = "/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository repo;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("produto/index");
        mav.addObject("produtos", repo.all());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/adicionar")
    public ModelAndView adicionar() {
        ModelAndView mav = new ModelAndView("produto/adicionar");
        mav.addObject("produto", new Produto());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/adicionar")
    public ModelAndView salvar(@Valid Produto produto, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("redirect:/produto");
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("produto/adicionar");
            mav.addObject("produto", produto);
            mav.addObject("org.springframework.validation.BindingResult.produto", bindingResult);
        } else {
            repo.save(produto);
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("produto/editar");
        Produto produto = repo.findById(id);
        if (produto!=null) {
            mav.addObject("produto", produto);
        } else {
            mav = new ModelAndView("redirect:/produto");
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editar")
    public ModelAndView salvarEditar(@Valid Produto produto, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("redirect:/produto");
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("produto/editar");
            mav.addObject("produto", produto);
            mav.addObject("org.springframework.validation.BindingResult.produto", bindingResult);
        } else {
            repo.update(produto);
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("redirect:/produto");
        Produto produto = repo.findById(id);
        if (produto!=null) {
            repo.remove(produto);
        }
        return mav;
    }
    
}