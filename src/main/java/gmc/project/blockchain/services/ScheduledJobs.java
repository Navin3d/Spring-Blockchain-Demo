package gmc.project.blockchain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import gmc.project.blockchain.daos.CompanyDao;
import gmc.project.blockchain.entities.BlockEntity;

@Component
public class ScheduledJobs {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Scheduled(fixedDelay = 6000000)
	private void checkForTamper() {		
		companyDao.findAll().forEach(company -> {
			
			Set<BlockEntity> blockChainList = company.getBlockChain();
			List<BlockEntity> blockchain = new ArrayList<>();
			
			blockChainList.forEach(block -> {
				blockchain.add(block);
			});
			boolean flag = true;
			
			for (int i = 0; i < blockchain.size(); i++) {
		        String previousHash = i==0 ? "0" : blockchain.get(i - 1).getHash();
		        int prefix = 4;               				// Prefix to be fetched from DB...
				String prefixString = new String(new char[prefix]).replace('\0', '0');
				
		        flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
		          && previousHash.equals(blockchain.get(i).getPreviousHash())
		          && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
		            if (!flag) break;
		    }
		    
			if(flag != true) {
				company.getCompanyOwner().getEmail();
			}
			
		});
		
	}

}
