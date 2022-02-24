package first;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(i + 1.35176);
        }
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(list.size() - 2));
        System.out.println(Math.ceil(list.get(list.size() - 2)));
        System.out.println(round(list.get(0),4) + 10);
        double s = round(list.get(0),4) + 14;
        System.out.println(s);

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
