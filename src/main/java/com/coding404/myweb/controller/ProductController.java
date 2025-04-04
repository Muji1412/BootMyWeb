package com.coding404.myweb.controller;

import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") String prodId, Model model) {

        ProductVO vo = productService.getDetail(prodId);

        model.addAttribute("vo", vo);

        return "product/productDetail";
    }
    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }
//    @GetMapping("/productList")
//    public String productList(Model model) {
//        String prodWriter = "admin";
//        List<ProductVO> list = productService.getList(prodWriter);
//        model.addAttribute("list", list);
//
//        log.info(list.toString());
//
//        return "product/productList";
//    }
    @GetMapping("/productList")
    public String productList(Model model ,Criteria cri) {


        String prodWriter = "admin";
        List<ProductVO> list = productService.getList(prodWriter, cri);

        int total = productService.getTotal(prodWriter, cri); //total 행 개수 가져옴
        PageVO pageVO = new PageVO(cri,total); // 페이지네이션
        model.addAttribute("pageVO", pageVO);
        model.addAttribute("list", list);

        log.info(cri.toString());

        return "product/productList";
    }

    @PostMapping("/registForm")
    public String registForm(ProductVO vo, RedirectAttributes ra) {

        int result = productService.productRegist(vo);
        // 1이면 성공, 0이면 실패
        if (result > 0) {
            ra.addFlashAttribute("msg", "정상등록됨");
        } else {
            ra.addFlashAttribute("msg", "등록 실패했습니다.");
        }
        return "redirect:/product/productList";
    }
    @PostMapping("/updateForm")
    public String updateForm(ProductVO vo, RedirectAttributes ra) {

        log.info(vo.toString());

        productService.productUpdate(vo);
        return "redirect:/product/productDetail?prodId=" + vo.getProdId();
    }

    @PostMapping("/deleteForm")
    public String deleteForm(ProductVO vo, RedirectAttributes ra) {
        int result = productService.productDelete(vo);

        if (result > 0) {
            ra.addFlashAttribute("msg", "삭제 성공");
        } else {
            ra.addFlashAttribute("msg", "삭제 실패");
        }
        return "redirect:/product/productList";
    }
}
