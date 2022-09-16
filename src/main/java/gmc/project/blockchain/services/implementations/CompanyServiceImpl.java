package gmc.project.blockchain.services.implementations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.daos.CompanyDao;
import gmc.project.blockchain.daos.UserDao;
import gmc.project.blockchain.entities.BlockEntity;
import gmc.project.blockchain.entities.CompanyEntity;
import gmc.project.blockchain.entities.UserEntity;
import gmc.project.blockchain.models.CompanyModel;
import gmc.project.blockchain.models.blockchain.FakeProduct;
import gmc.project.blockchain.models.blockchain.FakeProductMiningModel;
import gmc.project.blockchain.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserDao userDao;

	@Override
	public void save(CompanyModel companyModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		if(companyModel.getId() == null) {
			CompanyEntity detachedEntity = modelMapper.map(companyModel, CompanyEntity.class);
			UserEntity foundUser = userDao.findById(companyModel.getCompanyOwner().getId()).orElse(null);
			if(foundUser == null)
				throw new UsernameNotFoundException(companyModel.getCompanyOwner().getId());
			
			UserEntity oldOwner = detachedEntity.getCompanyOwner();
			oldOwner.setOwnerOfCompany(null);
			userDao.save(oldOwner);
			
			detachedEntity.setCompanyOwner(foundUser);
			CompanyEntity savedCompany = companyDao.save(detachedEntity);
			foundUser.setOwnerOfCompany(savedCompany);
			userDao.save(foundUser);
		} else {
			CompanyEntity detachedEntity = companyDao.findById(companyModel.getId()).orElse(null);
			if(detachedEntity == null)
				throw new RuntimeException(companyModel.getId());
			detachedEntity.setCompanyName(companyModel.getCompanyName());
			detachedEntity.setGstNumber(companyModel.getGstNumber());
			
			UserEntity foundUser = userDao.findById(companyModel.getCompanyOwner().getId()).orElse(null);
			if(foundUser == null)
				throw new UsernameNotFoundException(companyModel.getCompanyOwner().getId());
			
			UserEntity oldOwner = detachedEntity.getCompanyOwner();
			oldOwner.setOwnerOfCompany(null);
			userDao.save(oldOwner);
			
			detachedEntity.setCompanyOwner(foundUser);
			CompanyEntity savedCompany = companyDao.save(detachedEntity);
			foundUser.setOwnerOfCompany(savedCompany);
			userDao.save(foundUser);
		}
		
	}

	@Override
	public String fakeProductMine(FakeProductMiningModel fakeProductMiningModel) {
		FakeProduct fakeProduct = fakeProductMiningModel.getFakeProduct();
		fakeProduct.setDateTime(LocalDateTime.now());
		String fakeString = fakeProductMiningModel.toString();
		BlockEntity newBlock = new BlockEntity();
		newBlock.setData(fakeString);
		
		CompanyEntity foundCompanyEntity = companyDao.findById(fakeProductMiningModel.getCompanyId()).orElse(null);
		if(foundCompanyEntity == null)
			throw new RuntimeException("Company not found...");
		
		Set<BlockEntity> blockChainSet = foundCompanyEntity.getBlockChain();
		List<BlockEntity> blockChain = new ArrayList<>();
		
		blockChainSet.forEach(block -> {
			blockChain.add(block);
		});
		
		int prefix = 4;               				// Prefix to be fetched from DB...
		String prefixString = new String(new char[prefix]).replace('\0', '0');

		if(blockChain.size() > 0) {
			newBlock.setPreviousHash(blockChain.get(blockChain.size() - 1).getHash());
		} else {
			newBlock.setPreviousHash("start");
		}
		
		newBlock.setHash("Hash");              				// Hash to be calculated here...
		newBlock.setTimeStamp(new Date().getTime());
		newBlock.mineBlock(prefix);
		
		if(newBlock.getHash().substring(0, prefix).equals(prefixString))
			blockChain.add(newBlock);
		
		return newBlock.getHash();
	}

	@Override
	public FakeProduct getMinedBlock(String companyId, String productId) {
		CompanyEntity foundCompanyEntity = companyDao.findById(companyId).orElse(null);
		if(foundCompanyEntity == null)
			throw new RuntimeException("Company not found...");
		
		Set<BlockEntity> blockChainSet = foundCompanyEntity.getBlockChain();
		return null;
	}

}
