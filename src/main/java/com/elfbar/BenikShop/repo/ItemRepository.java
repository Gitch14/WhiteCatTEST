package com.elfbar.BenikShop.repo;

import com.elfbar.BenikShop.essences.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("select i from Item i where i.title like %?1%")
    public List<Item> search(String keyword);

    public default List<Item> listAll(String keyword) {
        if (keyword != null) {
            return search(keyword);
        }
        return (List<Item>) findAll();
    }
}
