package com.alkemy.icons.icons.entity;

import javax.persistence.*;
import lombok.Getter;                     // Con la dependencia lombok lo que hago es poder crear todos los set y get de mi clase de manera automática sin ensuciar tanto mi clase
import lombok.Setter;
        

@Entity
@Table(name = "continente")
@Getter
@Setter
public class ContinenteEntity {

  @Id                       // Identificador
  @GeneratedValue(strategy = GenerationType.SEQUENCE)          // Defino la estrategia como una secuencia
  
  private Long id;
  private String imagen;
  private String denominacion;
  
  
  }

