package br.ufpb.dcx.projeto.dcs.db.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {

    private  Long id;

    private String title;

    private String description;

    private Double goal;

    private LocalDate deadline;

}
