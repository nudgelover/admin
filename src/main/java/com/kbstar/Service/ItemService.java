package com.kbstar.Service;

import com.kbstar.dto.Item;
import com.kbstar.dto.ItemSearch;
import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements KBService<Integer, Item> {

    @Autowired
    ItemMapper mapper;


    @Override
    public void register(Item item) throws Exception {
        mapper.insert(item);
    }

    @Override
    public void remove(Integer s) throws Exception {
       mapper.delete(s);
    }

    @Override
    public void modify(Item item) throws Exception {
        mapper.update(item);
    }

    @Override
    public Item get(Integer s) throws Exception {
       return mapper.select(s);
    }

    @Override
    public List<Item> get() throws Exception {
        return mapper.selectall();
    }
    public List<Item> search(ItemSearch is) throws Exception{
        return mapper.search(is);
    }
}


