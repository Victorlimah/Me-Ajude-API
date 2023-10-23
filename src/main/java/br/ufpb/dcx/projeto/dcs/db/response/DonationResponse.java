package br.ufpb.dcx.projeto.dcs.db.response;

import br.ufpb.dcx.projeto.dcs.db.entity.Campaign;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationResponse {

    private Long id;

    private Double amount;

    private LocalDate donationDate;

    private Campaign campaign;

}
