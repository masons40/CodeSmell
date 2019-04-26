package nosejob;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Interface to include the methods that convert given temperature values and also the formatter
 */

public interface HeatScalable {
    NumberFormat formatter = new DecimalFormat("#0.00");

    double toCelsius();
    double toFahrenheit();
    double toKelvin();
    double getTemperature();
}
