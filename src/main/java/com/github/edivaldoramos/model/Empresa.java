package com.github.edivaldoramos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Edivaldo Ramos
 */
@Data
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_id_seq")
    @SequenceGenerator(name = "empresa_id_seq", sequenceName = "empresa_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;

    @Column(length = 8)
    private String cnpjRaiz;

    @OneToMany
    @JoinColumn(name = "empresa_id")
    @JsonIgnore
    private List<Filial> filiais = new ArrayList<>();
}
