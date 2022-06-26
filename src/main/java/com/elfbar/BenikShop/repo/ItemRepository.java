package com.elfbar.BenikShop.repo;

import com.elfbar.BenikShop.essences.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByTitleLike(String name);
}
