import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split(" ");

        for (String n: numbers) {
            if (!" ".equals(n) && !"".equals(n)) {
                System.out.println(n);
            }
        }

        /* Official solution
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextInt());
        }
         */
    }
}