import java.util.Scanner;

import org.fusesource.jansi.Ansi;

public class Main {
    static Ansi[] slots = {colorText("♣", Ansi.Color.DEFAULT), colorText("$", Ansi.Color.GREEN), colorText("♥", Ansi.Color.MAGENTA), colorText("☻", Ansi.Color.YELLOW), colorText("☼", Ansi.Color.YELLOW), colorText("♦", Ansi.Color.RED)};
    static int wins = 0;
    static int balance = 30;
    static boolean luckyMode = false;
    static Ansi pipe = colorText(" | ", Ansi.Color.DEFAULT);

    public static void main(String[] args) throws Exception {
        clearScreen();
        System.out.println(colorText("""
                    _____              __  __   ____    _        _____   _   _    _____
                 $ / ____|     /\\     |  \\/  | |  _ \\  | |      |_   _| | \\ | |  / ____|
                 $| |  __     /  \\    | \\  / | | |_) | | |        | |   |  \\| | | |  __
                 $| | |_ |   / /\\ \\   | |\\/| | |  _ <  | |        | |   | . ` | | | |_ |
                 $| |__| |  / ____ \\  | |  | | | |_) | | |____   _| |_  | |\\  | | |__| |
                 $ \\_____| /_/    \\_\\ |_|  |_| |____/  |______| |_____| |_| \\_|  \\_____|
                 $ \s
                 $ \s
                 $  _____   _____   __  __   _    _   _                   _______    ____    _____    _\s
                 $ / ____| |_   _| |  \\/  | | |  | | | |          /\\     |__   __|  / __ \\  |  __ \\  | |
                 $| (___     | |   | \\  / | | |  | | | |         /  \\       | |    | |  | | | |__) | | |
                 $ \\___ \\    | |   | |\\/| | | |  | | | |        / /\\ \\      | |    | |  | | |  _  /  | |
                 $ ____) |  _| |_  | |  | | | |__| | | |____   / ____ \\     | |    | |__| | | | \\ \\  |_|
                 $|_____/  |_____| |_|  |_|  \\____/  |______| /_/    \\_\\    |_|     \\____/  |_|  \\_\\ (_)\
                """, Ansi.Color.GREEN));
        Thread.sleep(2000);
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (balance >= 0){
                System.out.println("\n\nYOUR BALANCE IS " + colorText("$" + balance, Ansi.Color.GREEN));
            } else {
                System.out.println("\nYOU OWE " + colorText("$" + Math.abs(balance) + " ♥", Ansi.Color.RED));
            }
            System.out.println("\n" + colorText("press enter to spin!", Ansi.Color.CYAN) + " (or type " + colorText("exit", Ansi.Color.RED) + " to quit (" + colorText("loser", Ansi.Color.RED) + "))");
            String inp = sc.nextLine();
            if (inp.equalsIgnoreCase("exit")) {
                break;
            } else if (inp.equalsIgnoreCase("bitch let me win") &&  !luckyMode){
                System.out.println("okay fine... " + colorText("dont tell anyone...", Ansi.Color.RED));
                luckyMode = true;
                Thread.sleep(700);
            }
            spin();
            Thread.sleep(1700);
        }
        sc.close();
        System.out.println("\nBYE YOU " + colorText("LOSER!\n", Ansi.Color.RED));
    }

    public static void spin() throws InterruptedException{
        Ansi firstRow = newSlotRow();
        Ansi secondRow = newSlotRow();
        Ansi thirdRow = newSlotRow();
        for (int i = 0; i < (int)(Math.random() * 50); i++){
            clearScreen();

            System.out.println("----------");
            System.out.println(firstRow);
            System.out.println(secondRow + "  ◄");
            System.out.println(thirdRow);
            System.out.println("----------");

            Thread.sleep(350);

            firstRow = secondRow;
            secondRow = thirdRow;
            thirdRow = newSlotRow();
        }
        String[] separated;
        if (wins < 2) {
            separated = stripAnsi(countdown(firstRow, secondRow, thirdRow, false)).split(" ");
        } else {
            separated = stripAnsi(countdown(firstRow, secondRow, thirdRow, true)).split(" ");
        }
        Thread.sleep(360);
        if (separated[0].equals(separated[2]) && separated[2].equals(separated[4])){
            int win = ((int)(Math.random() * 20) + 30);
            System.out.println(colorText("\nYOU WON!!!!!! +$" + win, Ansi.Color.GREEN));
            wins++;
            balance += win;
            if (wins == 2 && !luckyMode){
                System.out.println(colorText("don't get used to it <3", Ansi.Color.RED));
            }
        } else {
            int loss = ((int)(Math.random() * 6) + 2);
            System.out.println(colorText("\nyou lost... sorry!! -$" + loss, Ansi.Color.RED));
            balance -= loss;
        }
    }

    public static Ansi countdown(Ansi first, Ansi second, Ansi third, Boolean fake) throws InterruptedException {
        for (int i = 0; i < 4; i++){
            clearScreen();
            if (i == 0){
                System.out.println("THREE...\n");
            } else if (i == 1){
                System.out.println("TWO...\n");
            } else if (i == 2){
                System.out.println("ONE!!\n");
            }

            System.out.println("----------");
            System.out.println(third);
            System.out.println(second + "  ◄");
            System.out.println(first);
            System.out.println("----------");

            Thread.sleep(450);

            third = second;
            second = first;
            if (luckyMode && i == 1){
                first = newLuckyRow();
            } else if (i == 1 && fake == true){
                first = newFakeRow();
            } else {
                first = newSlotRow();
            }
        }
        return third;
    }

    public static Ansi newSlotRow(){
        Ansi sym1 = slots[(int) (Math.random() * slots.length)];
        Ansi sym2 = slots[(int) (Math.random() * slots.length)];
        Ansi sym3 = slots[(int) (Math.random() * slots.length)];
        return Ansi.ansi().a(sym1).a(pipe).a(sym2).a(pipe).a(sym3);
    }

    public static Ansi newFakeRow(){
        Ansi fake = newSlotRow();
        String[] separated = stripAnsi(fake).split(" ");
        while (separated[0].equals(separated[2]) && separated[2].equals(separated[4])){
            fake = newSlotRow();
            separated = stripAnsi(fake).split(" ");
        }
        return fake;
    }

    public static Ansi newLuckyRow(){
        Ansi slot = slots[(int) (Math.random() * slots.length)];
        return Ansi.ansi().a(slot).a(pipe).a(slot).a(pipe).a(slot);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static Ansi colorText(String str, Ansi.Color color){
         return Ansi.ansi().fg(color).a(str).reset();
    }

    public static Ansi colorText(String str, Ansi.Color color, Ansi.Color bg){
        return Ansi.ansi().fg(color).bg(bg).a(str).reset();
    }

    public static String stripAnsi(Ansi input) {
        return input.toString().replaceAll("\u001B\\[[;?0-9]*[ -/]*[@-~]", "");
    }
}