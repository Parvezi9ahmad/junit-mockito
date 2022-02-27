package com.bharath.springcloud;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springcloud.controllers.CouponRestController;
import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;

@SpringBootTest
public class CouponserviceApplicationTests {

	private static final String SUPERSALE = "SUPERSALE";

	@Mock
	private CouponRepo repo;

	@InjectMocks
	private CouponRestController controller;

	@Test
	public void testCreate() {
		Coupon coupon = new Coupon();
		coupon.setCode(SUPERSALE);
		when(repo.save(coupon)).thenReturn(coupon);
		Coupon couponCreated = controller.create(coupon);
		verify(repo).save(coupon);
		assertNotNull(couponCreated);
		assertEquals(SUPERSALE, coupon.getCode());

	}

	@Test
	public void testCreate_WHEN_COUPON_IS_NULL_THROW_EXCEPTION() {

		assertThrows(IllegalArgumentException.class, () -> {
			controller.create(null);
		});

	}

	@Test
	public void testGetCoupon() {
		Coupon coupon = new Coupon();
		coupon.setId(123l);
		coupon.setCode(SUPERSALE);
		coupon.setDiscount(new BigDecimal(10));
		when(repo.findByCode(SUPERSALE)).thenReturn(coupon);
		Coupon couponResponse = controller.getCoupon(SUPERSALE);
		verify(repo).findByCode(SUPERSALE);
		assertNotNull(couponResponse);
		assertEquals(new BigDecimal(10), couponResponse.getDiscount());
	}

}
