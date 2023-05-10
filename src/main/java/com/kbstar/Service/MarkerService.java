package com.kbstar.Service;


import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.MarkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MarkerService implements KBService<Integer, Marker> {
    /**
     * 등록 및 가입 진행
     * argument : Object
     * return : null
     *
     * @param marker
     */

    @Autowired
    MarkerMapper mapper;

    @Override
    public void register(Marker marker) throws Exception {
        mapper.insert(marker);
    }

    @Override
    public void remove(Integer k) throws Exception {
        mapper.delete(k);
    }

    @Override
    public void modify(Marker marker) throws Exception {
        mapper.update(marker);
    }

    @Override
    public Marker get(Integer k) throws Exception {
        return mapper.select(k);
    }

    @Override
    public List<Marker> get() throws Exception {
        return mapper.selectall();
    }

    public List<Marker> getMyloc(String loc) throws Exception{
        return mapper.getmyloc(loc);
    }

    public List<Marker> search(MarkerSearch ms) throws Exception{
        return mapper.search(ms);
    }
}
