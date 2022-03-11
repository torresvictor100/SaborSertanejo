package com.system.sabor.sertanejo.service;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.system.sabor.sertanejo.entity.CashDesk;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CashDeskTest {

	@Autowired
	CashDeskService cashDeskService;

	@Test
	public void findAll() {

		List<CashDesk> department = cashDeskService.findAll(null);
		department.forEach(System.out::println);
	}

	@Test
	public void findById() {

		Long id = 1L;
		CashDesk cashDesk = cashDeskService.findById(id);
		System.out.println(cashDesk);
	}

	@Test
	public void findByName() {

		String name = "test";
		CashDesk cashDesk = cashDeskService.findByName(name);
		System.out.println(cashDesk);
	}
	

	@Test
	public void save() throws ParseException {
		
		CashDesk cashDesk = new CashDesk();

		cashDesk.setId(null);
		cashDesk.setName("test");
		cashDesk.setAmount(0.00);
		
		cashDesk = cashDeskService.save(cashDesk);
		System.out.println(cashDesk);
	}
	
	@Test
	public void update() throws ParseException {
		
		CashDesk cashDesk = new CashDesk();
		cashDesk.setId(1L);
		cashDesk.setName("test");
		
		cashDesk = cashDeskService.update(cashDesk);
		System.out.println(cashDesk);
	}
	
	@Test
	public void updateAdd() throws ParseException {
		
		CashDesk cashDesk = new CashDesk();
		cashDesk.setId(2L);
		cashDesk = cashDeskService.updateAdd(cashDesk, 2.00);
		System.out.println(cashDesk);
	}
	
	@Test
	public void updateRemove() throws ParseException {
		
		CashDesk cashDesk = new CashDesk();
		cashDesk.setId(2L);
		cashDesk = cashDeskService.updateRemove(cashDesk, 1.00);
		System.out.println(cashDesk);
	}
	 

	@Test
	public void deleteById() {
		Long id = 1L;
		cashDeskService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		cashDeskService.deleteAll();
	}

}
