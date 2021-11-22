package com.alkemy.icons.icons.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "icon")
@Getter
@Setter

// Esto es el soft delete, lo vamos a ver mas adelante
//@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id = ?")
//@Where(clause = "deleted=false")

public class IconEntity {

@Id                       // Identificador
 //@Column             no hace falta poner @Column cuando el atributo tiene el mismo nombre que la tabla
@GeneratedValue(strategy = GenerationType.SEQUENCE)          // Defino la estrategia como una secuencia
  
private Long id;
private String imagen;          // podríamos suponer que es una URL  de una imagen
private String denominacion;   
  
@Column(name = "fecha_creacion")
@DateTimeFormat(pattern = "yyyy/MM/dd")    // Esta notacion es para definir como me va a llegar el formato
private LocalDate fechaCreacion;
  
private Long altura;
private String historia;
  
// esto dijimos lo vamos a ver más adelante  
//  private boolean deleted = Boolean.FALSE;
  
@ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL)   // Acá le digo que la definición de este ManyToMany se hace cargo "icons", que es el atributo en la clase PaisEntity
private List <PaisEntity> paises = new ArrayList <>();

//por ahora ignoramos estos métodos de agregar y eliminar países
//// Add and Remove paises
//public void addPais(PaisEntity pais) { 
//    this.paises.add(pais);
//}
//public void removePais(PaisEntity pais) {
//    this.paises.remove(pais);
//}    


}    
