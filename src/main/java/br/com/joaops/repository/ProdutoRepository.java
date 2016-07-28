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

import br.com.joaops.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author João
 */
@Repository
public class ProdutoRepository {
    
    @PersistenceContext
    private EntityManager manager;
    
    public List<Produto> all() {
        return manager.createQuery("from Produto", Produto.class).getResultList();
    }
    
    public void save(Produto produto) {
        manager.persist(produto);
    }
    
    public Produto findById(Long id) {
        return manager.find(Produto.class, id);
    }
    
    public void remove(Produto produto) {
        manager.remove(produto);
    }
    
    public void update(Produto produto) {
        manager.merge(produto);
    }
    
}