import java.util.Scanner;

public class Othello {
    public static void main(String[] args) {
        Board.initialize();
        Board.showBoard();

        // コンソールからの入力を受け付ける
        Scanner s = new Scanner(System.in);

        // ゲーム実行中フラグがtrueの間ループする
        while(Board.game) {
            System.out.print("コマを置くx座標を入力してください:");
            int x = s.nextInt();

            System.out.print("コマを置くy座標を入力してください:");
            int y = s.nextInt();

            Board.setStone(x, y);
        }

        s.close();
    }
}
