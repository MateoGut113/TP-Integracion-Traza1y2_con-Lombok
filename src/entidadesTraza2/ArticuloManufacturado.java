package entidadesTraza2;

import entidadesTraza1.Sucursal;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString (callSuper = true, onlyExplicitlyIncluded = true)


public class ArticuloManufacturado extends Articulo{
    private String descripcion;
    @ToString.Include
    private int tiempoEstimadoMinutos;
    private String preparacion;

    //Relaciones con otras clases
    @Builder.Default
    private Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new HashSet<>();


}
