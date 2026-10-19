import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    ArrayList<WiseSaying> wiseList = new ArrayList<>();
    int lastId = 0;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");

            String[] parts = sc.nextLine().trim().split("\\?");

            String cmd = parts[0];

            if (cmd.equals("종료")) {
                break;
            }
            switch (cmd) {
                case "등록":
                    actionRegister();
                    break;
                case "목록":
                    actionPrintList();
                    break;
                case "삭제":
                    int id = Integer.parseInt(parts[1].split("=")[1]);

                    actionDeleteList(id);
                    break;
                case "수정":
                    id = Integer.parseInt(parts[1].split("=")[1]);

                    actionEditList(id);
                    break;
            }
        }
    }

    void actionRegister() {
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying item = write(author, content);

        System.out.printf("%d번 명언이 등록되었습니다.\n", item.id);
    }

    void actionPrintList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        ArrayList<WiseSaying> printList = findAllLatestFirst();
        for (WiseSaying item : printList) {

            System.out.printf("%d / %s / %s\n", item.id, item.author, item.content);
        }
    }

    WiseSaying write(String author, String content) {
        int id = ++lastId;
        WiseSaying item = new WiseSaying(id, author, content);
        wiseList.add(item);

        return item;
    }

    ArrayList<WiseSaying> findAllLatestFirst() {
        ArrayList<WiseSaying> result = new ArrayList<>();

        for (int i = wiseList.size() - 1; i >= 0; i--) {
            result.add(wiseList.get(i));
        }

        return result;
    }

    void actionDeleteList(int id) {
        if(delete(id)) {
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        } else {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }

    }

    boolean delete(int id) {
        int idx = findIndexById(id);

        if(idx != -1) {
            wiseList.remove(idx);
            return true;
        }

        return false;
    }


    void actionEditList(int id) {
        int idx = findIndexById(id);

        if(idx == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        WiseSaying item = wiseList.get(idx);

        System.out.printf("명언(기존) : %s\n", item.content);
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.printf("작가(기존) : %s\n", item.author);
        System.out.print("작가 : ");
        String author = sc.nextLine();

        edit(idx, new WiseSaying(id, author, content));
    }

    void edit(int idx, WiseSaying item) {
        wiseList.set(idx, item);
    }

    int findIndexById(int id) {
        for(int i=0; i<wiseList.size(); i++) {
            if(wiseList.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }
}
