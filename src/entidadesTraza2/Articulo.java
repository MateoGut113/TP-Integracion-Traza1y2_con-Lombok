package entidadesTraza2;

import entidadesTraza1.Sucursal;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString (onlyExplicitlyIncluded = true) //Solo apareceran los campos indicados
@Setter
@SuperBuilder //Herencia

public abstract class Articulo {
    @ToString.Include
    protected String denominacion;
    @ToString.Include
    protected Double precioVenta;
    @ToString.Include
    protected Long id;

    //Relaciones con otras clases
    @Builder.Default
    private Set<Imagen> imagenes = new HashSet<>();
    private UnidadMedida unidadMedida;
    @ToString.Include
    private Sucursal sucursal;


}
