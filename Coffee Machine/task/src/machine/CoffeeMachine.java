package machine;
import java.util.Scanner;

public class CoffeeMachine {
    State state;
    int water;
    int milk;
    int beans;
    int cups;
    int money;

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (machine.state != State.EXIT) {
            machine.prompt();
            machine.operate(scanner.next());
        }
    }

    public CoffeeMachine () {
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
        state = State.IDLE;
    }

    public void operate(String input) {
        if (this.state == State.IDLE) {
            input = input.toUpperCase();

            try {
                State action = State.valueOf(input);
                switch(action) {
                    case FILL:
                        this.state = State.FILL_WATER;
                        break;
                    case REMAINING:
                        this.remaining();
                        break;
                    case TAKE:
                        this.take();
                        break;
                    default:
                        this.state = action;
                }
            } catch (Exception e) {
                System.out.println("No Such Option!");
            }
        } else {
            switch(this.state) {
                case BUY:
                    this.buy(input);
                    break;
                case FILL_WATER:
                    this.fill("water", Integer.parseInt(input));
                    this.state = State.FILL_MILK;
                    break;
                case FILL_MILK:
                    this.fill("milk", Integer.parseInt(input));
                    this.state = State.FILL_BEANS;
                    break;
                case FILL_BEANS:
                    this.fill("beans", Integer.parseInt(input));
                    this.state = State.FILL_CUPS;
                    break;
                case FILL_CUPS:
                    this.fill("cups", Integer.parseInt(input));
                    this.state = State.IDLE;
                    break;
            }
        }
    }

    public void prompt() {
        switch(this.state) {
            case IDLE:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case FILL_WATER:
                System.out.println("Write how many ml of water do you want to add:");
                break;
            case FILL_MILK:
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case FILL_BEANS:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case FILL_CUPS:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            default:
                System.out.println("No such action exists!");
        }
    }

    public void buy(String option) {
        if (!"back".equals(option)) {
            int coffeeOption = Integer.parseInt(option);
            int waterRequired = 0;
            int milkRequired = 0;
            int beansRequired = 0;
            int moneyGained = 0;

            if (coffeeOption == 1) { // espresso
                waterRequired = 250;
                beansRequired = 16;
                milkRequired = 0;
                moneyGained = 4;
            } else if (coffeeOption == 2) { // latte
                waterRequired = 350;
                milkRequired = 75;
                beansRequired = 20;
                moneyGained = 7;
            } else if (coffeeOption == 3) { // cappuccino
                waterRequired = 200;
                milkRequired = 100;
                beansRequired = 12;
                moneyGained = 6;
            }

            if (water < waterRequired) {
                System.out.println("Sorry, not enough water");
            } else if (milk < milkRequired) {
                System.out.println("Sorry, not enough milk");
            } else if (beans <beansRequired) {
                System.out.println("Sorry, not enough coffee beans");
            } else if (cups < 1) {
                System.out.println("Sorry, not enough disposable cups");
            } else {
                System.out.println("I have enough resources, making you a coffee!");
                water -= waterRequired;
                milk -= milkRequired;
                beans -= beansRequired;
                money += moneyGained;
                cups--;
            }
        }
        this.state = State.IDLE;
    }

    public void fill(String type, int amount) {
        switch (type) {
            case "water":
                water += amount;
                break;
            case "milk":
                milk += amount;
                break;
            case "beans":
                beans += amount;
                break;
            case "cups":
                cups += amount;
                break;
            default:
                break;
        }
    }

    public void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
        this.state = state.IDLE;
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
        this.state = State.IDLE;
    }
}

enum State {
    IDLE,
    BUY,
    REMAINING,
    TAKE,
    FILL,
    FILL_WATER,
    FILL_MILK,
    FILL_BEANS,
    FILL_CUPS,
    EXIT;
}
