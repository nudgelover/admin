package com.kbstar.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    private int id;
    private String name;
    private int price;
    private String imgname;
    private Date rdate;
    private MultipartFile img;
//   int가 아닌 Integer로 하는 이유, search할 때 price에 null값이 들어와야하는데 int는 null이 들어올 수 없으니 객체인 integer로!
    public Item(int id, String name, int price, String imgname, Date rdate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgname = imgname;
        this.rdate = rdate;
    }
}
