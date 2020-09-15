package converter;

import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        convertFraction();
    }


    public static void convertAnyBase() {

        String sourceRadix = input.next();
        String sourceNumber = input.next();
        String destRadix = input.next();
        StringBuilder convertToDestRadix;
        int decimal = 0;


        if (Integer.parseInt(sourceRadix) == 1) {
            decimal = sourceNumber.length();
            convertToDestRadix = new StringBuilder(Integer.toString(decimal, Integer.parseInt(destRadix)));


        } else if (Integer.parseInt(destRadix) == 1) {

            decimal = Integer.parseInt(sourceNumber, Integer.parseInt(sourceRadix));
            convertToDestRadix = new StringBuilder();

            for (int i = 0; i < decimal; i++) {
                convertToDestRadix.append(1);
            }


        } else {


            convertToDestRadix = new StringBuilder(Integer.toString(decimal, Integer.parseInt(destRadix)));


        }

        System.out.println(convertToDestRadix);


    }

    public static void convertFraction() {

        String sourceRadix = input.next();
        double nAvogadro = 6.022e23;



        if (!sourceRadix.matches("\\d+") || Double.parseDouble(sourceRadix) <= 0.0) {
            System.out.println("error");
            System.exit(0);
        }


        String destRadix = input.next();

        if(destRadix.matches("(\\d+(?:\\.\\d+)?)") || Integer.parseInt(sourceRadix) >= 16 ){
            String sourceNumber = input.next();
            input.nextLine();
            String temp1 = destRadix;
            destRadix = sourceNumber;
            sourceNumber = temp1;

            System.out.println("sr " + sourceRadix);
            System.out.println("numbeer " + sourceNumber);
            System.out.println("dr = " + destRadix);


            if ( Integer.parseInt(sourceRadix) <= 0 ||Integer.parseInt(sourceRadix)>36 ) {

                System.out.println("error");

                System.exit(0);

            }
            else if((!destRadix.matches("(\\d+(?:\\.\\d+)?)"))){
                System.out.println("error");

                System.exit(0);

            }else if (Integer.parseInt(destRadix) > 36 || Integer.parseInt(destRadix) <= 0) {
                System.out.println("error");
                System.exit(0);
            }  else {


                int total = 0;
                int radix = Integer.parseInt(sourceRadix);
                double decimal;
                double avoidingDecimalError = 0;

                StringBuilder convertToDestRadix = new StringBuilder("");


                //converting source number to decimal

                int decimalPoint = 0;
                boolean flag = false;
                StringBuilder fraction1 = new StringBuilder();
                StringBuilder wholeNumbers = new StringBuilder();
                int finalDecimal;
                double fractionDecimal = 0;


                //checking decimal point position

                if (Integer.parseInt(sourceRadix) == 1) {


                    finalDecimal = sourceNumber.length();


                } else {
                    for (int i = 0; i < sourceNumber.length(); i++) {
                        if (sourceNumber.charAt(i) == '.') {

                            flag = true;
                            continue;

                        }
                        if (flag) {

                            decimalPoint++;
                            fraction1.append(sourceNumber.charAt(i));
                        } else {

                            wholeNumbers.append(sourceNumber.charAt(i));
                        }
                    }


                    finalDecimal = Integer.parseInt(String.valueOf(wholeNumbers), radix);

                    if (String.valueOf(fraction1).isBlank()) {
                        fractionDecimal = 0;
                    } else {
                        double total2 = 0;


                        for (int i = 0; i < decimalPoint; i++) {
                            String check = "" + fraction1.charAt(i);
                            double check1 = Integer.parseInt(check, radix);


                            double decimal2 = check1 / (Math.pow(radix, (i + 1)));


                            total2 += decimal2;

                        }


                        fractionDecimal = total2;
                    }


                }


                //converting fraction to int
                int number = finalDecimal;


                //getting the fraction part
                String fraction = "" + fractionDecimal;
                int combineLength = wholeNumbers.length() + fraction1.length();


                //converting dumber to decimal first and appending

                String dest = Long.toString(number, Integer.parseInt(destRadix));


                if (decimalPoint == 0) {

                    convertToDestRadix.append(dest);
                } else {
                    convertToDestRadix.append(dest + ".");
                }

                //converting fraction part to destination radix

                double fractionHold = Double.parseDouble(fraction);


                if (!fraction.equals("0.0")) {
                    int destRad = Integer.parseInt(destRadix);
                    for (int i = 0; i < 5; i++) {
                        double temp = destRad * fractionHold;


                        fractionHold = temp % 1;

                        int tempo = (int) temp;

                        String hello = Long.toString(tempo, destRad);

                        convertToDestRadix.append(hello);

                    }

                }


                // if destination radix is 1
                if (Integer.parseInt(destRadix) == 1) {
                    convertToDestRadix.delete(0, convertToDestRadix.length());


                    convertToDestRadix.append("1".repeat(Math.max(0, finalDecimal)));

                }

                System.out.println(convertToDestRadix);
            }
        }
        else{
            System.out.println("errorlast");
        }






    }


}
