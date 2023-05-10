import java.awt.*;

public class PlayfairCipher {
    private static char[][] matrix = new char[5][5];
    private static Point[] positions = new Point[26];

    public static String code(String str) {
        str = makeText(str);
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i += 2) {
            if (i == sb.length() - 1) {
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
            } else if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X');
            }
        }
        return codec(sb, 1).toString();
    }

    public static String decode(String str) {
        str = makeText(str);
        StringBuilder sb = codec(new StringBuilder(str), 4);
        for (int i = 0; i < sb.length() - 2; i += 2) {
                if ((sb.charAt(i) == sb.charAt(i + 2)) && (sb.charAt(i + 1) == 'X')) {
                sb.deleteCharAt(i + 1);
            }
        }
        return sb.toString();
    }

    private static StringBuilder codec(StringBuilder text, int direction) {
        int len = text.length();
        for (int i = 0; i < len; i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;

            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;

            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;

            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }

            text.setCharAt(i, matrix[row1][col1]);
            text.setCharAt(i + 1, matrix[row2][col2]);
        }
        return text;
    }

    public static void makeMatrix(String key) {
        key += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = makeText(key);
        key = key.replace("Q", "");

        int len = key.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = key.charAt(i);
            if (positions[c - 'A'] == null) {
                matrix[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }

    public static String showMatrix() {
        StringBuilder str = new StringBuilder();
        str.append("<html><body>");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                str.append(matrix[i][j]).append(" ");
            }
            str.append("<br>");
        }
        str.append("</body></html>");
        return str.toString();
    }

    public static String makeText(String text) {
        return text.toUpperCase().replaceAll("[^A-Z]", "");
    }
}
