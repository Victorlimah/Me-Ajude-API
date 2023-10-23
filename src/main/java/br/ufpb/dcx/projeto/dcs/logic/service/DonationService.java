package br.ufpb.dcx.projeto.dcs.logic.service;

import br.ufpb.dcx.projeto.dcs.db.entity.Donation;
import br.ufpb.dcx.projeto.dcs.db.response.DonationResponse;
import br.ufpb.dcx.projeto.dcs.logic.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    public List<DonationResponse> findAllDonations() {
        List<Donation> donations = donationRepository.findAllByOrderByCreatedAsc();
        return donations.stream()
                .map(donation -> DonationResponse.builder()
                        .id(donation.getId())
                        .amount(donation.getAmount())
                        .donationDate(donation.getCreated())
                        .campaign(donation.getCampaign())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public List<DonationResponse> findDonationsByCampaign(Long campaignId) {
        List<Donation> donations = donationRepository.findByCampaignIdOrderByCreatedAsc(campaignId);
        return donations.stream()
                .map( donation -> DonationResponse.builder()
                        .id(donation.getId())
                        .amount(donation.getAmount())
                        .donationDate(donation.getCreated())
                        .campaign(donation.getCampaign())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
