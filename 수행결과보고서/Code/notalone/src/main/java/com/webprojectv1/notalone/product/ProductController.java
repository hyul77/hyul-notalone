package com.webprojectv1.notalone.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // 상품 등록 페이지 (GET)
    // @GetMapping("")
    // public String insertProductForm() { 
    //     return ""; 
    // }

    // 상품 등록 (POST)
    // @PostMapping
    // public String insertProduct(Product productDto) { 
    //     productService.inserProduct(productDto);
    //     return "/"; 
    // }

    // 상품 수정 페이지 (GET)
    @GetMapping("/product/update/{productId}")
    public String updateProductForm(@PathVariable("productId") Long productId, Model model) {
        model.addAttribute("item", productService.selectProductOne(productId));
        return "/product/update";
    }

    // 상품 수정 (POST)
    // @PostMapping("/product/update/{productId}")
    // public String updateProduct(Product product, @PathVariable("productId") Long productId) { 
    //     productService.updateProduct(product, productId);
    //     return "redirect:/";
    // }

    // 상품 상세 페이지
    @GetMapping(value = "/product/detail/{productId}")
    public String productDetail(Model model, @PathVariable("productId") Long productId) {
        model.addAttribute("product", productService.selectProductOne(productId));
        return "product-detail";
    }


    // 상품 삭제
    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return "/";
    }

}
