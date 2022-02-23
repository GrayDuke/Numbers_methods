package first;

public class Main {
    public static void main(String[] args) {
        Method t1 = new Method(2,3,0.001);
        t1.decide();
        t1.get_answer();

    }
}

class Method{
    double first_n1, second_n1, first_n2, second_n2, c, accuracy, new_accuracy;
    int number;
    String limit;

    public Method(double first_n, double second_n, double accuracy) {
        this.first_n1 = first_n;
        this.second_n1 = second_n;
        this.accuracy = accuracy;
        c = (first_n1+second_n1)/2;
        new_accuracy = accuracy+1;
        limit = "["+first_n1 + "; " + second_n1 + "]";
    }

    public double equations(double num){
        return Math.pow(num, 3) - (4*num) - 2;
    }

    public void get_answer() {
        System.out.println("Ответ: x=" + c);
    }


    public void help(){
        c = (first_n1+second_n1)/2;
        if((equations(first_n1) * equations(c))<0){
            first_n2 = c*1;
            second_n2 = second_n1*1;
            second_n1 = c*1;
            new_accuracy = second_n1 - first_n1;
        }else if((equations(c) * equations(second_n1)) <0){
            first_n2 = first_n1*1;
            second_n2 = c*1;
            first_n1 = c*1;
            new_accuracy = second_n1 - first_n1;
        }

    }

    public void decide(){
        while(new_accuracy>accuracy){
            help();
            number++;
            System.out.println(number +") " + limit +"; c"+number+" = "+c);
            System.out.print("Верный: ");
            System.out.print("["+first_n1 + "; " + second_n1 + "]; ");
            System.out.print("f("+first_n1+")="+equations(first_n1)+"; ");
            System.out.println("f("+second_n1+")="+equations(second_n1)+"; ");
            if (new_accuracy > accuracy) System.out.println("Погрешность= " + new_accuracy + " > "+accuracy+ " - точность не достигнута");
            else System.out.println("Погрешность= " + new_accuracy + " < "+accuracy + " - точность достигнута");
            System.out.print("Неверный: ");
            System.out.print("["+first_n2 + "; " + second_n2 + "]; ");
            System.out.print("f("+first_n2+")="+equations(first_n2)+"; ");
            System.out.println("f("+second_n2+")="+equations(second_n2)+"; ");
            System.out.println();
            limit = "["+first_n1 + "; " + second_n1 + "]";
        }

    }
}
