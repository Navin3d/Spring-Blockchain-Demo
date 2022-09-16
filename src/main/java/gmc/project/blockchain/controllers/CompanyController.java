package gmc.project.blockchain.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.models.CompanyModel;
import gmc.project.blockchain.models.blockchain.FakeProduct;
import gmc.project.blockchain.models.blockchain.FakeProductMiningModel;
import gmc.project.blockchain.services.CompanyService;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
	
	private CompanyService companyService;
	
	@PostMapping
	private ResponseEntity<String> createOrUpdate(@RequestBody CompanyModel companyModel) {
		companyService.save(companyModel);
		return ResponseEntity.status(HttpStatus.CREATED).body("Company Registered Successfully...");
	}
	
	@PostMapping(path = "/fake-product/mine/block")
	private ResponseEntity<String> mineABlock(@RequestBody FakeProductMiningModel fakeProduct) {
		companyService.fakeProductMine(fakeProduct);
		return ResponseEntity.status(HttpStatus.CREATED).body("Block has been mined...");
	}
	
	@GetMapping(path = "/fake-product/{companyId}/{productId}")
	private ResponseEntity<FakeProduct> getABlock(@PathVariable String companyId, @PathVariable String productId) {
		FakeProduct returnValue = companyService.getMinedBlock(companyId, productId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
}
