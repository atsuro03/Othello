public class Board {

    // ゲーム実行中フラグ
    static boolean game = true;

    // オセロ版に対応した多次元配列
    static String[][] board = new String[8][8];

    static final String EMPTY = " ";
    static final String BLACK = "●";
    static final String WHITE = "○";

    static String stone;
    static String rev_stone;

    static public void initialize() {

        // オセロ版の要素を全てクリアにする
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = EMPTY;
            }
        }

        // 初期状態の配置
        board[3][3] = BLACK;
        board[3][4] = WHITE;
        board[4][3] = WHITE;
        board[4][4] = BLACK;

        // 次に打つコマの色を指定
        stone = BLACK;
        rev_stone = WHITE;

        // ゲーム実行中フラグ
        game = true;
    }

    static public void showBoard() {

        // まだ空いている座標があるか
        boolean existempty = false;
        // 黒いコマの数、集計用
        int cnt_black = 0;
        // 白いコマの数、集計用
        int cnt_white = 0;


        // オセロ版を描写する
        int i = 0;
        System.out.println("|0 |1 |2 |3 |4 |5 |6 |7 |");
        System.out.println("------------------");
        for(String[] sarr : board) {
            System.out.print(i + " |");
            for(String s : sarr) {
                System.out.print(s);
                System.out.print("|");

                // 空いている座標があるか、各コマ数の集計
                if(s.equals(EMPTY)) {
                    existempty = true;
                } else if(s.equals(BLACK)) {
                    cnt_black++;
                } else if(s.equals(WHITE)) {
                    cnt_white++;
                }

            }
            System.out.println();
            System.out.println("--------------------");

            i++;
        }

        System.out.println(BLACK + ":" + cnt_black);
        System.out.println(WHITE + ":" + cnt_white);
        System.out.println("------------------");

        if(existempty) {
            System.out.println(stone + "のターンです");
        } else {
            System.out.println(stone + "ゲーム終了");
            game = false;
        }

    }

    static public void setStone(int x, int y) {

        // 版外の座標を指定した場合
        if(x > 7 || y > 7) {
            System.out.println("その位置にコマはおけません");
        }

        // コマを配置できる場合
        if(board[y][x].equals(EMPTY)) {
            board[y][x] = stone;

            // ひっくり返す処理
            turnStone(x, y);

            // 次打つコマの設定
            String next_rev_storn = stone;
            stone = rev_stone;
            rev_stone = next_rev_storn;

            // オセロ版の描写
            showBoard();

        } else {

            // すでにコマがある位置を指定した場合
            System.out.println("その位置にコマは置けません");
        }

    }

    static public void turnStone(int x, int y) {

        // ８方向のコマの配置を確認し、ひっくり返す
        turnLeftUp(x, y);
        turnUp(x, y);
        turnRightUp(x, y);
        turnLeft(x, y);
        turnRight(x, y);
        turnLeftDown(x, y);
        turnDown(x, y);
        turnRightDown(x, y);
    }

    static public void turnLeftUp(int x, int y) {
        if(y > 1 && x > 1) {

            // 隣のコマ
            String next = board[y - 1][x - 1];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらにその一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(x - i < 0 || y - i < 0 || board[y - i][x - i].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y - i][x - i].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y - t][x - t] = stone;
                        }
                        break;
                    }
                }
            }

        }
    }

    static public void turnUp(int x, int y) {
        if(y > 1) {

            // 隣のコマ
            String next = board[y - 1][x];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらにその一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(y - i < 0 || board[y - i][x].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y - i][x].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y - t][x] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }

    static public void turnRightUp(int x, int y) {
        if(y > 1 && x < 6) {

            // 隣のコマ
            String next = board[y - 1][x + 1];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらにその一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(x + i > 7 || y - i < 0 || board[y - i][x + i].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y - i][x + i].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y - t][x - t] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }

    static public void turnDown(int x, int y) {
        if(y < 6) {

            // 隣のコマ
            String next = board[y + 1][x];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらにその一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(y + i > 7 || board[y + 1][x].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y + 1][x].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y + t][x] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }

    static public void turnRight(int x, int y) {
        if(x < 6) {

            // 隣のコマ
            String next = board[y][x + 1];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらに一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(x + i < 7 || board[y][x + i].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y][x + i].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y][x + t] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }

    static public void turnLeftDown(int x, int y) {
        if(y < 6 && x > 1) {

            // 隣のコマ
            String next = board[y + 1][x - 1];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらにその一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(x - i < 0 || y + i > 7 || board[y + i][x - i].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y + i][x - i].equals(stone)) {
                        //自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y + t][x - t] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }

    static public void turnLeft(int x, int y) {
        if(x > 1) {

            // 隣のコマ
            String next = board[y][x - 1];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらに一つ隣から順に確認
                for(int i = 2; true; i++) {
                    if(x - i < 0 || board[y][x - i].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y][x - i].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素の上書き
                            board[y][x - t] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }

    static public void turnRightDown(int x, int y) {
        if(y < 6 && x < 6) {

            // 隣のコマ
            String next = board[y + 1][x + 1];

            // 隣のコマが裏コマの場合
            if(next.equals(rev_stone)) {

                // さらにその一つとなりから順に確認
                for(int i = 2; true; i++) {
                    if(x + i > 7 || y + i > 7 || board[y + i][x + i].equals(EMPTY)) {
                        // コマがない場合終了
                        break;
                    } else if(board[y + i][x + i].equals(stone)) {
                        // 自コマの場合

                        // 間のコマを全て自コマにひっくり返す
                        for(int t = 1; t < i; t++) {
                            // 配列の要素を上書き
                            board[y + t][x + t] = stone;
                        }
                        break;
                    }
                }
            }
        }
    }








}
