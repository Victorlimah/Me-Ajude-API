package br.ufpb.dcx.projeto.dcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufpb.dcx.projeto.dcs.db.dto.CampaignDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import br.ufpb.dcx.projeto.dcs.logic.service.CampaignService;

@RestController
@RequestMapping("/v1/api/campaigns")
public class CampaignController {
    
    @Autowired
    CampaignService campaignService;

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody CampaignDTO campaignDTO, 
                                                    @RequestHeader("Authorization") String authorization) {
        Campaign campaign = campaignService.save(campaignDTO, authorization);
        return ResponseEntity.ok(campaign);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Campaign> deleteCampaign(@PathVariable Long id, 
                                                    @RequestHeader("Authorization") String authorization) {
        Campaign campaign = campaignService.delete(id, authorization);
        return ResponseEntity.ok(campaign);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, 
                                                    @RequestBody CampaignDTO campaignDTO, 
                                                    @RequestHeader("Authorization") String authorization) {
        Campaign campaign = campaignService.update(id, campaignDTO, authorization);
        return ResponseEntity.ok(campaign);
    }
}
