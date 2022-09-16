package gmc.project.blockchain.services;

import gmc.project.blockchain.models.CompanyModel;
import gmc.project.blockchain.models.blockchain.FakeProduct;
import gmc.project.blockchain.models.blockchain.FakeProductMiningModel;

public interface CompanyService {
	public void save(CompanyModel companyModel);
	public String fakeProductMine(FakeProductMiningModel fakeProduct);
	public FakeProduct getMinedBlock(String companyId, String productId);
}
