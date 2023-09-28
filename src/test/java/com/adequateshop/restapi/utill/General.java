package com.adequateshop.restapi.utill;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.thedeanda.lorem.LoremIpsum;

public class General extends BaseApiTest {
    public static String getDummyName(){
        String dummyName = LoremIpsum.getInstance().getName();
        return dummyName;
    }
    public static String getDummyEmail(){
        String dummyEmail = LoremIpsum.getInstance().getEmail();
        return dummyEmail;
    }
}
