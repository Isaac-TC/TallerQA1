# --- Memento ---
class GameMemento:
    def __init__(self, level, health):
        self.level = level
        self.health = health

# --- Originator ---
class Player:
    def __init__(self):
        self.level = 1
        self.health = 100

    def play(self):
        self.level += 1
        self.health -= 10
        print(f"▶ Jugando... Nivel: {self.level}, Vida: {self.health}")

    def save(self):
        print("💾 Guardando progreso...")
        return GameMemento(self.level, self.health)

    def restore(self, memento):
        self.level = memento.level
        self.health = memento.health
        print(f"⏪ Restaurado → Nivel: {self.level}, Vida: {self.health}")

# --- Caretaker ---
class GameHistory:
    def __init__(self):
        self.saves = []

    def add(self, memento):
        self.saves.append(memento)

    def undo(self):
        if self.saves:
            return self.saves.pop()
        return None


# --- Uso ---
player = Player()
history = GameHistory()

# Primera partida
player.play()   # Nivel 2, Vida 90
history.add(player.save())   # Guardar

# Avanzamos más
player.play()   # Nivel 3, Vida 80
player.play()   # Nivel 4, Vida 70

# Restaurar último guardado
player.restore(history.undo())
