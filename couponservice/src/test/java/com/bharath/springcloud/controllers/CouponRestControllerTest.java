package com.bharath.springcloud.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;

@SpringBootTest
class CouponRestControllerTest {

	private static final String SUPERSALE = "SUPERSALE";
	
	@Mock
	CouponRepo repo;
	
	@InjectMocks
	CouponRestController controller;
	
	@Test
	public void CouponCreated() {
		Coupon coupon=new Coupon();
		coupon.setCode(SUPERSALE);
		when(repo.save(coupon)).thenReturn(coupon);
		Coupon create = controller.create(coupon);
		assertNotNull(create);
		assertEquals(SUPERSALE,create.getCode());
		
	}
	
	@Test
	public void create_couponShould_ThroughException() {
		assertThrows(IllegalArgumentException.class,()->{
			controller.create(null);
		});
	}
	
	@Test
	public void TestCouponGet() {
        Coupon coupon=new Coupon();
        coupon.setId(123l);
        coupon.setCode(SUPERSALE);
        coupon.setDiscount(new BigDecimal(10));
        when(repo.findByCode(SUPERSALE)).thenReturn(coupon);
        Coupon Result = controller.getCoupon(SUPERSALE);
        assertEquals(new BigDecimal(10),Result.getDiscount());
        assertEquals(SUPERSALE,Result.getCode());
        
	}
	
	@Test
	public void TestCouponGet_through_Exception() {
		assertThrows(IllegalArgumentException.class,()->{
			controller.getCoupon(null);
		});
	}

}
