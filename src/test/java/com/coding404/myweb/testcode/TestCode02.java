package com.coding404.myweb.testcode;

import com.coding404.myweb.command.DemoMemberVO;
import com.coding404.myweb.command.DemoOrderVO;
import com.coding404.myweb.product.service.ProductMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestCode02 {

    private static final Logger log = LoggerFactory.getLogger(TestCode02.class);

    @Autowired
    public TestMapper testMapper;

    @Test
    public void testCode01() {
        List<DemoOrderVO> list = testMapper.manyToOne();
        System.out.println(list);


    }
    @Test
    public void testCode02() {
        DemoMemberVO vo = testMapper.oneToMany();
        System.out.println(vo);

    }
}
