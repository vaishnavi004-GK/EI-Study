import java.util.HashMap;
import java.util.Map;


interface Prototype extends Cloneable {
    Prototype clone();
}


class Circle implements Prototype {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public Prototype clone() {
        return new Circle(radius);
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }
}


class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();

    public void addPrototype(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    public Prototype getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}


public class PrototypePatternDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        PrototypeRegistry registry = new PrototypeRegistry();
        registry.addPrototype("Circle", circle);

        Circle clonedCircle = (Circle) registry.getPrototype("Circle");
        System.out.println(clonedCircle);
    }
}