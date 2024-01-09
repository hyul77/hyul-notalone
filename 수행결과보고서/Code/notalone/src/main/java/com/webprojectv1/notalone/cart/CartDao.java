package com.webprojectv1.notalone.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webprojectv1.notalone.product.IProductRepository;
import com.webprojectv1.notalone.product.Product;
import com.webprojectv1.notalone.user.SiteUser;

import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartDao {
    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    public void createCart(SiteUser user) {
        Cart cart = Cart.createCart(user);
        cartRepository.save(cart);
    }

    // C(Insert) & U(Update)
    public void insertCart(SiteUser siteUserDto, Product productDto, int amount) {
        log.info("[CartDao] Cart Insert");
        Cart cart = cartRepository.findBySiteUser_Id(siteUserDto.getId());

        // 장바구니가 존재하지 않는다면
        if (cart == null) {
            cart = Cart.createCart(siteUserDto);
            cartRepository.save(cart);
        }

        Product product = productRepository.getReferenceById(productDto.getProductId());
        CartItem cartItem = cartItemRepository.findByCart_CartIdAndProduct_ProductId(cart.getCartId(), product.getProductId());

        // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, product, amount);
            cartItemRepository.save(cartItem);
        }
        // 상품이 장바구니에 이미 존재한다면 수량만 증가
        else {
            CartItem update = cartItem;
            update.setCart(cartItem.getCart());
            update.setProduct(cartItem.getProduct());
            update.addCount(amount);
            update.setCartItemCount(update.getCartItemCount());
            cartItemRepository.save(update);
        }

        // 카트 상품 총 개수 증가
        cart.setCartCount(cart.getCartCount()+amount);
    }

    public List<CartItem> allUserCartView(Cart userCart) {
        // 유저의 카트 id를 꺼냄
        long userCartId = userCart.getCartId();

        // id에 해당하는 유저가 담은 상품들 넣어둘 곳
        List<CartItem> UserCartItemList = new ArrayList<>();

        // 유저 상관 없이 카트에 있는 상품 모두 불러오기
        List<CartItem> CartItemList = cartItemRepository.findAll();

        for(CartItem cartItem : CartItemList) {
            if(cartItem.getCart().getCartId() == userCartId) {
                UserCartItemList.add(cartItem);
            }
        }

        return UserCartItemList;
    }

    public Cart findCartBySiteUser_Id(long id) {
        return cartRepository.findCartBySiteUser_Id(id);
    }

    // R(Select)
    public List<Cart> selectCartAll() {
        log.info("[CartDao] Cart Select All");
        List<Cart> cartList = cartRepository.findAll();
        return cartList;
    }

     // D(Delete) : id
    public void deleteCart(long cartId) {
        Cart savedCart = cartRepository.getReferenceById(cartId);
        // 삭제하고자 하는 데이터를 DB에서 확인
        if (savedCart == null) {
            log.info("[CartDao] Failed User Delete : Does not exist. : " + cartId);
            return;
        }
        log.info("Cart Delete");
        cartRepository.deleteById(cartId);
    }

    public void allCartItemDelete(long id) {
        // 전체 cartItem 찾기
        List<CartItem> cartItemList = cartItemRepository.findAll();

        // 반복문을 이용하여 해당하는 User 의 CartItem 만 찾아서 삭제
        for(CartItem cartItem : cartItemList){
            if(cartItem.getCart().getSiteUser().getId() == id) {
                cartItem.getCart().setCartCount(0);
                cartItemRepository.deleteById(cartItem.getCartItemId());
                cartRepository.deleteById(cartItem.getCart().getCartId());
            }
        }
    }
}
