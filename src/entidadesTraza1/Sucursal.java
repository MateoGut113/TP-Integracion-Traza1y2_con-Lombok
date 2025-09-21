package entidadesTraza1;

import entidadesTraza2.Articulo;
import lombok.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true) //Solo apareceran los campos indicados

public class Sucursal {
    @ToString.Include
    private String nombre; //Asi solo aparecera el nombre de la sucursal por pantalla.
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Motriz;
    private Long id; //Servirá para mostrar precios de los articulos por pantalla

    //Relaciones con otras clases.
    private Domicilio domicilio;
    private Set<Articulo> articulos = new HashSet<>(); //Relacion a los articulos que se dispondrán en la sucursal.


}
