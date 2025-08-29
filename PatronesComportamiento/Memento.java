package PatronesComportamiento;


final class GameMemento {
    private final int level;
    private final int health;

    public GameMemento(int level, int health) {
        this.level = level;
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }
}


class Player {
    private int level = 1;
    private int health = 100;

    public void play() {
        level++;
        health -= 10;
        System.out.println(" Jugando... Nivel: " + level + " | Vida: " + health);
    }

    public GameMemento save() {
        System.out.println("Guardando progreso...");
        return new GameMemento(level, health);
    }

    public void restore(GameMemento memento) {
        this.level = memento.getLevel();
        this.health = memento.getHealth();
        System.out.println(" Progreso restaurado → Nivel: " + level + " | Vida: " + health);
    }
}


public class Memento {
    public static void main(String[] args) {
        Player player = new Player();

        
        player.play();
        player.play();

        
        GameMemento checkpoint = player.save();

        
        player.play();
        player.play();

        player.restore(checkpoint);
    }
}
