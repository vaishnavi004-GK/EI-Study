interface TemperatureAdapter {
    double getTemperatureInCelsius();
}


class LegacyTemperatureSystem {
    public double getTemperatureInFahrenheit() {
        return 100.0; 
    }
}


class TemperatureAdapterImpl implements TemperatureAdapter {
    private LegacyTemperatureSystem legacySystem;

    public TemperatureAdapterImpl(LegacyTemperatureSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public double getTemperatureInCelsius() {
        return (legacySystem.getTemperatureInFahrenheit() - 32) * 5 / 9;
    }
}


public class AdapterPatternDemo {
    public static void main(String[] args) {
        LegacyTemperatureSystem legacySystem = new LegacyTemperatureSystem();
        TemperatureAdapter adapter = new TemperatureAdapterImpl(legacySystem);

        System.out.println("Temperature in Celsius: " + adapter.getTemperatureInCelsius());
    }
}