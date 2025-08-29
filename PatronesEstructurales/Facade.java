package PatronesEstructurales;

class CPU {
    void encender() { System.out.println("CPU: encendida"); }
    void apagar() { System.out.println("CPU: apagada"); }
}

class RAM {
    void cargarKernel() { System.out.println("RAM: kernel cargado"); }
    void liberar() { System.out.println("RAM: memoria liberada"); }
}

class Disco {
    void leerBootloader() { System.out.println("Disco: bootloader leído"); }
    void guardarEstado() { System.out.println("Disco: estado guardado en disco"); }
}

class GPU {
    void activar(String modo) { System.out.println("GPU: activada (modo " + modo + ")"); }
    void desactivar() { System.out.println("GPU: desactivada"); }
}

class SistemaOperativo {
    void iniciar() { System.out.println("SO: iniciado"); }
    void apagar() { System.out.println("SO: apagado"); }
    void modoJuego() { System.out.println("SO: modo juego habilitado"); }
    void modoAhorro() { System.out.println("SO: modo ahorro habilitado"); }
}


class Computador {
    private final CPU cpu = new CPU();
    private final RAM ram = new RAM();
    private final Disco disco = new Disco();
    private final GPU gpu = new GPU();
    private final SistemaOperativo so = new SistemaOperativo();

    public void encender() {
        System.out.println("Encendiendo PC...");
        cpu.encender();
        disco.leerBootloader();
        ram.cargarKernel();
        so.iniciar();
        gpu.activar("normal");
        System.out.println("PC lista.\n");
    }

    public void apagar() {
        System.out.println("Apagando PC...");
        so.apagar();
        disco.guardarEstado();
        gpu.desactivar();
        ram.liberar();
        cpu.apagar();
        System.out.println("PC apagada.\n");
    }

    public void modoJuego() {
        System.out.println("Activando modo JUEGO 🎮");
        so.modoJuego();
        gpu.activar("alto rendimiento");
        System.out.println("FPS felices.\n");
    }

    public void modoAhorro() {
        System.out.println("Activando modo AHORRO 🔋");
        so.modoAhorro();
        gpu.activar("eco");
        System.out.println("Batería agradecida.\n");
    }
}


public class Facade {
    public static void main(String[] args) {
        Computador pc = new Computador();
        pc.encender();
        pc.modoJuego();
        pc.modoAhorro();
        pc.apagar();
    }
}
