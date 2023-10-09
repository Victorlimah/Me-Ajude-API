package br.ufpb.dcx.projeto.dcs.db.dto;

import lombok.*;

import java.beans.BeanProperty;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {

    private String title;

    private String description;

    private double goal;

    private LocalDate deadline;

}
