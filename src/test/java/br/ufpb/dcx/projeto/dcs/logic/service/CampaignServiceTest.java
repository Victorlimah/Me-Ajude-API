package br.ufpb.dcx.projeto.dcs.logic.service;


import br.ufpb.dcx.projeto.dcs.config.exception.AppException;
import br.ufpb.dcx.projeto.dcs.db.dto.CampaignDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import br.ufpb.dcx.projeto.dcs.logic.repository.CampaignRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class CampaignServiceTest {

    CampaignRepository campaignRepository;
    JWTService jwtService;

    private User testUser = User.builder()
            .id(1L)
            .build();

    private CampaignDTO defaultCampaignDTO = CampaignDTO.builder()
            .goal(100.0)
            .deadline(LocalDate.now().plusDays(10))
            .build();

    @BeforeEach
    void init(){
        campaignRepository = Mockito.mock(CampaignRepository.class);
        jwtService = Mockito.mock(JWTService.class);
    }

    @Test
    @DisplayName("Save campaign should return campaign")
    void saveCampaignShouldReturnCampaign() {
        CampaignDTO campaignDTO = defaultCampaignDTO;
        campaignDTO.setTitle("Title");
        campaignDTO.setDescription("Description");
        campaignDTO.setDeadline(LocalDate.now().plusDays(1));

        Mockito.when(campaignRepository.save(Mockito.any(Campaign.class)))
                .thenReturn(Campaign.builder().goal(10.0).currentAmount(0.0).build());

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Campaign campaign = campaignService.save(campaignDTO, "testAuthorization");
        Assertions.assertNotNull(campaign);
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for missing title")
    void validFieldsMissingTitleThrowAppException() {
        CampaignDTO campaignWithoutTitle = defaultCampaignDTO;
        campaignWithoutTitle.setTitle(null);
        campaignWithoutTitle.setDescription("Description");

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithoutTitle, "testAuthorization");
        });
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for missing description")
    void validFieldsMissingDescriptionThrowAppException() {
        CampaignDTO campaignWithoutDescription = defaultCampaignDTO;
        campaignWithoutDescription.setTitle("Title");
        campaignWithoutDescription.setDescription(null);

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithoutDescription, "testAuthorization");
        });
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for missing goal")
    void validFieldsMissingGoalThrowAppException() {
        CampaignDTO campaignWithoutGoal = defaultCampaignDTO;
        campaignWithoutGoal.setTitle("Title");
        campaignWithoutGoal.setDescription("Description");
        campaignWithoutGoal.setGoal(0.0);

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithoutGoal, "testAuthorization");
        });
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for missing deadline")
    void validFieldsMissingDeadlineThrowAppException() {
        CampaignDTO campaignWithoutDeadline = defaultCampaignDTO;
        campaignWithoutDeadline.setTitle("Title");
        campaignWithoutDeadline.setDescription("Description");
        campaignWithoutDeadline.setDeadline(null);

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithoutDeadline, "testAuthorization");
        });
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for deadline before today")
    void validFieldsDeadlineBeforeTodayThrowAppException() {
        CampaignDTO campaignWithDeadlineBeforeToday = defaultCampaignDTO;
        campaignWithDeadlineBeforeToday.setTitle("Title");
        campaignWithDeadlineBeforeToday.setDescription("Description");
        campaignWithDeadlineBeforeToday.setDeadline(LocalDate.now().minusDays(1));

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithDeadlineBeforeToday, "testAuthorization");
        });
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for title already exists")
    void validFieldsTitleAlreadyExistsThrowAppException() {
        CampaignDTO campaignWithExistingTitle = defaultCampaignDTO;
        campaignWithExistingTitle.setTitle("Title");
        campaignWithExistingTitle.setDescription("Description");
        campaignWithExistingTitle.setDeadline(LocalDate.now().plusDays(1));

        Mockito.when(campaignRepository.findByTitle(campaignWithExistingTitle.getTitle()))
                .thenReturn(java.util.Optional.of(Campaign.builder().goal(10.0).currentAmount(0.0).build()));

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithExistingTitle, "testAuthorization");
        });
    }

    @Test
    @DisplayName("Campaign fields validation should throw exception for goal less than 0")
    void validFieldsGoalLessThanZeroThrowAppException() {
        CampaignDTO campaignWithGoalLessThanZero = defaultCampaignDTO;
        campaignWithGoalLessThanZero.setTitle("Title");
        campaignWithGoalLessThanZero.setDescription("Description");
        campaignWithGoalLessThanZero.setDeadline(LocalDate.now().plusDays(1));
        campaignWithGoalLessThanZero.setGoal(-1.0);

        CampaignService campaignService = new CampaignService(campaignRepository, jwtService);
        Assertions.assertThrows(AppException.class, () -> {
            campaignService.save(campaignWithGoalLessThanZero, "testAuthorization");
        });
    }
}