import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");


        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if(cmd.equals("종료")) {
                break;
            }
        }
    }
}
