package com.system.sabor.sertanejo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.sabor.sertanejo.entity.CashDesk;
import com.system.sabor.sertanejo.service.CashDeskService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/cashdesk")
public class CashDeskController {
	
	private final CashDeskService cashDeskService;

	public CashDeskController(CashDeskService cashDeskService) {
		super();
		this.cashDeskService = cashDeskService;
	}
	
	@ApiOperation(value = "Find all cashdesk")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CashDesk>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<CashDesk> cashDesk = cashDeskService.findAll(name);
		return new ResponseEntity<>(cashDesk, HttpStatus.OK);
	}
		
	@ApiOperation(value = "Find a cashdesk by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{cashdesk_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CashDesk> findById(@PathVariable(name = "cashdesk_id") Long id) {
		CashDesk cashDesk = cashDeskService.findById(id);
		if (cashDesk == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(cashDesk, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Save a cashdesk")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CashDesk> save(@RequestBody CashDesk cashDesk) {
		try {
			cashDesk = cashDeskService.save(cashDesk);
			return new ResponseEntity<>(cashDesk, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update a cashdesk")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{cashdesk_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CashDesk> update(@PathVariable(name = "cashdesk_id") Long id,
			@RequestBody CashDesk cashDesk) {
		cashDesk.setId(id);
		try {
			cashDesk = cashDeskService.update(cashDesk);
			if (cashDesk == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(cashDesk, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "UpdateAdd a cashdesk")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/add/{cashdesk_id}&valoe={cashdesk_valoe}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CashDesk> updateAdd(@PathVariable(name = "cashdesk_id") Long id,
			@PathVariable(name = "valoe")Double valoe) {
		CashDesk cashDesk = cashDeskService.findById(id);
		valoe.doubleValue();
		try {
			cashDesk = cashDeskService.updateAdd(cashDesk, valoe);
			if (cashDesk == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(cashDesk, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value = "UpdateRemove a cashdesk")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/remove/{cashdesk_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CashDesk> updateRemove(@PathVariable(name = "cashdesk_id") Long id,
			@RequestBody CashDesk cashDesk,@PathVariable(name = "valoe")Double valoe) {
		cashDesk.setId(id);
		try {
			cashDesk = cashDeskService.updateRemove(cashDesk, valoe);
			if (cashDesk == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(cashDesk, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a cashdesk")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping(path = "/{cashdesk_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "cashdesk_id") Long id) {
		cashDeskService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all cashdesk")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		cashDeskService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
