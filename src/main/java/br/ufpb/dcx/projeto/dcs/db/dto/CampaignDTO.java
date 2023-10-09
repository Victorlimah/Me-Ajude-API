package br.ufpb.dcx.projeto.dcs.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {

    private String title;

    private String description;

    private double goal;

    private LocalDate deadline;

}
