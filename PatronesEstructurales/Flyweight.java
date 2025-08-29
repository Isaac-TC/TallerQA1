package PatronesEstructurales;
import java.util.*;


final class FoodType {
    private final String nombre;
    private final String imagen;
    private final String sonido;

    public FoodType(String nombre, String imagen, String sonido) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.sonido = sonido;
    }

    public void mostrar(int mesa, String lugar) {
        System.out.printf("Servir %s en mesa %d, lugar %s con imagen %s y sonido %s%n",
                nombre, mesa, lugar, imagen, sonido);
    }
}


final class FoodFactory {
    private static final Map<String, FoodType> tipos = new HashMap<>();

    
    public static FoodType obtenerTipo(String nombre, String imagen, String sonido) {
        return tipos.computeIfAbsent(nombre, n -> new FoodType(n, imagen, sonido));
    }

    public static int cacheSize() {
        return tipos.size();
    }
}


final class Dish {
    private final FoodType tipo; 
    private final int mesa;      
    private final String lugar;  

    public Dish(FoodType tipo, int mesa, String lugar) {
        this.tipo = tipo;
        this.mesa = mesa;
        this.lugar = lugar;
    }

    public void servir() {
        tipo.mostrar(mesa, lugar);
    }
}

public class Flyweight{
    public static void main(String[] args) {
        FoodType pizza = FoodFactory.obtenerTipo("Pizza", "pizza.png", "croc.mp3");
        FoodType burger = FoodFactory.obtenerTipo("Hamburguesa", "burger.png", "crunch.mp3");

        List<Dish> ordenes = List.of(
                new Dish(pizza, 1, "A"),
                new Dish(pizza, 1, "B"),
                new Dish(burger, 2, "C"),
                new Dish(burger, 2, "D")
        );

        ordenes.forEach(Dish::servir);

        System.out.println("Tipos en caché (flyweights): " + FoodFactory.cacheSize());
    }
}
