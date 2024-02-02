import java.util.Scanner;

public class CurrencyConverter_4 {

    public static void main(String[] args) {
        String[] availableCurrencies = {"EURO", "USD", "TL"};
        double[] currencyRates = {1, 0.92, 0.03};

        if (args.length == 3) {
            currencyRates[0] = Double.parseDouble(args[0]);
            currencyRates[1] = Double.parseDouble(args[1]);
            currencyRates[2] = Double.parseDouble(args[2]);
        }

        displayWelcomeMessage(availableCurrencies);

        Scanner scanner = new Scanner(System.in);
        String currencyFrom, currencyTo;

        do {
            currencyFrom = readCurrency("Выберите исходную валюту: ", availableCurrencies);

            double x = getRateValue(currencyFrom, currencyRates);

            System.out.print("Выберите конечную валюту: ");
            currencyTo = scanner.next();

            double y = getRateValue(currencyTo, currencyRates);

            System.out.print("Введите сумму в изначальной валюте: ");
            double initSum = scanner.nextDouble();

            double finalSum = calculateFinalSum(initSum, x, y);

            System.out.println("Вы получите: " + finalSum);
        } while (!currencyFrom.equalsIgnoreCase("exit"));
    }

    public static void displayWelcomeMessage(String[] currencies) {
        System.out.println("Приветствуем в CurrencyConverter!\nВыберите исходную валюту:");
        for (String currency : currencies) {
            System.out.println("* " + currency);
        }
        System.out.println("Или введите exit, чтобы завершить программу");
    }

    public static double getRateValue(String currency, double[] rates) {
        switch (currency.toUpperCase()) {
            case "EURO":
                return rates[0];
            case "USD":
                return rates[1];
            case "TL":
                return rates[2];
            default:
                throw new IllegalArgumentException("Некорректная валюта: " + currency);
        }
    }

    public static String readCurrency(String prompt, String[] validCurrencies) {
        Scanner scanner = new Scanner(System.in);
        String inputCurrency;

        do {
            System.out.print(prompt);
            inputCurrency = scanner.nextLine();

            if (inputCurrency.equalsIgnoreCase("exit")) {
                System.out.println("До свидания!");
                System.exit(0);
            } else if (!isValidCurrency(inputCurrency, validCurrencies)) {
                System.out.println("Некорректное значение, попробуйте еще раз!");
            }
        } while (!isValidCurrency(inputCurrency, validCurrencies));

        return inputCurrency.toUpperCase();
    }

    public static boolean isValidCurrency(String currency, String[] validCurrencies) {
        for (String validCurrency : validCurrencies) {
            if (currency.equalsIgnoreCase(validCurrency)) {
                return true;
            }
        }
        return false;
    }

    public static double calculateFinalSum(double initSum, double x, double y) {
        int roundedTotal = (int) ((initSum * x / y) * 100);
        return (double) roundedTotal / 100;
    }
}