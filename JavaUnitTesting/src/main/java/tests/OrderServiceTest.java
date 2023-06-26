package tests;

import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

	@Mock
	DiscountUtility discountUtility;

	@InjectMocks
	OrderService orderService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetOrderPrice() {
		// Given
		Product product1 = new Product(1, "Product 1", 1.0, 10.0);
		Product product2 = new Product(2, "Product 2", 2.0, 10.0);
		String dob = "1990/10/10";

		ShoppingCart cart = new ShoppingCart(Arrays.asList(product1, product2));
		UserAccount user = new UserAccount("John", "Smith", dob, cart);


		when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

		// When
		double orderPrice = orderService.getOrderPrice(user);

		// Then
		assertEquals(cart.getCartTotalPrice() - 3.0, orderPrice);
		verify(discountUtility, times(1)).calculateDiscount(user);
		verifyNoMoreInteractions(discountUtility);
	}
}

