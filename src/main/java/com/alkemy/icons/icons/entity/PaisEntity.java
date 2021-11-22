package com.alkemy.icons.icons.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pais")
@Getter
@Setter
public class PaisEntity {

 @Id                       // Identificador
 //@Column             no hace falta poner @Column cuando el atributo tiene el mismo nombre que la tabla
 @GeneratedValue(strategy = GenerationType.SEQUENCE)          // Defino la estrategia como una secuencia
  
  private Long id;
  private String imagen;
  private String denominacion;   
    
  
@Column(name = "cant_habitantes")       // Ahora si escribo @Column porque el nombre va a ser diferente, y además es una buena práctica usar en las tablas la manera de escribir separando las palabras con guion bajo
private Long cantidadHabitantes;

private Long superficie; //m2

// Esto de abajo se parece a lo de mas abajo pero este es para ir a buscar información el cual va a traer un objeto del tipo continente

@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)                      // el FetchType.EAGER significa que la inicialización va a ser del tipo temprana, o sea que cuando yo pida un dato tipo país, si o si va a venir con su continente,  y el CascadeType.ALL es para que todas las operaciones sean consecuentes con su continente
@JoinColumn(name = "continente_id", insertable = false, updatable = false)          // Esto es para saber como voy a joinear la entidad del país con la tabla del continente y en este caso lo va a hacer a través del atributo "continente_id"
                                                                                                                                              // insertable = false, updatable = false son false porque solamente los uso para  obtener información 
private ContinenteEntity continente;                                                                               // este atribulto realmente me define mi atributo continenteId en la base de datos

// Este es para guardar y actualizar donde realmente tengo el Id
@Column(name = "continente_id", nullable = false)
private Long continenteId;

@ManyToMany(
          cascade = {
                         CascadeType.PERSIST,      //CascadeType.PERSIST: las operaciones de guardado en la base de datos de las entidades padre se propagarán a las entidades relacionadas.
                         CascadeType.MERGE        //CascadeType.MERGE: las entidades relacionadas se unirán al contexto de persistencia cuando la entidad propietaria se una.
                            })
 
// en este jointable voy a definir como se "mezclan" "joinean" estas relaciones del ManyToMany para crear la tabla intermedia que necesito
@JoinTable( 
          name = "icon_pais",     // como es una relacion manytomany no va a tener una tabla intermedia, pero con este name defino como yo quiero que se llame la tabla intemedia
          joinColumns = @JoinColumn(name = "pais_id"),    // acá le denomino como se va a llamar
          inverseJoinColumns = @JoinColumn(name = "icon_id"))   // y del otro lado hacia acá
private Set<IconEntity> icons = new HashSet<>();                              // Defino un Set IconEntity que lo llamo icons y defino un new HashSet, así se da cuenta que debe apuntar hacia la tabla de iconos
 

// con este override básicamente estoy redefiniendo la comparación de países
@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    final PaisEntity other = (PaisEntity) obj;
    return other.id == this.id;       
    // netbeans me informa que lo de arriba es lo mismo que esto aca abajo
    //return Objects.equals(other.id, this.id); logico
   
}

}
    
    
