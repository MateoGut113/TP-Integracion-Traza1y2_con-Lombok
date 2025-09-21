package entidadesTraza1;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Localidad {
    private String nombre;

    //Relaciones con otras clases.
    private Provincia provincia;

}
