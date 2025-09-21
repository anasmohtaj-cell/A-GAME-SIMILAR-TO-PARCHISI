import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    static int size = 8;
    static Object[][] board = new Object[size][size];
    static Random rand = new Random();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = 0;
        int joueur1 = 0;
        int joueur2 = 63;
        char a = 'A';
        char b = 'B';
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = g;
                g++;
            }
        }
        board[0][0] = a;
        board[size - 1][size - 1] = b;
        int oldpos1 = 0;
        int oldpos2 = 63;
        int fois = 1;
        int DICE;
         printboard(board);
        while (true) {
            System.out.print("entrez ");
            String name = sc.nextLine();
            if (fois % 2 != 0) {

                DICE = aleatoir();
                System.out.println("joueur 1 = " + DICE);
                System.out.println("joueur 1 EST DANS LA CASE = " + joueur1);
                oldpos1 = joueur1;
                joueur1 = joueur1 + DICE;

                movePlayer(size, board, a, joueur1, oldpos1);
                handle(board, joueur1, size, a, b);

                printboard(board);

            } else {

                DICE = aleatoir();
                System.out.println("joueur 2 = " + DICE);
                System.out.println("joueur 2 EST DANS LA CASE = " + joueur1);
                oldpos2 = joueur2;
                joueur2 = joueur2 - DICE;
                movePlayer(size, board, b, joueur2, oldpos2);
                handle(board, joueur2, size, b, a);

                printboard(board);
            }

            boolean ee = check(board, joueur1, joueur2);
            if (ee) {
                break;
            }
            fois++;

        }



    }

    static int aleatoir() {

        return rand.nextInt(6) + 1;

    }

    static Object[][] movePlayer(int size, Object[][] boardf, char e, int newPos, int oldPos) {
        boardf[oldPos / size][oldPos % size] = oldPos;
        boardf[newPos / size][newPos % size] = e;
        return boardf;
    }

    static void printboard(Object[][] boardf) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("|");
                System.out.print("\t" + boardf[i][j] + "\t");

            }
            System.out.print("|");
            System.out.println();

        }
    }

    static boolean check(Object[][] boardf, int e, int f) {
        if (e >= 63) {
            System.out.println(" joueur 1 win");
            return true;
        } else if (f <= 0) {
            System.out.println(" joueur 2 win");
            return true;
        }
        return false;
    }

    static Object[][] handle(Object[][] boardf, int newpos, int size, char currentplayer, char otherPlayer) {
        int r = newpos / size;
        int c = newpos % size;
        if (boardf[r][c] instanceof Character) {
            otherPlayer = (char) boardf[r][c];
            if (otherPlayer != currentplayer) {
                System.out.println("⚔" + currentplayer + "se heurt a " + otherPlayer);
                int startposition = getStartPOsition(otherPlayer, size);
                int sr = startposition / size;
                int sc = startposition % size;
                boardf[sr][sc] = otherPlayer;
            }
            boardf[r][c] = currentplayer;
        }
        return boardf;
    }

    static int getStartPOsition(char Player, int Size) {
        switch (Player) {
            case 'A':
                return 0;
            case 'B':
                return Size * Size - 1;
            default:
                return 0;
        }
    }
}
