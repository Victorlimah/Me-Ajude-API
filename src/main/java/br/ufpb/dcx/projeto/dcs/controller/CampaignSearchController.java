package br.ufpb.dcx.projeto.dcs.controller;

import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import br.ufpb.dcx.projeto.dcs.logic.service.CampaignSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/search/campaigns")
public class CampaignSearchController {

    @Autowired
    private CampaignSearchService campaignSearchService;

    @GetMapping("/active/title")
    public ResponseEntity<List<Campaign>> findActiveCampaignsOrderByTitle() {
        List<Campaign> campaigns = campaignSearchService.findActiveCampaignsOrderByTitle();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/active/created-at")
    public ResponseEntity<List<Campaign>> findActiveCampaignsOrderByCreatedAt() {
        List<Campaign> campaigns = campaignSearchService.findActiveCampaignsOrderByCreatedAt();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/completed/created-at")
    public ResponseEntity<List<Campaign>> findCompletedCampaignsOrderByCreatedAt() {
        List<Campaign> campaigns = campaignSearchService.findCompletedCampaignsOrderByCreatedAt();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/completed/goal")
    public ResponseEntity<List<Campaign>> findCompletedCampaignsOrderByGoal() {
        List<Campaign> campaigns = campaignSearchService.findCompletedCampaignsOrderByGoal();
        return ResponseEntity.ok(campaigns);
    }
}