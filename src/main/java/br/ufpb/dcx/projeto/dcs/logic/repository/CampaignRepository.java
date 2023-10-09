package br.ufpb.dcx.projeto.dcs.logic.repository;

import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository  extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByTitle(String title);
}
