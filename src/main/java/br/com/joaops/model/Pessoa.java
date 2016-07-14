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
package br.com.joaops.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João
 */
public class Pessoa {
    
    private String nome;
    private Integer idade;
    private List<Telefone> telefones;
    
    public Pessoa() {
        nome = "";
        idade = 0;
        telefones = new ArrayList<>();
    }
    
    public Pessoa(String nome, Integer idade, List<Telefone> telefones) {
        this.nome = nome;
        this.idade = idade;
        this.telefones = telefones;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getIdade() {
        return idade;
    }
    
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    
    public List<Telefone> getTelefones() {
        return telefones;
    }
    
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    
}