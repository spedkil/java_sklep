package com.example.spring_shop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

//    @GetMapping
//    public List<Item> getItems(){
//        return itemService.getItems();
//    }

    @GetMapping("/")
    public String getItems(Model model){
        model.addAttribute("listItems", itemService.getItems());
        return "index";
    }

    @GetMapping("/management")
    public String manageItems(Model model){
        model.addAttribute("listItems", itemService.getItems());
        return "management";
    }

    @GetMapping("/management/new")
    public String newItemForm(Model model){
        model.addAttribute("item", new Item());
        model.addAttribute("pageTitle","Add New Item");
        return "item_form";
    }

    @PostMapping("/management/save")
    public String saveItem(Item item, RedirectAttributes redirectAttributes){
        itemService.addNewItem(item);
        redirectAttributes.addFlashAttribute("message", "Item has been saved successfully");
        return "redirect:/management";
    }

    @GetMapping("/management/edit/{itemId}")
    public String editItemForm(@PathVariable("itemId") Long itemId, Model model, RedirectAttributes redirectAttributes){
        Item item = itemService.getItem(itemId);
        model.addAttribute("item", item);
        model.addAttribute("pageTitle","Edit Item (ID: " + itemId + ")");
        return "item_form";
    }

    @GetMapping("/management/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") Long itemId, RedirectAttributes redirectAttributes){
        itemService.deleteItem(itemId);
        redirectAttributes.addFlashAttribute("message", "Item (" + itemId + ") has been deleted successfully");
        return "redirect:/management";
    }

////    @GetMapping(path="{itemId}")
////    public Optional<Item> getItem(@PathVariable("itemId") Long itemId){
////        return itemService.getItem(itemId);
////    }
//
//    @PostMapping
//    public void registerNewItem(@RequestBody Item item){
//        itemService.addNewItem(item);
//    }
//
////    @DeleteMapping(path = "/management/delete/{itemId}")
////    public void deleteItem(@PathVariable("itemId") Long itemId){
////        itemService.deleteItem(itemId);
////    }
//
//    @PutMapping(path="{itemId}")
//    public void updateItem(
//            @PathVariable("itemId") Long itemId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) Float price
//    ){
//        itemService.updateItem(itemId, name, price);
//    }
}
