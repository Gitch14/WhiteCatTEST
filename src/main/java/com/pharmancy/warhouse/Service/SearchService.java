package com.pharmancy.warhouse.Service;

import com.pharmancy.warhouse.essences.Item;
import com.pharmancy.warhouse.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private ItemRepository repo;

    public Iterable<Item> listName(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return (Iterable<Item>) repo.findAll();
    }

}
