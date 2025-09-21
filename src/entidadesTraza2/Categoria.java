package entidadesTraza2;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Categoria {
    private String denominacion;
    private Long id;

    //Relaciones con otras clases. Se puede hacer aunque Articulo sea abstracto.
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

}
