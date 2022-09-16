package gmc.project.blockchain.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.blockchain.entities.EngineSettingsEntity;

public interface EngineDao extends JpaRepository<EngineSettingsEntity, Long> {

}
