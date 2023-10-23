package br.ufpb.dcx.projeto.dcs.controller;

import br.ufpb.dcx.projeto.dcs.db.response.DonationResponse;
import br.ufpb.dcx.projeto.dcs.logic.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/donations")
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping
    public ResponseEntity<List<DonationResponse>> listAllDonations() {
        List<DonationResponse> donations = donationService.findAllDonations();
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<DonationResponse>> listDonationsByCampaign(@PathVariable Long campaignId) {
        List<DonationResponse> donations = donationService.findDonationsByCampaign(campaignId);
        return ResponseEntity.ok(donations);
    }
}
