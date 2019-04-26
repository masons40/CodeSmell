package nosejob;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public	interface HeatScalable {
	public	static NumberFormat	formatter=new DecimalFormat("#0.00");
	public	double toCelsius();
	public	double toFahrenheit();
	public	double toKelvin();
}
