
package PatronesComportamiento;

import java.util.ArrayList;
import java.util.List;

// Mediador
interface TorreControl {
    void enviar(String mensaje, Avion emisor);
    void registrar(Avion avion);
}

// Mediador concreto
class TorreAeropuerto implements TorreControl {
    private final List<Avion> aviones = new ArrayList<>();

    @Override
    public void enviar(String mensaje, Avion emisor) {
        for (Avion a : aviones) {
            if (a != emisor) a.recibir(mensaje);
        }
    }

    @Override
    public void registrar(Avion avion) {
        aviones.add(avion);
    }
}

// Colega
class Avion {
    private final String nombre;
    private final TorreControl torre;

    public Avion(String nombre, TorreControl torre) {
        this.nombre = nombre;
        this.torre = torre;
        this.torre.registrar(this);
    }

    public void transmitir(String mensaje) {
        System.out.println("✈️ " + nombre + " transmite: " + mensaje);
        torre.enviar(mensaje, this);
    }

    public void recibir(String mensaje) {
        System.out.println("📡 " + nombre + " recibe: " + mensaje);
    }
}

// Demo
public class AirTrafficMediatorDemo {
    public static void main(String[] args) {
        TorreControl torre = new TorreAeropuerto();

        Avion a1 = new Avion("LATAM123", torre);
        Avion a2 = new Avion("Avianca456", torre);
        Avion a3 = new Avion("KLM789", torre);

        a1.transmitir("Solicitando permiso para aterrizar.");
        a2.transmitir("Rodando hacia la pista 18.");
        a3.transmitir("Listo para despegar desde pista 36.");
    }
}
