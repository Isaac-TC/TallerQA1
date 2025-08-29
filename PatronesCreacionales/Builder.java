package PatronesCreacionales;
import java.util.ArrayList;
import java.util.List;


class Part {
    private final String name;
    private final double price;

    public Part(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice() { return price; }
    @Override public String toString() {
        return String.format("%s: $%.2f", name, price);
    }
}


class AutoKit {
    private final List<Part> parts = new ArrayList<>();
    public void addPart(Part p) { parts.add(p); }
    public double getPrice() {
        return parts.stream().mapToDouble(Part::getPrice).sum();
    }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder("Kit de Auto:\n");
        for (Part p : parts) sb.append(p).append("\n");
        sb.append(String.format("Precio total: $%.2f", getPrice()));
        return sb.toString();
    }
}


class AutoKitBuilder {
    private final AutoKit kit = new AutoKit();

    public AutoKitBuilder addBrakePads() {
        kit.addPart(new Part("Pastillas de freno (juego)", 35));
        return this;
    }
    public AutoKitBuilder addOilFilter() {
        kit.addPart(new Part("Filtro de aceite", 8));
        return this;
    }
    public AutoKitBuilder addEngineOil() {
        kit.addPart(new Part("Aceite de motor 4L", 28));
        return this;
    }
    public AutoKitBuilder addSparkPlugs(int qty) {
        kit.addPart(new Part("Bujías x" + qty, 5 * qty));
        return this;
    }
    public AutoKitBuilder addWipers() {
        kit.addPart(new Part("Plumillas (par)", 12));
        return this;
    }
    public AutoKit getKit() { return kit; }
}


public class Builder {
    public static void main(String[] args) {
        AutoKit kit = new AutoKitBuilder()
                .addOilFilter()
                .addEngineOil()
                .addBrakePads()
                .addSparkPlugs(4)
                .addWipers()
                .getKit();

        System.out.println(kit);
    }
}
