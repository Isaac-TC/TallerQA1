class Part:
    def __init__(self, name, price):
        self.name = name
        self.price = float(price)

    def get_price(self):
        return self.price

    def __str__(self):
        return f"{self.name}: ${self.price:.2f}"


class AutoKit:
    def __init__(self):
        self.parts = []

    def add_part(self, part):
        self.parts.append(part)

    def get_price(self):
        return sum(p.get_price() for p in self.parts)

    def __str__(self):
        lines = ["Kit de Auto:"]
        lines += [str(p) for p in self.parts]
        lines += [f"Precio total: ${self.get_price():.2f}"]
        return "\n".join(lines)


class AutoKitBuilder:
    def __init__(self):
        self.kit = AutoKit()

    # Componentes comunes
    def add_brake_pads(self):
        self.kit.add_part(Part("Pastillas de freno (juego)", 35))
        return self

    def add_oil_filter(self):
        self.kit.add_part(Part("Filtro de aceite", 8))
        return self

    def add_engine_oil(self):
        self.kit.add_part(Part("Aceite de motor 4L", 28))
        return self

    def add_spark_plugs(self, qty=4):
        self.kit.add_part(Part(f"Bujías x{qty}", 5 * qty))
        return self

    def add_wipers(self):
        self.kit.add_part(Part("Plumillas (par)", 12))
        return self

    def get_kit(self):
        return self.kit


# --- Uso ---
builder = AutoKitBuilder()
kit = (builder
       .add_oil_filter()
       .add_engine_oil()
       .add_brake_pads()
       .add_spark_plugs(qty=4)
       .add_wipers()
       .get_kit())

print(kit)
