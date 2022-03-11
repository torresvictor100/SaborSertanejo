package com.system.sabor.sertanejo.reposity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.sabor.sertanejo.entity.CashDesk;

@Repository
public interface CashDeskReposity extends JpaRepository< CashDesk, Long> {
	
	List<CashDesk> findByNameContainingIgnoreCase(String name);
	
	Optional<CashDesk> findByName(String name);
	
}
