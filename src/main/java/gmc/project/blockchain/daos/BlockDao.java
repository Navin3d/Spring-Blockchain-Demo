package gmc.project.blockchain.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.blockchain.entities.BlockEntity;

public interface BlockDao extends JpaRepository<BlockEntity, String> {

}
