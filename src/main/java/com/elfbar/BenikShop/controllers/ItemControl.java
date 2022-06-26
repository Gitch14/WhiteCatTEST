package com.elfbar.BenikShop.controllers;


import com.elfbar.BenikShop.repo.ItemRepository;
import com.elfbar.BenikShop.essences.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Optional;


@Controller
public class ItemControl {

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/")
    public String item(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "shop";
    }

    @GetMapping("/login")
    public String login(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "LogOut";
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "admin-panel";
    }

    @GetMapping("/admin-panel/add")
    public String itemAdd(Model model){
        return "item-add";
    }


    @PostMapping("/admin-panel/add")
    public String itemAddDB(@RequestParam String title, @RequestParam String imgUrl, @RequestParam int count, @RequestParam double price){
        Item item = new Item(title,imgUrl,count, price);
        itemRepository.save(item);
        return "redirect:/admin-panel";
    }


    @GetMapping("/admin-panel/{id}/edit")
    public String itemEdit(@PathVariable(value = "id") long id, Model model){
        if (!itemRepository.existsById(id)){
            return "redirect:/item";
        }


        Optional<Item> item1 = itemRepository.findById(id);
        ArrayList<Item> shopItem = new ArrayList<>();
        item1.ifPresent(shopItem::add);
        model.addAttribute("item1", shopItem);
        return "item-edit";
    }


    @PostMapping("/admin-panel/{id}/edit")
    public String itemUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String imgUrl,@RequestParam double price, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        item.setTitle(title);
        item.setImgUrl(imgUrl);
        item.setPrice(price);
        itemRepository.save(item);

        return "redirect:/admin-panel";
    }



    @GetMapping("/admin-panel/{id}/itemPlusCount")
    public String itemBuyPlus(@PathVariable(value = "id") long id, Model model){
        if (!itemRepository.existsById(id)){
            return "redirect:/admin-panel";
        }


        Optional<Item> item1 = itemRepository.findById(id);
        ArrayList<Item> shopItem1 = new ArrayList<>();
        item1.ifPresent(shopItem1::add);
        model.addAttribute("item1", shopItem1);
        return "item-plus";
    }


    @PostMapping("/admin-panel/{id}/itemPlusCount")
    public String itemCountPlus(@PathVariable(value = "id") long id, @RequestParam int count, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        item.setCount(count + item.getCount());
        itemRepository.save(item);

        return "redirect:/admin-panel";
    }


    @GetMapping("/admin-panel/{id}/itemMinusCount")
    public String itemBuyMinus(@PathVariable(value = "id") long id, Model model){
        if (!itemRepository.existsById(id)){
            return "redirect:/admin-panel";
        }


        Optional<Item> item2 = itemRepository.findById(id);
        ArrayList<Item> shopItem2 = new ArrayList<>();
        item2.ifPresent(shopItem2::add);
        model.addAttribute("item2", shopItem2);
        return "item-minus";
    }


    @PostMapping("/admin-panel/{id}/itemMinusCount")
    public String itemCountMinus(@PathVariable(value = "id") long id, @RequestParam int count, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        if (item.getCount() >= count) {
            item.setCount(item.getCount() - count);
            itemRepository.save(item);
        }

        return "redirect:/admin-panel";
    }


    @PostMapping("/admin-panel/{id}/remove")
    public String itemDelete(@PathVariable(value = "id") long id, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        itemRepository.delete(item);

        return "redirect:/admin-panel";
    }

}
