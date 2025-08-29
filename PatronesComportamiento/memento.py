
class GameMemento:
    def __init__(self, level, health):
        self.level = level
        self.health = health

class Player:
    def __init__(self):
        self.level = 1
        self.health = 100

    def play(self):
        self.level += 1
        self.health -= 10
        print(f" Jugando... Nivel: {self.level}, Vida: {self.health}")

    def save(self):
        print(" Guardando progreso...")
        return GameMemento(self.level, self.health)

    def restore(self, memento):
        self.level = memento.level
        self.health = memento.health
        print(f" Restaurado → Nivel: {self.level}, Vida: {self.health}")


class GameHistory:
    def __init__(self):
        self.saves = []

    def add(self, memento):
        self.saves.append(memento)

    def undo(self):
        if self.saves:
            return self.saves.pop()
        return None



player = Player()
history = GameHistory()


player.play()   
history.add(player.save())   


player.play()   
player.play()   

player.restore(history.undo())
