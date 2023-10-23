package br.ufpb.dcx.projeto.dcs.logic.repository;

import br.ufpb.dcx.projeto.dcs.db.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByOrderByCreatedAsc();

    List<Donation> findByCampaignIdOrderByCreatedAsc(Long campaignId);
}
