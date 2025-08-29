package PatronesComportamiento;

// --- Memento ---
final class GameMemento {
    private final int level;
    private final int health;

    public GameMemento(int level, int health) {
        this.level = level;
        this.health = health;
    }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
}

// --- Originator ---
class Player {
    private int level = 1;
    private int health = 100;

    public void play() {
        level += 1;
        health -= 10;
        System.out.println("▶ Jugando... Nivel: " + level + ", Vida: " + health);
    }

    public GameMemento save() {
        System.out.println("💾 Guardando progreso...");
        return new GameMemento(level, health);
    }

    public void restore(GameMemento m) {
        if (m == null) {
            System.out.println("⚠️ No hay guardados para restaurar.");
            return;
        }
        this.level = m.getLevel();
        this.health = m.getHealth();
        System.out.println("⏪ Restaurado → Nivel: " + level + ", Vida: " + health);
    }
}

// --- Caretaker ---
import java.util.ArrayDeque;
import java.util.Deque;

class GameHistory {
    private final Deque<GameMemento> saves = new ArrayDeque<>();

    public void add(GameMemento m) { saves.push(m); }     // LIFO
    public GameMemento undo() { return saves.isEmpty() ? null : saves.pop(); }
}

// --- Demo ---
public class GameMementoDemo {
    public static void main(String[] args) {
        Player player = new Player();
        GameHistory history = new GameHistory();

        // Primera partida
        player.play();                  // Nivel 2, Vida 90
        history.add(player.save());     // Guardar

        // Avanzamos más
        player.play();                  // Nivel 3, Vida 80
        player.play();                  // Nivel 4, Vida 70

        // Restaurar último guardado
        player.restore(history.undo()); // Vuelve a Nivel 2, Vida 90
    }
}
