package com.webprojectv1.notalone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webprojectv1.notalone.product.IProductRepository;
import com.webprojectv1.notalone.product.Product;
import com.webprojectv1.notalone.user.IUserRepository;
import com.webprojectv1.notalone.user.SiteUser;

@SpringBootTest
class NotaloneApplicationTests {

	@Autowired
	IProductRepository iProductRepository;
	@Autowired
	IUserRepository userRepository;


	@Test
	void createProduct() {
		Product p = new Product();
		p.setProductName("Sample Product");
		p.setProductCategory("CategoryEnum.MEAT");
		p.setProductPrice(10);
		p.setProductRating(4.5);
		p.setProductStock(100);
		p.setProductImage("sample_image.jpg");

		iProductRepository.save(p);
	}


	@Test
    public void testFindByUserId() {
        // Assuming you have an instance of IUserRepository named userRepository
        String userId = "qqqq"; // Replace with an actual user ID from your database
        Optional<SiteUser> user = userRepository.findByUserId(userId);
        
        assertNotNull(user.orElse(null)); // Assert that the user is found
        assertEquals(userId, user.get().getUserId()); // Assert that the correct user is returned
    }

}
