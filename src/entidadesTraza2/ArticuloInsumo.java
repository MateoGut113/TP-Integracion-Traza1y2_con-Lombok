package entidadesTraza2;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString (callSuper = true, exclude = "esParaElaborar")

public class ArticuloInsumo extends Articulo{
    private Double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private int stockMaximo;
    private Boolean esParaElaborar;

    //Relaciones con otras clases

    //Metodo para verificar stock
    public void verificarStock(){
        if (stockActual < stockMinimo){
            System.out.println("Se debe comprar mas de: "+denominacion+" en sucursal: "+getSucursal()+", stock disponible: "+stockActual+".");
        }
    }

}
