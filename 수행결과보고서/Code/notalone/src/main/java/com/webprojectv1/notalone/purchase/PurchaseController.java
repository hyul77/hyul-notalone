package com.webprojectv1.notalone.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import com.webprojectv1.notalone.cart.Cart;
import com.webprojectv1.notalone.cart.CartItem;
import com.webprojectv1.notalone.cart.CartService;
import com.webprojectv1.notalone.user.SiteUser;
import com.webprojectv1.notalone.user.UserService;

import java.util.*;

@Controller
@RequestMapping(value = "/mypage")
public class PurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String purchaseSelectAll(Principal principal, Model model) {
        // 로그인이 되어있는 유저의 id와 주문 내역에 접속하는 id가 같아야 함
        SiteUser siteUser = userService.getUser(principal.getName());
        // if (siteUser.getId() == id) {
            // 로그인 되어 있는 유저에 해당하는 구매내역 가져오기
            List<PurchaseItem> purchaseItemList = purchaseService.findUserPurchaseItemList(siteUser.getId());

            // 총 주문 개수
            int totalCount = 0;
            for (PurchaseItem purchaseItem : purchaseItemList) {
                totalCount += purchaseItem.getPurchaseCount();
            }

            model.addAttribute("totalCount", totalCount);
            model.addAttribute("purchaseItemList", purchaseItemList);
            model.addAttribute("user", userService.selectUserOne(siteUser.getId()));

            return "mypage";
        // }
        // // 로그인 id와 주문 내역 접속 id가 같지 않는 경우
        // else {
        //     return "redirect:/";
        // }
    }
    
    @Transactional
    @PostMapping("/insertPurchase")
    public String  insertPurchase(Principal principal) {
        // 유저 정보
        SiteUser siteUser = userService.getUser(principal.getName());

        // 유저 카트 찾기
        // Cart cart = siteUser.getCart();
        Cart userCart = cartService.findUserCart(siteUser.getId());

        // 유저 카트 안에 상품 리스트
        List<CartItem> userCartItemList = cartService.allUserCartView(userCart);

        List<PurchaseItem> purchaseItemList = new ArrayList<>();
        for (CartItem cartItem : userCartItemList) {
            // 재고 감소
            cartItem.getProduct().setProductStock(cartItem.getProduct().getProductStock() - cartItem.getCartItemCount());

            // purchase, purchaseItem 에 담기
            PurchaseItem purchaseItem = purchaseService.addCartPurchase(cartItem.getProduct().getProductId(), siteUser.getId(), cartItem);

            purchaseItemList.add(purchaseItem);
        }

        purchaseService.addPurchase(siteUser, purchaseItemList);
        cartService.allCartItemDelete(siteUser.getId());

        // model.addAttribute("userCartItemList", userCartItemList);
        // model.addAttribute("user", userService.selectUserOne(siteUser.getId()));
        return "redirect:/mypage";
    }
}
