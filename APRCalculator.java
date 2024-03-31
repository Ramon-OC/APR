import java.util.Scanner;

public class APRCalculator{

  public static double calculateAPR(double amountFinanced, double periodicPayment, int noPayments, double ppy, double disclosed, double oddDays){
    double percentageAPR = disclosed;
    double tempguess = disclosed;       

    do{
        percentageAPR = tempguess;

        double rate01 = tempguess/(100*ppy);
        double A1 = generalEquation(noPayments, periodicPayment, oddDays, rate01);

        double rate02 = (tempguess + 0.1)/(100*ppy);
        double A2 = generalEquation(noPayments, periodicPayment, oddDays, rate02);

        tempguess = tempguess + 0.1*(amountFinanced - A1)/(A2 - A1);        
    }while(Math.abs(percentageAPR*10000 - tempguess*10000) > 1);

    return percentageAPR; 
  }

  public static double generalEquation(int period, double periodicPayment, double oddDays, double rate){
    double retval = 0;
    for (int x = 0; x < period; x++){
      retval += periodicPayment / ((1.0 + oddDays*rate)*Math.pow(1+rate,1 + x));
    }
    return retval;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Amount Financed: ");
    double amount = scanner.nextDouble();
    System.out.print("Periodic payment: ");
    double periodicPayment = scanner.nextDouble();
    System.out.print("Total no of payments: ");
    int noPayments = scanner.nextInt();
    System.out.print("No. of payment periods per year: ");
    double ppy = scanner.nextDouble();
    System.out.print("Disclosed (or estimated) APR: ");
    double disclosed = scanner.nextDouble()/100;
    System.out.print("Odd days: ");
    double oddDays = scanner.nextDouble()/30;

    double apr = calculateAPR(amount,periodicPayment,noPayments,ppy,disclosed,oddDays);
    System.out.println("Annual Percentage Rate: "+apr+"%");
  }

}