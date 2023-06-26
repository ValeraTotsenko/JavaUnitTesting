package tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest {

	private ShoppingCart shoppingCart;

	@BeforeEach
	void setUp() {
		shoppingCart = new ShoppingCart(new ArrayList<>());
	}

	@Test
	public void testAddProductToCart() {
		Product product = new Product(1, "Product 1", 1.0, 10.0);
		shoppingCart.addProductToCart(product);

		// Verify that the product was added to the cart
		assertEquals(product, shoppingCart.getProductById(product.getId()));
	}

	@Test
	public void testRemoveProductFromCart() {
		Product product = new Product(1, "Product 1", 1.0, 10.0);
		shoppingCart.addProductToCart(product);

		// Verify that the product was added to the cart
		assertEquals(product, shoppingCart.getProductById(product.getId()));

		shoppingCart.removeProductFromCart(product);

		// Verify that the product was removed from the cart
		assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(product.getId()));
	}

	@Test
	public void testGetCartTotalPrice() {
		Product product1 = new Product(1, "Product 1", 2.0, 10.0);
		Product product2 = new Product(2, "Product 2", 3.0, 20.0);
		shoppingCart.addProductToCart(product1);
		shoppingCart.addProductToCart(product2);

		// Verify the total price of the cart
		assertEquals(2*10 + 3*20, shoppingCart.getCartTotalPrice());
	}
}
