package br.ufpb.dcx.projeto.dcs.logic.service;

import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import br.ufpb.dcx.projeto.dcs.logic.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignSearchService {

    @Autowired
    CampaignRepository campaignRepository;

    public List<Campaign> findActiveCampaignsOrderByTitle() {
        return campaignRepository.findActiveCampaignsOrderByTitle();
    }

    public List<Campaign> findActiveCampaignsOrderByCreatedAt() {
        return campaignRepository.findActiveCampaignsOrderByCreatedAt();
    }

    public List<Campaign> findCompletedCampaignsOrderByCreatedAt() {
        return campaignRepository.findCompletedCampaignsOrderByCreatedAt();
    }

    public List<Campaign> findCompletedCampaignsOrderByGoal() {
        return campaignRepository.findCompletedCampaignsOrderByGoal();
    }
}
