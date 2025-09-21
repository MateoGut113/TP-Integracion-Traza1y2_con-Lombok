package entidadesTraza1;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class Empresa {
    private Long id; //Atributo necesario para la implementacion de la clase InMemoryRepository
    private String nombre;
    private String razonSocial;
    private int cuit;
    private String logo;

    //Relaciones con otras clases.
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

}
