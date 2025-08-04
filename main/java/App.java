import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static int lastId = 0;
    static Scanner sc = new Scanner(System.in);
    static List<WiseSaying> wiseSayings = new ArrayList<>();

    public static void run() {
        while(true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            String command = sc.nextLine();

            processCommand(command);
        }
    }

    public static void processCommand(String command) {
        if (command.equals("종료")) {
            System.out.println("앱을 종료합니다.");
            System.exit(0);
        } else if (command.equals("등록")) {
            addWiseSaying();
        } else if(command.equals("목록")) {
            printWiseSayings();
        } else if(command.startsWith("삭제?id=")) {
            deleteWiseSaying(parseId(command));
        } else if(command.startsWith("수정?id=")) {
            updateWiseSaying(parseId(command));
        }
    }

    public static void addWiseSaying() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        lastId++;

        WiseSaying wiseSaying = new WiseSaying(lastId, content, author);
        wiseSayings.add(wiseSaying);
        System.out.printf("%d번 명언이 등록되었습니다.\n", lastId);
    }

    public static void printWiseSayings() {
        if (wiseSayings.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        } else {
            System.out.println("번호 / 작가 / 명언");
            System.out.println("-------------------------");

            //명언 목록 출력
            for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                WiseSaying wiseSaying = wiseSayings.get(i);
                System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
            }
        }
    }

    public static void deleteWiseSaying(int id) {
        WiseSaying wiseSaying = findById(id);
        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        wiseSayings.remove(wiseSaying);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public static void updateWiseSaying(int id) {
        WiseSaying wiseSaying = findById(id);
        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("명언 (기존) : " + wiseSaying.content);
        System.out.print("명언 : ");
        wiseSaying.content = sc.nextLine();

        System.out.println("작가 (기존) : " + wiseSaying.author);
        System.out.print("작가 : ");
        wiseSaying.author = sc.nextLine();

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    public static WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == id) {
                return wiseSaying;
            }
        }
        return null;
    }

    public static int parseId(String command) {
        return Integer.parseInt(command.split("=")[1]);
    }
}



