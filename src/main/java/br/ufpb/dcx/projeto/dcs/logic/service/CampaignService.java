package br.ufpb.dcx.projeto.dcs.logic.service;

import br.ufpb.dcx.projeto.dcs.config.exception.AppException;
import br.ufpb.dcx.projeto.dcs.db.dto.CampaignDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import br.ufpb.dcx.projeto.dcs.db.enums.ErrorTypes;
import br.ufpb.dcx.projeto.dcs.db.enums.Role;
import br.ufpb.dcx.projeto.dcs.db.enums.StatesTypes;
import br.ufpb.dcx.projeto.dcs.logic.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    JWTService jwtService;

    private static final String CAMPAIGN_NOT_FOUND = "Campaign not found";

    public CampaignService(CampaignRepository campaignRepository, JWTService jwtService) {
        this.campaignRepository = campaignRepository;
        this.jwtService = jwtService;
    }

    public Campaign save(CampaignDTO campaignDTO, String authorization) {
        this.validFields(campaignDTO);
        User user = jwtService.validateToken(authorization);

        return campaignRepository.save(Campaign.builder()
                .title(campaignDTO.getTitle())
                .description(campaignDTO.getDescription())
                .goal(campaignDTO.getGoal())
                .deadline(campaignDTO.getDeadline())
                .user(user)
                .currentAmount(0.0)
                .build());
    }

    public Campaign delete(Long id, String authorization) {
        User user = jwtService.validateToken(authorization);
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorTypes.NOT_FOUND, CAMPAIGN_NOT_FOUND));

        Boolean isOwner = campaign.getUser().getId().equals(user.getId());
        if (!isOwner || !Role.ADMIN.equals(user.getRole())) {
            throw new AppException(ErrorTypes.UNAUTHORIZED, "You are not authorized to delete this campaign");
        }

        if (campaign.getCurrentAmount() > 0.0) {
            throw new AppException(ErrorTypes.UNAUTHORIZED, "This campaign already has donations");
        }

        campaign.setState(StatesTypes.INACTIVE);
        return campaignRepository.save(campaign);
    }

    private void validFields(CampaignDTO campaignDTO) {
        if (Objects.isNull(campaignDTO.getTitle()) || campaignDTO.getTitle().isEmpty()) {
            throw new AppException(ErrorTypes.UNPROCESSABLE_ENTITY, "Title is required");
        }
        if (Objects.isNull(campaignDTO.getDescription()) || campaignDTO.getDescription().isEmpty()) {
            throw new AppException(ErrorTypes.UNPROCESSABLE_ENTITY, "Description is required");
        }
        if (campaignDTO.getGoal() <= 0.0) {
            throw new AppException(ErrorTypes.UNPROCESSABLE_ENTITY, "Goal must be greater than 0");
        }
        if (Objects.isNull(campaignDTO.getDeadline())) {
            throw new AppException(ErrorTypes.UNPROCESSABLE_ENTITY, "Deadline is required");
        }
        if (campaignDTO.getDeadline().isBefore(LocalDate.now())) {
            throw new AppException(ErrorTypes.UNPROCESSABLE_ENTITY, "Deadline must be greater than today");
        }
        campaignRepository.findByTitle(campaignDTO.getTitle()).ifPresent(campaign -> {
            throw new AppException(ErrorTypes.UNPROCESSABLE_ENTITY, "Title already exists");
        });
    }
}
