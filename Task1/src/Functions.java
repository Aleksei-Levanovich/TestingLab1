public class Functions{

    private static final double CLOSEBORDER = 0.96;

    private static double doubleFact(double n){
        if (n <= 0) {
            return 1;
        }
        return (n-1) / n * doubleFact(n-2);
    }

    private static double seriesTeilor(double x, double eps){
        int n = 1;
        double result = x;
        double prevResult;
        do {
            prevResult = result;
            double fac = doubleFact(2 * n);
            result += fac * Math.pow(x, 2 * n + 1) / (2 * n + 1);
            n++;
        } while (Math.abs(result - prevResult) > eps);
        return result;
    }

    public static double arcsin(double x, double eps){
        if (Math.abs(x) > 1.0){
            return Double.NaN;
        }
        if (Math.abs(x) > CLOSEBORDER){
            double newX = Math.sqrt(1 - x*x);
            return Math.signum(x) * (Math.PI/2 - seriesTeilor(newX, eps));
        }
        else {
            return seriesTeilor(x, eps);
        }
    }

    public static void main(String[] args){
        //проверка, до какого числа можно использовать обычный ряд Тейлора. получили примерно 0.96. Далее - используем другой способ
        //вывод формулы
        //arcsin(x)+arccos(x)=pi/2
        //arccos(x)=arcsin(sqrt(1-xx)) - формулы тригонометрии
        //подставим второе в первое
        //arcsin(x)=pi/2-arcsin(sqrt(1-xx))
        //ряд Тейлора точнее высчитывает меньшие арксинусы, после 0.96 используем вторую формулу
        double DELTA = 1E-5;
        for (double i=0.96; i<1; i+=0.001){
            double resultAsin = Math.asin(i);
            double resultTeilor = seriesTeilor(i,1E-6);
            if (Math.abs(resultAsin-resultTeilor)>DELTA) {
                System.out.println(i);
                System.out.println(arcsin(i,1E-6)-seriesTeilor(i,1E-6));
                break;
            }
        }
    }
}