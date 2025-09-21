import entidadesTraza1.*;
import entidadesTraza2.*;
import repositorios.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //--> Iniciamos el Repositorio: Traza 1 <--
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();
        InMemoryRepository<Sucursal> sucursalRepository = new InMemoryRepository<>();
        //--> Iniciamos los Repositorios: Traza 2 <--
        InMemoryRepository<Categoria> categoriaRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloInsumo> articuloInsumoRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloManufacturado> articuloManufacturadoRepository = new InMemoryRepository<>();
        InMemoryRepository<UnidadMedida> unidadMedidaRepository = new InMemoryRepository<>();

            // ---> Creacion de instancias necesarias de Traza 1. <---

        //Creamos el pais donde funcionara en un principio nuestro sistema
        Pais argentina = Pais.builder().nombre("Argentina")
                .build();

        //Creacion de provincias disponibles para nuestro sistema (incluyendo localidades y domicilios)
            //Creacion de la provincia de Buenos Aires
        Provincia buenosAires = Provincia.builder().nombre("Buenos Aires").pais(argentina)
                .build();

            //Creacion de Localidad para la provincia de Buenos Aires
        Localidad caba = Localidad.builder().nombre("CABA").provincia(buenosAires)
                .build();

            //Creacion de Domicilio
        Domicilio domicilioCABA1 = Domicilio.builder().numero(1).cp(1094).calle("Calle Almagro").localidad(caba)
                .build();

            //Creacion de Domicilio
        Domicilio domicilioCABA2 = Domicilio.builder().numero(2).cp(1094).calle("Calle Atlanta").localidad(caba)
                .build();

            //Creacion de Localidad para la provincia de Buenos Aires
        Localidad laPlata = Localidad.builder().nombre("La Plata").provincia(buenosAires)
                .build();

            //Creacion de Domicilio
        Domicilio domicilioLAPLATA1 = Domicilio.builder().numero(2).cp(1919).calle("Calle Lobo").localidad(laPlata)
                .build();

            //Creacion de Domicilio
        Domicilio domicilioLAPLATA2 = Domicilio.builder().numero(2).cp(1919).calle("Calle León").localidad(laPlata)
                .build();

        //Creacion de sucursales
        Sucursal sucursal1 = Sucursal.builder().nombre("Sucursal 1")
                .horarioApertura(LocalTime.of(9,00))
                .horarioCierre(LocalTime.of(20,00))
                .domicilio(domicilioCABA1)
                .es_Casa_Motriz(false)
                .build();

        Sucursal sucursal2 = Sucursal.builder().nombre("Sucursal 2")
                .horarioApertura(LocalTime.of(8,00))
                .horarioCierre(LocalTime.of(18,00))
                .domicilio(domicilioLAPLATA1)
                .es_Casa_Motriz(true)
                .build();

        Sucursal sucursal3 = Sucursal.builder().nombre("Sucursal 3")
                .horarioApertura(LocalTime.of(10,30))
                .horarioCierre(LocalTime.of(22,00))
                .domicilio(domicilioCABA2)
                .es_Casa_Motriz(true)
                .build();

        Sucursal sucursal4 = Sucursal.builder().nombre("Sucursal 4")
                .horarioApertura(LocalTime.of(8,00))
                .horarioCierre(LocalTime.of(20,00))
                .domicilio(domicilioLAPLATA2)
                .es_Casa_Motriz(false)
                .build();
        // Guardar sucursales en el repositorio
        sucursalRepository.save(sucursal1);
        sucursalRepository.save(sucursal2);
        sucursalRepository.save(sucursal3);
        sucursalRepository.save(sucursal4);

        //Creacion de empresas
        Empresa empresa1 = Empresa.builder().nombre("Empresa 1").cuit(304050).razonSocial("Razon Social 1").logo("Tigre")
                .sucursales(new HashSet<>(Set.of(sucursal1,sucursal2)))
                .build();

        Empresa empresa2 = Empresa.builder().nombre("Empresa 2").cuit(405060).razonSocial("Razon Social 2").logo("Lobo")
                .sucursales(new HashSet<>(Set.of(sucursal3,sucursal4)))
                .build();

        // Guardar empresas en el repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);


         //------------>CAMBIO DE TRAZA<------------


            // ---> Creacion de instancias necesarias de Traza 2. <---

        //Creamos las categorias disponibles que usaremos para todas las sucursales
        Categoria pizza = Categoria.builder().denominacion("Pizzas")
                .build();
        Categoria sandwich = Categoria.builder().denominacion("Sandwiches")
                .build();
        Categoria lomo = Categoria.builder().denominacion("Lomos")
                .build();
        Categoria imsumo = Categoria.builder().denominacion("Insumos")
                .build();

            // Guardar categorias en su repositorio
        categoriaRepository.save(pizza);
        categoriaRepository.save(lomo);
        categoriaRepository.save(sandwich);
        categoriaRepository.save(imsumo);

        //Creamos las unidades de medidas que usaremos para todas las sucursales
        UnidadMedida litro = UnidadMedida.builder().denomincion("Litro")
                .build();
        UnidadMedida kilogramo = UnidadMedida.builder().denomincion("Kilogramo")
                .build();
        UnidadMedida gramo = UnidadMedida.builder().denomincion("Gramo")
                .build();

            // Guardar unidades de medidas en su repositorio
        unidadMedidaRepository.save(litro);
        unidadMedidaRepository.save(kilogramo);
        unidadMedidaRepository.save(gramo);

        //Creamos los articulos insumos que dispondremos
            //Para Sucursal 1:
        ArticuloInsumo aceiteSucursal1 = ArticuloInsumo.builder().denominacion("Aceite").precioCompra(500.0)
                .sucursal(sucursal1)
                .precioVenta(700.0)
                .stockMaximo(100).stockActual(70)
                .stockMinimo(10)
                .esParaElaborar(false)
                .unidadMedida(litro)
                .build();
        ArticuloInsumo salSucursal1 = ArticuloInsumo.builder().denominacion("Sal").precioCompra(400.0)
                .sucursal(sucursal1)
                .precioVenta(500.0)
                .stockMaximo(110).stockActual(80)
                .stockMinimo(20)
                .esParaElaborar(false)
                .unidadMedida(gramo)
                .build();
        ArticuloInsumo carneSucursal1 = ArticuloInsumo.builder().denominacion("Carne").precioCompra(5500.0)
                .sucursal(sucursal1)
                .precioVenta(6500.0)
                .stockMaximo(90).stockActual(60)
                .stockMinimo(10)
                .esParaElaborar(true)
                .unidadMedida(kilogramo)
                .build();
        ArticuloInsumo harinaSucursal1 = ArticuloInsumo.builder().denominacion("Harina").precioCompra(700.0)
                .sucursal(sucursal1)
                .precioVenta(850.0)
                .stockMaximo(70).stockActual(50)
                .stockMinimo(10)
                .esParaElaborar(true)
                .unidadMedida(gramo)
                .build();
        // Guardar Articulos insumos en su repositorio
        articuloInsumoRepository.save(aceiteSucursal1);
        articuloInsumoRepository.save(salSucursal1);
        articuloInsumoRepository.save(carneSucursal1);
        articuloInsumoRepository.save(harinaSucursal1);
        sucursal1.setArticulos(new HashSet<>(Set.of(aceiteSucursal1, carneSucursal1, salSucursal1, harinaSucursal1)));

            //Para Sucursal 2:
        ArticuloInsumo aceiteSucursal2 = ArticuloInsumo.builder().denominacion("Aceite").precioCompra(500.0)
                .sucursal(sucursal2)
                .precioVenta(650.0)
                .stockMaximo(110).stockActual(80)
                .stockMinimo(20)
                .esParaElaborar(false)
                .unidadMedida(litro)
                .build();
        ArticuloInsumo salSucursal2 = ArticuloInsumo.builder().denominacion("Sal").precioCompra(400.0)
                .sucursal(sucursal2)
                .precioVenta(700.0)
                .stockMaximo(120).stockActual(90)
                .stockMinimo(25)
                .esParaElaborar(false)
                .unidadMedida(gramo)
                .build();
        ArticuloInsumo carneSucursal2 = ArticuloInsumo.builder().denominacion("Carne").precioCompra(5500.0)
                .sucursal(sucursal2)
                .precioVenta(5900.0)
                .stockMaximo(100).stockActual(70)
                .stockMinimo(20)
                .esParaElaborar(true)
                .unidadMedida(kilogramo)
                .build();
        ArticuloInsumo harinaSucursal2 = ArticuloInsumo.builder().denominacion("Harina").precioCompra(700.0)
                .sucursal(sucursal2)
                .precioVenta(800.0)
                .stockMaximo(80).stockActual(60)
                .stockMinimo(10)
                .esParaElaborar(true)
                .unidadMedida(gramo)
                .build();
        // Guardar Articulos insumos en su repositorio
        articuloInsumoRepository.save(aceiteSucursal2);
        articuloInsumoRepository.save(salSucursal2);
        articuloInsumoRepository.save(carneSucursal2);
        articuloInsumoRepository.save(harinaSucursal2);
        sucursal2.setArticulos(new HashSet<>(Set.of(aceiteSucursal2, carneSucursal2, salSucursal2, harinaSucursal2)));

        //Para Sucursal 3:
        ArticuloInsumo aceiteSucursal3 = ArticuloInsumo.builder().denominacion("Aceite").precioCompra(500.0)
                .sucursal(sucursal3)
                .precioVenta(600.0)
                .stockMaximo(80).stockActual(40)
                .stockMinimo(15)
                .esParaElaborar(false)
                .unidadMedida(litro)
                .build();
        ArticuloInsumo salSucursal3 = ArticuloInsumo.builder().denominacion("Sal").precioCompra(400.0)
                .sucursal(sucursal3)
                .precioVenta(600.0)
                .stockMaximo(90).stockActual(80)
                .stockMinimo(20)
                .esParaElaborar(false)
                .unidadMedida(gramo)
                .build();
        ArticuloInsumo carneSucursal3 = ArticuloInsumo.builder().denominacion("Carne").precioCompra(5500.0)
                .sucursal(sucursal3)
                .precioVenta(6100.0)
                .stockMaximo(70).stockActual(30)
                .stockMinimo(15)
                .esParaElaborar(true)
                .unidadMedida(kilogramo)
                .build();
        ArticuloInsumo harinaSucursal3 = ArticuloInsumo.builder().denominacion("Harina").precioCompra(700.0)
                .sucursal(sucursal3)
                .precioVenta(800.0)
                .stockMaximo(80).stockActual(50)
                .stockMinimo(20)
                .esParaElaborar(true)
                .unidadMedida(gramo)
                .build();
        // Guardar Articulos insumos en su repositorio
        articuloInsumoRepository.save(aceiteSucursal3);
        articuloInsumoRepository.save(salSucursal3);
        articuloInsumoRepository.save(carneSucursal3);
        articuloInsumoRepository.save(harinaSucursal3);
        sucursal3.setArticulos(new HashSet<>(Set.of(aceiteSucursal3, carneSucursal3, salSucursal3, harinaSucursal3)));

        //Para Sucursal 4:
        ArticuloInsumo aceiteSucursal4 = ArticuloInsumo.builder().denominacion("Aceite").precioCompra(500.0)
                .sucursal(sucursal4)
                .precioVenta(600.0)
                .stockMaximo(115).stockActual(60)
                .stockMinimo(20)
                .esParaElaborar(false)
                .unidadMedida(litro)
                .build();
        ArticuloInsumo salSucursal4 = ArticuloInsumo.builder().denominacion("Sal").precioCompra(400.0)
                .sucursal(sucursal4)
                .precioVenta(500.0)
                .stockMaximo(80).stockActual(60)
                .stockMinimo(15)
                .esParaElaborar(false)
                .unidadMedida(gramo)
                .build();
        ArticuloInsumo carneSucursal4 = ArticuloInsumo.builder().denominacion("Carne").precioCompra(5500.0)
                .sucursal(sucursal4)
                .precioVenta(6000.0)
                .stockMaximo(110).stockActual(70)
                .stockMinimo(30)
                .esParaElaborar(true)
                .unidadMedida(kilogramo)
                .build();
        ArticuloInsumo harinaSucursal4 = ArticuloInsumo.builder().denominacion("Harina").precioCompra(700.0)
                .sucursal(sucursal4)
                .precioVenta(800.0)
                .stockMaximo(75).stockActual(40)
                .stockMinimo(20)
                .esParaElaborar(true)
                .unidadMedida(gramo)
                .build();
        // Guardar Articulos insumos en su repositorio
        articuloInsumoRepository.save(aceiteSucursal4);
        articuloInsumoRepository.save(salSucursal4);
        articuloInsumoRepository.save(carneSucursal4);
        articuloInsumoRepository.save(harinaSucursal4);
        sucursal4.setArticulos(new HashSet<>(Set.of(aceiteSucursal4, carneSucursal4, salSucursal4, harinaSucursal4)));

        //Creamos imagenes para las categorias disponibles
        Imagen imagen1 = Imagen.builder().denominacion("HawainaPizza1")
                .build();
        Imagen imagen2 = Imagen.builder().denominacion("HawainaPizza2")
                .build();
        Imagen imagen3 = Imagen.builder().denominacion("HawainaPizza3")
                .build();
        Imagen imagen4 = Imagen.builder().denominacion("LomoCompleto1")
                .build();
        Imagen imagen5 = Imagen.builder().denominacion("LomoCompleto2")
                .build();
        Imagen imagen6 = Imagen.builder().denominacion("LomoCompleto3")
                .build();

        //Creamos los detalles de los articulos manufacturados
        //Para la sucursal 1:
        ArticuloManufacturadoDetalle detallePizzaHawaina1Sucursal1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal1)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina2Sucursal1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(2).articuloInsumo(harinaSucursal1)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina3Sucursal1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal1)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto1Sucursal1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal1)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto2Sucursal1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal1)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto3Sucursal1 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(carneSucursal1)
                .build();

        //Para la sucursal 2:
        ArticuloManufacturadoDetalle detallePizzaHawaina1Sucursal2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal2)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina2Sucursal2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(2).articuloInsumo(harinaSucursal2)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina3Sucursal2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal2)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto1Sucursal2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal2)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto2Sucursal2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal2)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto3Sucursal2 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(carneSucursal2)
                .build();

        //Para la sucursal 3:
        ArticuloManufacturadoDetalle detallePizzaHawaina1Sucursal3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal3)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina2Sucursal3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(2).articuloInsumo(harinaSucursal3)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina3Sucursal3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal3)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto1Sucursal3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal3)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto2Sucursal3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal3)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto3Sucursal3 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(carneSucursal3)
                .build();

        //Para la sucursal 4:
        ArticuloManufacturadoDetalle detallePizzaHawaina1Sucursal4 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal4)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina2Sucursal4 = ArticuloManufacturadoDetalle.builder()
                .cantidad(2).articuloInsumo(harinaSucursal4)
                .build();
        ArticuloManufacturadoDetalle detallePizzaHawaina3Sucursal4 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal4)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto1Sucursal4 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(salSucursal4)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto2Sucursal4 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(aceiteSucursal4)
                .build();
        ArticuloManufacturadoDetalle detalleLomoCompleto3Sucursal4 = ArticuloManufacturadoDetalle.builder()
                .cantidad(1).articuloInsumo(carneSucursal4)
                .build();


        //Creamos los articulos manufacturados
        //Primero la pizza hawaina
            //Para la sucursal 1:
        ArticuloManufacturado pizzaHawainaSucursal1 = ArticuloManufacturado.builder().denominacion("Pizza Hawaina")
                .sucursal(sucursal1)
                .descripcion("Base de salsa de tomate, queso fundido, jamón cocido y trozos jugosos de ananá")
                .preparacion("Hornear por 10 minutos")
                .tiempoEstimadoMinutos(10)
                .precioVenta(4000.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizzaHawaina1Sucursal1,
                        detallePizzaHawaina2Sucursal1, detallePizzaHawaina3Sucursal1)))
                .imagenes(new HashSet<>(Set.of(imagen1, imagen2, imagen3)))
                .build();

            //Para la sucursal 2:
        ArticuloManufacturado pizzaHawainaSucursal2 = ArticuloManufacturado.builder().denominacion("Pizza Hawaina")
                .sucursal(sucursal2)
                .descripcion("Base de salsa de tomate, queso fundido, jamón cocido y trozos jugosos de ananá")
                .preparacion("Hornear por 15 minutos")
                .tiempoEstimadoMinutos(15)
                .precioVenta(4500.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizzaHawaina1Sucursal2,
                        detallePizzaHawaina2Sucursal2, detallePizzaHawaina3Sucursal2)))
                .imagenes(new HashSet<>(Set.of(imagen1, imagen2, imagen3)))
                .build();

            //Para la sucursal 3:
        ArticuloManufacturado pizzaHawainaSucursal3 = ArticuloManufacturado.builder().denominacion("Pizza Hawaina")
                .sucursal(sucursal3)
                .descripcion("Base de salsa de tomate, queso fundido, jamón cocido y trozos jugosos de ananá")
                .preparacion("Hornear por 15 minutos")
                .tiempoEstimadoMinutos(15)
                .precioVenta(5000.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizzaHawaina1Sucursal3,
                        detallePizzaHawaina2Sucursal3, detallePizzaHawaina3Sucursal3)))
                .imagenes(new HashSet<>(Set.of(imagen1, imagen2, imagen3)))
                .build();

            //Para la sucursal 4:
        ArticuloManufacturado pizzaHawainaSucursal4 = ArticuloManufacturado.builder().denominacion("Pizza Hawaina")
                .sucursal(sucursal4)
                .descripcion("Base de salsa de tomate, queso fundido, jamón cocido y trozos jugosos de ananá")
                .preparacion("Hornear por 20 minutos")
                .tiempoEstimadoMinutos(20)
                .precioVenta(5500.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizzaHawaina1Sucursal4,
                        detallePizzaHawaina2Sucursal4, detallePizzaHawaina3Sucursal4)))
                .imagenes(new HashSet<>(Set.of(imagen1, imagen2, imagen3)))
                .build();

        // Guardar Articulos manufacturados en su repositorio
        articuloManufacturadoRepository.save(pizzaHawainaSucursal1);
        articuloManufacturadoRepository.save(pizzaHawainaSucursal2);
        articuloManufacturadoRepository.save(pizzaHawainaSucursal3);
        articuloManufacturadoRepository.save(pizzaHawainaSucursal4);


        //Luego el lomo completo
        //Para la sucursal 1:
        ArticuloManufacturado lomoCompletoSucursal1 = ArticuloManufacturado.builder().denominacion("Lomo Completo")
                .sucursal(sucursal1)
                .descripcion("Pan con mayonesa y jamón, queso, lechuga, tomate y huevos fritos")
                .preparacion("Cocinar por 45 minutos.")
                .tiempoEstimadoMinutos(45)
                .precioVenta(5000.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detalleLomoCompleto1Sucursal1,
                        detalleLomoCompleto2Sucursal1, detalleLomoCompleto3Sucursal1)))
                .imagenes(new HashSet<>(Set.of(imagen4, imagen5, imagen6)))
                .build();

        //Para la sucursal 2:
        ArticuloManufacturado lomoCompletoSucursal2 = ArticuloManufacturado.builder().denominacion("Lomo Completo")
                .sucursal(sucursal2)
                .descripcion("Pan con mayonesa y jamón, queso, lechuga, tomate y huevos fritos")
                .preparacion("Cocinar por 50 minutos.")
                .tiempoEstimadoMinutos(50)
                .precioVenta(5500.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detalleLomoCompleto1Sucursal2,
                        detalleLomoCompleto2Sucursal2, detalleLomoCompleto3Sucursal2)))
                .imagenes(new HashSet<>(Set.of(imagen4, imagen5, imagen6)))
                .build();

        //Para la sucursal 3:
        ArticuloManufacturado lomoCompletoSucursal3 = ArticuloManufacturado.builder().denominacion("Lomo Completo")
                .sucursal(sucursal3)
                .descripcion("Pan con mayonesa y jamón, queso, lechuga, tomate y huevos fritos")
                .preparacion("Cocinar por 50 minutos.")
                .tiempoEstimadoMinutos(50)
                .precioVenta(6000.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detalleLomoCompleto1Sucursal3,
                        detalleLomoCompleto2Sucursal3, detalleLomoCompleto3Sucursal3)))
                .imagenes(new HashSet<>(Set.of(imagen4, imagen5, imagen6)))
                .build();

        //Para la sucursal 4:
        ArticuloManufacturado lomoCompletoSucursal4 = ArticuloManufacturado.builder().denominacion("Lomo Completo")
                .sucursal(sucursal4)
                .descripcion("Pan con mayonesa y jamón, queso, lechuga, tomate y huevos fritos")
                .preparacion("Cocinar por 55 minutos.")
                .tiempoEstimadoMinutos(55)
                .precioVenta(6500.0)
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detalleLomoCompleto1Sucursal4,
                        detalleLomoCompleto2Sucursal4, detalleLomoCompleto3Sucursal4)))
                .imagenes(new HashSet<>(Set.of(imagen4, imagen5, imagen6)))
                .build();

            // Guardar Articulos manufacturados en su repositorio
        articuloManufacturadoRepository.save(lomoCompletoSucursal1);
        articuloManufacturadoRepository.save(lomoCompletoSucursal2);
        articuloManufacturadoRepository.save(lomoCompletoSucursal3);
        articuloManufacturadoRepository.save(lomoCompletoSucursal4);
        // Guardamos los Articulos manufacturados en la lista sin pisar a los insumos previamente guardados
        sucursal1.getArticulos().addAll(Set.of(pizzaHawainaSucursal1, lomoCompletoSucursal1));
        sucursal2.getArticulos().addAll(Set.of(pizzaHawainaSucursal2, lomoCompletoSucursal2));
        sucursal3.getArticulos().addAll(Set.of(pizzaHawainaSucursal3, lomoCompletoSucursal3));
        sucursal4.getArticulos().addAll(Set.of(pizzaHawainaSucursal4, lomoCompletoSucursal4));



        //---------------------------------------------------------------------------------------------------------------//

        //----->Probamos metodos que fusionen ambas trazas<-----

        //----->PROBAMOS EL REPOSITORIO<------
        System.out.println("\nPRUEBA DEL NUEVO REPOSITORIO DEL SISTEMA:");

        //MOSTRAMOS las EMPRESAS previamente guardadas
        System.out.println("\nTodas las empresas disponibles en el sistema son:");
        List<Empresa> todasLasEmpresas = empresaRepository.findAll();
        todasLasEmpresas.forEach(System.out::println);

        //MOSTRAMOS todas las SUCURSALES en el sistema:
        System.out.println("\nTodas las sucursales disponibles en el sistema son:");
        List<Sucursal> todasLasSucursales = sucursalRepository.findAll();
        todasLasSucursales.forEach(System.out::println);

        //MOSTRAMOS las CATEGORIAS previamente guardadas
        System.out.println("\nTodas las categorias disponibles en el sistema son:");
        List<Categoria> todasLasCategorias = categoriaRepository.findAll();
        todasLasCategorias.forEach(System.out::println);

        //MOSTRAMOS los PRECIOS de todas las sucursales.
        System.out.println("\nLos precios en nuestras sucursales son:");

        //MOSTRAMOS los precios de los LOMOS de todas las sucursales
        System.out.println("\nLos precios de los lomos son:");
        List<ArticuloManufacturado> todosLosPreciosdeLomos = articuloManufacturadoRepository.genericFindByField("denominacion", "Lomo Completo");
        todosLosPreciosdeLomos.forEach(System.out::println);

        //MOSTRAMOS los precios de las PIZZAS de todas las sucursales
        System.out.println("\nLos precios de las pizzas son:");
        List<ArticuloManufacturado> todosLosPreciosdePizzas = articuloManufacturadoRepository.genericFindByField("denominacion", "Pizza Hawaina");
        todosLosPreciosdePizzas.forEach(System.out::println);

        //MOSTRAMOS el STOCK de insumos de casa sucursal
        System.out.println("\nStock de insumos por sucursal:");
        List<ArticuloInsumo> todosLosInsumos = articuloInsumoRepository.findAll();
        todosLosInsumos.forEach(System.out::println);

        //ACTUALIZAR el STOCK de insumos de una sucursal.
        System.out.println("\nActualizamos el stock de la Sucursal 1...");
        ArticuloInsumo aceiteSucursal1actualizado = ArticuloInsumo.builder().denominacion("Aceite").precioCompra(500.0)
                .sucursal(sucursal1)
                .precioVenta(700.0)
                .stockMaximo(100).stockActual(25)
                .stockMinimo(10)
                .esParaElaborar(false)
                .unidadMedida(litro)
                .build();
        ArticuloInsumo salSucursal1actualizado = ArticuloInsumo.builder().denominacion("Sal").precioCompra(400.0)
                .sucursal(sucursal1)
                .precioVenta(500.0)
                .stockMaximo(110).stockActual(40)
                .stockMinimo(20)
                .esParaElaborar(false)
                .unidadMedida(gramo)
                .build();
        ArticuloInsumo carneSucursal1actualizado = ArticuloInsumo.builder().denominacion("Carne").precioCompra(5500.0)
                .sucursal(sucursal1)
                .precioVenta(6500.0)
                .stockMaximo(90).stockActual(20)
                .stockMinimo(10)
                .esParaElaborar(true)
                .unidadMedida(kilogramo)
                .build();
        ArticuloInsumo harinaSucursal1actualizado = ArticuloInsumo.builder().denominacion("Harina").precioCompra(700.0)
                .sucursal(sucursal1)
                .precioVenta(850.0)
                .stockMaximo(70).stockActual(9)
                .stockMinimo(10)
                .esParaElaborar(true)
                .unidadMedida(gramo)
                .build();

        //Guardamos los cambios
        articuloInsumoRepository.genericUpdate(1L, aceiteSucursal1actualizado);
        articuloInsumoRepository.genericUpdate(2L, salSucursal1actualizado);
        articuloInsumoRepository.genericUpdate(3L, carneSucursal1actualizado);
        articuloInsumoRepository.genericUpdate(4L, harinaSucursal1actualizado);

        //Aqui mostramos los cambios hechos
        System.out.println("\nStock de articulos de Sucursal 1 despues de actualizacion:");
        List<ArticuloInsumo> stockVerificado = articuloInsumoRepository.genericFindByField("sucursal", sucursal1);
        stockVerificado.forEach(System.out::println);

        //Al mismo tiempo VERIFICAMOS stock
        harinaSucursal1actualizado.verificarStock();
        salSucursal1actualizado.verificarStock();
        aceiteSucursal1actualizado.verificarStock();
        carneSucursal1actualizado.verificarStock();

    }
}
