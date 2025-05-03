import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversor conversor = new Conversor();

        while (true) {
            System.out.println("\nEscolha a conversão desejada:");
            System.out.println("1 - USD para BRL");
            System.out.println("2 - USD para EUR");
            System.out.println("3 - USD para ARS");
            System.out.println("4 - BRL para USD");
            System.out.println("5 - EUR para USD");
            System.out.println("6 - BRL para EUR");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.println("Encerrando...");
                break;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            conversor.realizarConversao(opcao, valor);
        }

        scanner.close();
    }
}
