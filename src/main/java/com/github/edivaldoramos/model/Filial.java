package com.github.edivaldoramos.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Edivaldo Ramos
 */
@Data
@Entity
@Table(name = "filial")
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial_id_seq")
    @SequenceGenerator(name = "filial_id_seq", sequenceName = "filial_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;

    @Column(length = 14)
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa = new Empresa();
}
