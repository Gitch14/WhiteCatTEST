package com.elfbar.BenikShop.captcha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Captcha {

    private final List<String> list = new ArrayList<String>(Arrays.asList("qwert1","ahb28fsa","p3fe@","p09kfQ"));


    public String getList() {
            int a = (int) (Math.random() * 3);
        return list.get(a);
    }
}
