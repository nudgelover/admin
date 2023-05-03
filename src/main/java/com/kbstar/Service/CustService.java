package com.kbstar.Service;

import com.kbstar.dto.Cust;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.CustMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustService implements KBService<String, Cust> {

    @Autowired
    CustMapper mapper;

    //Autowired란 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    @Override
    public void register(Cust cust) throws Exception {
//        mapper.insert(cust);
        mapper.insert(cust);
        log.info("send mail..." + cust.getId());

    }

    @Override
    public void remove(String s) throws Exception {
        mapper.delete(s);

    }

    @Override
    public void modify(Cust cust) throws Exception {
        mapper.update(cust);
        log.info("send mail..." + cust.getId());

    }

    @Override
    public Cust get(String s) throws Exception {
        return mapper.select(s);
    }

    @Override
    public List<Cust> get() throws Exception {
        return mapper.selectall();
    }


}
