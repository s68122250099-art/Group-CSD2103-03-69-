class SalesSummary {
    double total;
    double average;
    double maximum;
}

public class Main {

    public static SalesSummary calculateSales(double[] sales) {
        SalesSummary summary = new SalesSummary();

        summary.maximum = sales[0];

        for (int i = 0; i < sales.length; i++) {
            summary.total += sales[i];

            if (sales[i] > summary.maximum) {
                summary.maximum = sales[i];
            }
        }

        summary.average = summary.total / sales.length;

        return summary;
    }

    public static void main(String[] args) {

        double[] sales = {
                1250.50,
                890.00,
                1575.25,
                2300.00,
                940.75
        };

        SalesSummary result = calculateSales(sales);

        System.out.println("Total Sales   : " + result.total);
        System.out.println("Average Sales : " + result.average);
        System.out.println("Maximum Sales : " + result.maximum);
    }
}