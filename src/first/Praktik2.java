package first;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Praktik2 {
    public static void main(String[] args) {
        DoubleMethod doubleMethod1 = new DoubleMethod(1,2,0.001);
        doubleMethod1.run();

    }
}
class DoubleMethod{
    double accuracy, new_accuracy,difference_accuracy,x0;
    List<Double> nums = new ArrayList<>();
    int number;

    public DoubleMethod(double first_n, double second_n, double accuracy) {
        nums.add(first_n);
        nums.add(second_n);
        this.accuracy = accuracy;
        difference_accuracy = accuracy+1;
    }

    public double func(double num){
        return round(Math.pow(num,3) + Math.pow(num, 2)-11, 4);
    }
    public double func1(double num){
        return round(3 * Math.pow(num,2) + 2*num,4);
    }
    public double func2(double num){
        return round(6*num + 2,4);
    }

    public void run(){
        while(difference_accuracy>accuracy) {
            number++;
            System.out.println(number + ".1 Отрезок изоляции корня имеет вид\n\t[" + getNum1() + "; " + getNum2() + "]");

            System.out.println(number + ".2 Проверим выполнение условия f(a) * f(b) < 0\n\t" + "f(" + getNum1() + ") = "
                    + func(getNum1()));
            System.out.println("\tf(" + getNum2() + ") = " + func(getNum2()));

            System.out.println(number + ".4 Проверим постоянство знака производных на отрезке\n\t" + "f'(" + getNum1() + ") = "
                    + func1(getNum1()));
            System.out.println("\t" + "f'(" + getNum2() + ") = " + func1(getNum2()));
            System.out.println("\t" + "f''(" + getNum1() + ") = " + func2(getNum1()));
            System.out.println("\t" + "f''(" + getNum2() + ") = " + func2(getNum2()));

            System.out.print(number + ".5 Проверим условие f(x0) * f''(x0)>0\n\t" + "f(" + getNum1() + ") * f''(" + getNum1() + ""
                    + ") = " + (func(getNum1()) * func2(getNum1())));
            if (func(getNum1()) * func2(getNum1()) > 0) System.out.println(" (Подходит)");
            else System.out.println(" (Не подходит)");
            System.out.print("\t" + "f(" + getNum2() + ") * f''(" + getNum2() + ") = " + (func(getNum2()) * func2(getNum2())));
            if (func(getNum2()) * func2(getNum2()) > 0) System.out.println(" (Подходит)");
            else System.out.println(" (Не подходит)");
            System.out.println("\tXo = " + check_Xo());


            System.out.print(number + ".6 Найдем приближение корней\n");
            System.out.println("\tа) X" + number + "1 = " + methodNewton(check_Xo()));
            System.out.println("\tб) X" + number + "2 = " + methodHord());
            addNums();

            System.out.print(number + ".7 Приближение корня\n");
            System.out.println("\tE" + number + " = " + new_accuracy);


            System.out.print(number + ".8 Проверим условие |E - x|\n");
            if (difference_accuracy > accuracy) {
                System.out.println("\t" + difference_accuracy + " > " + accuracy);
                System.out.println("\tУсловие не выполнено, значит продолжает вычисление");
            } else{
                System.out.println("\t" + difference_accuracy + " < " + accuracy);
                System.out.println("\tУсловие выполнено");
            }
            System.out.println();
        }
    }

    public double getNum1(){
        return nums.get(nums.size()-2);
    }
    public double getNum2(){
        return nums.get(nums.size()-1);
    }

    public double methodNewton(double num){
        return num - (func(num)/func1(num));
    }
    public double methodHord(){
        double a = getNum1();
        double b = getNum2();
        return a - ( ( (b-a)*func(a) ) / (func(b)-func(a) ) );
    }

    public double check_Xo(){
        double a = getNum1();
        double b = getNum2();
        double answer = 0;
        if(func(a)* func2(a) >0 ) answer =  a;
        else if (func(b)* func2(b) >0 )answer = b;
        return answer;
    }

    public void addNums(){
        double num1 = methodNewton(check_Xo());
        double num2 = methodHord();
        if(num1 < num2){
            nums.add(round(num1,4));
            nums.add(round(num2,4));
        }else{
            nums.add(round(num2,4));
            nums.add(round(num1,4));
        }
        new_accuracy = (num1+num2)/2;
        difference_accuracy = round(Math.abs(new_accuracy-num1),5);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
