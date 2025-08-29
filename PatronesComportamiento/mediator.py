from abc import ABC, abstractmethod

# Mediador abstracto
class TorreControl(ABC):
    @abstractmethod
    def enviar(self, mensaje, avion):
        pass

# Mediador concreto
class TorreAeropuerto(TorreControl):
    def __init__(self):
        self.aviones = []

    def registrar(self, avion):
        self.aviones.append(avion)

    def enviar(self, mensaje, avion):
        for otro in self.aviones:
            if otro != avion:
                otro.recibir(mensaje)

# Colega
class Avion:
    def __init__(self, nombre, torre):
        self.nombre = nombre
        self.torre = torre
        self.torre.registrar(self)

    def enviar(self, mensaje):
        print(f"✈️ {self.nombre} transmite: {mensaje}")
        self.torre.enviar(mensaje, self)

    def recibir(self, mensaje):
        print(f"📡 {self.nombre} recibe: {mensaje}")

# --- Uso ---
torre = TorreAeropuerto()
avion1 = Avion("LATAM123", torre)
avion2 = Avion("Avianca456", torre)
avion3 = Avion("KLM789", torre)

avion1.enviar("Solicitando permiso para aterrizar.")
avion2.enviar("Rodando hacia la pista.")
avion3.enviar("Listo para despegar.")
