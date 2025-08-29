class TipoComida:
    def __init__(self, nombre, imagen, sonido):
        self.nombre = nombre
        self.imagen = imagen
        self.sonido = sonido

    def mostrar(self, mesa, lugar):
        print(f"🍽️ Servir {self.nombre} en mesa {mesa}, lugar {lugar} "
              f"con imagen {self.imagen} y sonido {self.sonido}")


class FabricaComida:
    _tipos = {}

    @staticmethod
    def obtener_tipo(nombre, imagen, sonido):
        if nombre not in FabricaComida._tipos:
            FabricaComida._tipos[nombre] = TipoComida(nombre, imagen, sonido)
        return FabricaComida._tipos[nombre]


class Plato:
    def __init__(self, tipo, mesa, lugar):
        self.tipo = tipo
        self.mesa = mesa
        self.lugar = lugar

    def servir(self):
        self.tipo.mostrar(self.mesa, self.lugar)


# --- Uso ---
tipo_pizza = FabricaComida.obtener_tipo("Pizza", "pizza.png", "croc.mp3")
tipo_burger = FabricaComida.obtener_tipo("Hamburguesa", "burger.png", "crunch.mp3")

ordenes = [
    Plato(tipo_pizza, 1, "A"),
    Plato(tipo_pizza, 1, "B"),
    Plato(tipo_burger, 2, "C"),
    Plato(tipo_burger, 2, "D"),
]

for plato in ordenes:
    plato.servir()
