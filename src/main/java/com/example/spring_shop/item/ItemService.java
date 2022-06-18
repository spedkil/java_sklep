package com.example.spring_shop.item;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }



    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public void addNewItem(Item item) {
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if(!exists){
            throw new IllegalStateException("item with id" + itemId + "does not exist");
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, String name, float price) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalStateException("item with id" + itemId + "does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(item.getName(), name)){
            item.setName(name);
        }

        if(price > 0.0f && !Objects.equals(item.getPrice(), price)){
            item.setPrice(price);
        }
    }

    public Item getItem(Long itemId) {
        Optional<Item> res = itemRepository.findById(itemId);
        if(!res.isPresent()){
            throw new IllegalStateException("item with id" + itemId + "does not exist");
        }
        return res.get();
    }
}
