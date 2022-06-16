package com.example.sklep_java.Item;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    public List<Item> getItems(){
        return List.of(
                new Item(
                        1L,
                        "milk",
                        3.99f
                )
        );
    }
}
