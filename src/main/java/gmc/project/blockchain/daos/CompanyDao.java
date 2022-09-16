package gmc.project.blockchain.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.blockchain.entities.CompanyEntity;

public interface CompanyDao extends JpaRepository<CompanyEntity, String> {

}
