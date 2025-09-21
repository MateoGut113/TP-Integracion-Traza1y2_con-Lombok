package entidadesTraza2;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString (exclude = "articuloInsumo")

public class ArticuloManufacturadoDetalle {
    private Long id;
    private int cantidad;

    //Relaciones con otras clases
    private ArticuloInsumo articuloInsumo;

}
