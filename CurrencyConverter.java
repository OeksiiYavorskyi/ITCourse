import java.util.Scanner;

public class CurrencyConverter {
    private double[] currencyRates;
    private String[] availableCurrencies;

    public CurrencyConverter() {
        this.availableCurrencies = new String[]{"EURO", "USD", "TL"};
        this.currencyRates = new double[]{1, 0.92, 0.03};
    }

    public CurrencyConverter(double[] currencyRates) {
        this.availableCurrencies = new String[]{"EURO", "USD", "TL"};
        this.currencyRates = currencyRates;
    }

    public CurrencyConverter(double[] currencyRates, String[] availableCurrencies) {
        this.availableCurrencies = availableCurrencies;
        this.currencyRates = currencyRates;
    }

    public void startConversion() {
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String currencyFrom, currencyTo;

        do {
            currencyFrom = readCurrency("Выберите исходную валюту: ");

            double x = getRateValue(currencyFrom);

            System.out.print("Выберите конечную валюту: ");
            currencyTo = scanner.next();

            double y = getRateValue(currencyTo);

            System.out.print("Введите сумму в изначальной валюте: ");
            double initSum = scanner.nextDouble();

            double finalSum = calculateFinalSum(initSum, x, y);

            System.out.println("Вы получите: " + finalSum);
        } while (!currencyFrom.equalsIgnoreCase("exit"));
    }

    private void displayWelcomeMessage() {
        System.out.println("Приветствуем в CurrencyConverter!\nВыберите исходную валюту:");
        for (String currency : availableCurrencies) {
            System.out.println("* " + currency);
        }
        System.out.println("Или введите exit, чтобы завершить программу");
    }

    private double getRateValue(String currency) {
        switch (currency.toUpperCase()) {
            case "EURO":
                return currencyRates[0];
            case "USD":
                return currencyRates[1];
            case "TL":
                return currencyRates[2];
            default:
                throw new IllegalArgumentException("Некорректная валюта: " + currency);
        }
    }

    private String readCurrency(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String inputCurrency;

        do {
            System.out.print(prompt);
            inputCurrency = scanner.nextLine();

            if (inputCurrency.equalsIgnoreCase("exit")) {
                System.out.println("До свидания!");
                System.exit(0);
            } else if (!isValidCurrency(inputCurrency)) {
                System.out.println("Некорректное значение, попробуйте еще раз!");
            }
        } while (!isValidCurrency(inputCurrency));

        return inputCurrency.toUpperCase();
    }

    private boolean isValidCurrency(String currency) {
        for (String validCurrency : availableCurrencies) {
            if (currency.equalsIgnoreCase(validCurrency)) {
                return true;
            }
        }
        return false;
    }

    private double calculateFinalSum(double initSum, double x, double y) {
        int roundedTotal = (int) ((initSum * x / y) * 100);
        return (double) roundedTotal / 100;
    }
}
