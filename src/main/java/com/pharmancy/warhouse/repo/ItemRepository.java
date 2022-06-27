package com.pharmancy.warhouse.repo;

import com.pharmancy.warhouse.essences.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("select i from Item i where i.title like %?1%")
    public List<Item> search(String keyword);

    @Query("select i from Item i where (:priceMax is null or i.price = :priceMax)")
    public Iterable<Item> count(int priceMax);


}
