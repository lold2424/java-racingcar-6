package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static String[] carNameArr, winner;
    public static int[] carCount;

    public static void main(String[] args) {

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        carNameArr = carInput();
        System.out.println("시도할 회수는 몇회인가요?");
        int tryCount = tryInput();

        carCount = new int[carNameArr.length];

        for (int i = 0; i < tryCount; i++) {
            tryResult(carNameArr, carCount);
            System.out.println();
        }

        finalResult();
    }

    public static String[] carInput() {
        String input = Console.readLine();
        String[] list = input.split(",");
        return list;
    }

    public static int tryInput() {
        String input = Console.readLine();
        int tryCount = Integer.parseInt(input);
        return tryCount;
    }

    public static int randomTry() {
        int rand = Randoms.pickNumberInRange(1, 9);
        return rand;
    }

    public static void tryResult(String[] carNameArr, int[] tryCount) {
        for (int i = 0; i < carNameArr.length; i++) {
            int rand = randomTry();
            carCount[i] = move(rand, carCount[i]);
            tryOutput(carNameArr[i], tryCount[i]);
        }
    }

    public static int move(int rand, int carCount) {
        if (rand >= 4) {
            carCount++;
        }
        return carCount;
    }

    public static void tryOutput(String carName, int carCount) {
        System.out.printf("%s : ", carName);
        for (int i = 0; i < carCount; i++) {
            System.out.printf("-");
        }
        System.out.println();
    }

    public static void finalResult() {
        int maxCount = 0;
        List<String> winners = new ArrayList<>();

        for (int i = 0; i < carCount.length; i++) {
            maxCount = maxResultCompare(i, maxCount);
        }

        // 최종 우승자(들) 찾기
        for (int i = 0; i < carCount.length; i++) {
            if (carCount[i] == maxCount) {
                winners.add(carNameArr[i]);
            }
        }

        // 우승자 출력
        System.out.print("최종 우승자: ");
        System.out.println(String.join(", ", winners));
    }

    public static int maxResultCompare(int index, int maxCount) {
        if (carCount[index] > maxCount) {
            maxCount = carCount[index];
        }
        return maxCount;
    }

}
