package br.ufpb.dcx.projeto.dcs.logic.repository;

import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository  extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByTitle(String title);

    @Query("SELECT c FROM Campaign c WHERE c.state = 'ACTIVE' ORDER BY c.title")
    List<Campaign> findActiveCampaignsOrderByTitle();

    @Query("SELECT c FROM Campaign c WHERE c.state = 'ACTIVE' ORDER BY c.createdAt")
    List<Campaign> findActiveCampaignsOrderByCreatedAt();

    @Query("SELECT c FROM Campaign c WHERE c.state = 'COMPLETED' ORDER BY c.createdAt")
    List<Campaign> findCompletedCampaignsOrderByCreatedAt();

    @Query("SELECT c FROM Campaign c WHERE c.currentAmount >= c.goal ORDER BY c.createdAt")
    List<Campaign> findCompletedCampaignsOrderByGoal();
}
