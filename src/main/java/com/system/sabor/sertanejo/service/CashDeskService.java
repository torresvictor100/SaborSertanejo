package com.system.sabor.sertanejo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.sabor.sertanejo.entity.CashDesk;
import com.system.sabor.sertanejo.reposity.CashDeskReposity;

@Service
public class CashDeskService {

	private final CashDeskReposity cashDeskReposity;

	public CashDeskService(CashDeskReposity cashDeskReposity) {
		super();
		this.cashDeskReposity = cashDeskReposity;
	}

	public List<CashDesk> findAll(String name) {

		if (name == null) {
			return cashDeskReposity.findAll();
		} else {
			return cashDeskReposity.findByNameContainingIgnoreCase(name);
		}
	}

	public CashDesk findById(Long id) {
		return cashDeskReposity.findById(id).orElse(null);
	}

	public CashDesk findByName(String name) {
		return cashDeskReposity.findByName(name).orElse(null);
	}

	public CashDesk save(CashDesk cashDesk) {
		cashDesk.setId(null);
		return cashDeskReposity.save(cashDesk);
	}

	public CashDesk update(CashDesk cashDesk) {
		Long id = cashDesk.getId();
		if (id != null && cashDeskReposity.existsById(id)) {
			return cashDeskReposity.save(cashDesk);
		} else {
			return null;
		}
	}
	
	
	
	public CashDesk callDesk(CashDesk cashDesk, Long id) {
		
		cashDesk.setName(findById(id).getName());
		cashDesk.setAmount(findById(id).getAmount());
		return cashDesk;
	}

	public CashDesk updateAdd(CashDesk cashDesk, Double valoe) {
		Long id = cashDesk.getId();
		cashDesk =callDesk(cashDesk, id);
			
		if (id != null && cashDeskReposity.existsById(id) && valoe > 0) {
			cashDesk.setAmount(cashDesk.getAmount() + valoe);
			return cashDeskReposity.save(cashDesk);
		} else {
			return null;
		}
	}

	public CashDesk updateRemove(CashDesk cashDesk, Double valoe) {
		Long id = cashDesk.getId();
		cashDesk =callDesk(cashDesk, id);
		if (id != null && cashDeskReposity.existsById(id) && 
				valoe > 0 
				&& cashDesk.getAmount() >= valoe) {
			cashDesk.setAmount(cashDesk.getAmount() - valoe);
			return cashDeskReposity.save(cashDesk);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (cashDeskReposity.existsById(id)) {
			cashDeskReposity.deleteById(id);
		}
	}

	public void deleteAll() {
		cashDeskReposity.deleteAll();

	}
}
