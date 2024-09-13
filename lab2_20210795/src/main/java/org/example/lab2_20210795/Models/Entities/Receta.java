package org.example.lab2_20210795.Models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Getter
@Setter
@Entity
@Table(name = "receta")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreceta", nullable = false)
    private Integer idReceta;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "dificultad")
    private Integer dificultad;

    @Column(name = "instrucciones", length = 500)
    private String instrucciones;

    // Cambiar el departamento a una relación
    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria idcategoria;  // Relación con Department

}
