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
package br.com.joaops.repository;

import br.com.joaops.model.Pessoa;
import br.com.joaops.model.Telefone;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author João
 */
@Repository
public class Pessoas {
    
    private static final List<Pessoa> LISTA_PESSOAS = new ArrayList<>();
    
    static {
        Pessoa p = new Pessoa(1L, "João", LocalDate.of(1994, 8, 10), new ArrayList<Telefone>());
        p.getTelefones().add(new Telefone("88888888", "Celular"));
        p.getTelefones().add(new Telefone("88888889", "Celular2"));
        p.getTelefones().add(new Telefone("33333333", "Casa"));
        p.getTelefones().add(new Telefone("33333334", "Trabalho"));
        Pessoas.LISTA_PESSOAS.add(p);
        p = new Pessoa(2L, "Paulo", LocalDate.of(1993, Month.AUGUST, 10), new ArrayList<Telefone>());
        p.getTelefones().add(new Telefone("88889999", "Celular"));
        p.getTelefones().add(new Telefone("33334444", "Casa"));
        Pessoas.LISTA_PESSOAS.add(p);
    }
    
    public List<Pessoa> todos() {
        return LISTA_PESSOAS;
    }
    
    public void adicionar(Pessoa p) {
        Pessoas.LISTA_PESSOAS.add(p);
    }
    
}