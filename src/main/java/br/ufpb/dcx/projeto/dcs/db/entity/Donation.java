package br.ufpb.dcx.projeto.dcs.db.entity;

import jakarta.persistence.*;

import java.util.Date;

public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private Date date = new Date();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
