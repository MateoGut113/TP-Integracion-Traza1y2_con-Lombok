package entidadesTraza1;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Provincia {
    private String nombre;

    //Relaciones con otras clases.
    private Pais pais;

}
