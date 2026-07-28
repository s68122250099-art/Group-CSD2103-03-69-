public class Main {

    static class SalesSummary {
        double total;
        double average;
        double maximum;

        SalesSummary(double total, double average, double maximum) {
            this.total = total;
            this.average = average;
            this.maximum = maximum;
        }

        // เพิ่ม toString เพื่อพิมพ์ผลลัพธ์ได้ง่ายขึ้น
        @Override
        public String toString() {
            return String.format(
                    "ยอดขายรวม: %.2f%nยอดขายเฉลี่ย: %.2f%nยอดขายสูงสุด: %.2f",
                    total, average, maximum);
        }
    }

    static SalesSummary calculateSales(double[] sales) {
        // ป้องกันกรณี array ว่าง (edge case ที่โค้ดเดิมยังไม่กันไว้)
        if (sales == null || sales.length == 0) {
            throw new IllegalArgumentException("อาร์เรย์ยอดขายต้องไม่ว่าง");
        }

        double total = 0;
        double maximum = sales[0];

        // วนลูปครั้งเดียว (single pass) ผ่านข้อมูลทั้งหมด
        for (double sale : sales) {
            total += sale;
            if (sale > maximum) {
                maximum = sale;
            }
        }

        double average = total / sales.length;

        return new SalesSummary(total, average, maximum);
    }

    public static void main(String[] args) {
        double[] sales = {
                1250.50, 890.00, 1575.25,
                2300.00, 940.75
        };

        SalesSummary result = calculateSales(sales);
        System.out.println(result);
    }
}