class CPU:
    def encender(self): print("CPU: encendida")
    def apagar(self):   print("CPU: apagada")

class RAM:
    def cargar_kernel(self): print("RAM: kernel cargado")
    def liberar(self):       print("RAM: memoria liberada")

class Disco:
    def leer_bootloader(self): print("Disco: bootloader leído")
    def guardar_estado(self):  print("Disco: estado guardado en disco")

class GPU:
    def activar(self, modo="normal"): print(f"GPU: activada (modo {modo})")
    def desactivar(self):             print("GPU: desactivada")

class SistemaOperativo:
    def iniciar(self):  print("SO: iniciado")
    def apagar(self):   print("SO: apagado")
    def modo_juego(self): print("SO: modo juego habilitado")
    def modo_ahorro(self): print("SO: modo ahorro habilitado")

# ------- Fachada -------
class Computador:
    def __init__(self):
        self.cpu = CPU()
        self.ram = RAM()
        self.disco = Disco()
        self.gpu = GPU()
        self.so  = SistemaOperativo()

    def encender(self):
        print(">> Encendiendo PC...")
        self.cpu.encender()
        self.disco.leer_bootloader()
        self.ram.cargar_kernel()
        self.so.iniciar()
        self.gpu.activar("normal")
        print(">> PC lista.\n")

    def apagar(self):
        print(">> Apagando PC...")
        self.so.apagar()
        self.disco.guardar_estado()
        self.gpu.desactivar()
        self.ram.liberar()
        self.cpu.apagar()
        print(">> PC apagada.\n")

    def modo_juego(self):
        print(">> Activando modo JUEGO 🎮")
        self.so.modo_juego()
        self.gpu.activar("alto rendimiento")
        print(">> FPS felices.\n")

    def modo_ahorro(self):
        print(">> Activando modo AHORRO 🔋")
        self.so.modo_ahorro()
        self.gpu.activar("eco")
        print(">> Batería agradecida.\n")

# ------- Uso -------
pc = Computador()
pc.encender()
pc.modo_juego()
pc.modo_ahorro()
pc.apagar()
