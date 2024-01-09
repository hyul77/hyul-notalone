package com.webprojectv1.notalone.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webprojectv1.notalone.product.Product;
import com.webprojectv1.notalone.product.ProductService;
import com.webprojectv1.notalone.user.SiteUser;
import com.webprojectv1.notalone.user.UserService;

import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.*;

@Slf4j
@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getcartItemList(Model model, Principal principal) {
        log.info("getcartItemList 들어옴");
        SiteUser siteUser = userService.getUser(principal.getName());
        Cart cart = siteUser.getCart();

        if (cart == null) {
            return "redirect:/";
        }

        // 장바구니에 들어있는 아이템 모두 가져오기
        List<CartItem> cartItemList = cartService.allUserCartView(cart);

        // 장바구니에 들어있는 상품들의 총 가격
        int totalPrice = 0;
        for (CartItem cartitem : cartItemList) {
            totalPrice += cartitem.getCartItemCount() * cartitem.getProduct().getProductPrice();
        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalCount", cart.getCartCount());
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("user", userService.selectUserOne(siteUser.getId()));

        return "cart";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/insertCart/{productId}")
    public String insertCart(@PathVariable("productId") Long productId, int amount, Principal principal){
        SiteUser siteUser = userService.getUser(principal.getName());
        Product product = productService.selectProductOne(productId);

        cartService.insertCart(siteUser, product, amount);

        return "redirect:/cart";
    }

    public void allCartItemDelete(long id) {
        cartService.allCartItemDelete(id);
    }

    // @PostMapping("/update")    
    // public String updateCartItem(@ModelAttribute CartItem cartItemDto){
    //     log.info("updateCartItem");
    //     cartItemService.updateCartItem(cartItemDto);
    //     return "redirect:/";
    // }

    // @PostMapping("/delete")    
    // public String deleteCartItem(@ModelAttribute CartItem cartItemDto){
    //     log.info("deleteCartItem");
    //     cartItemService.deleteCartItem(cartItemDto.getCartItemId());
    //     return "redirect:/";
    // }
}
