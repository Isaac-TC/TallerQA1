
package PatronesComportamiento;

import java.util.ArrayList;
import java.util.List;


interface TorreControl {
    void enviar(String mensaje, Avion emisor);
    void registrar(Avion avion);
}

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

class Avion {
    private final String nombre;
    private final TorreControl torre;

    public Avion(String nombre, TorreControl torre) {
        this.nombre = nombre;
        this.torre = torre;
        this.torre.registrar(this);
    }

    public void transmitir(String mensaje) {
        System.out.println("" + nombre + " transmite: " + mensaje);
        torre.enviar(mensaje, this);
    }

    public void recibir(String mensaje) {
        System.out.println("" + nombre + " recibe: " + mensaje);
    }
}


public class Mediator {
    public static void main(String[] args) {
        TorreControl torre = new TorreAeropuerto();

        Avion a1 = new Avion("LATAM", torre);
        Avion a2 = new Avion("Avianca", torre);
        Avion a3 = new Avion("KLM", torre);

        a1.transmitir("Solicitando permiso para aterrizar.");
        a2.transmitir("Rodando hacia la pista 18.");
        a3.transmitir("Listo para despegar desde pista 36.");
    }
}
