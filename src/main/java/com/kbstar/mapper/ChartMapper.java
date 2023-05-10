package com.kbstar.mapper;

import com.kbstar.dto.Cart;
import com.kbstar.dto.Chart;
import com.kbstar.frame.KBMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ChartMapper  {
    //원래는 데이터분석 전용 Mapper를 만들어서 상속받아야하는게 합리적이긴하다.
    //chart는 crud가 없으니까 mapper 상속 out!
    List<Chart> getMonthlyTotal();

}
