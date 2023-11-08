import java.util.Scanner;
import java.lang.Math;

public class LevenshteinDistance
{

    static int matriz[][];

    static int column_size = 0;
    static int row_size    = 0;
    static int cost;

    static String[] row;
    static String[] column;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        row    = scanner.nextLine().split(" ");
        column = scanner.nextLine().split(" ");

        column_size = column.length + 1;
        row_size    = row.length    + 1;

        matriz = new int[row_size][column_size];

        init_first_line_and_column();
        levenshtein_distance();
        show();
    }

    static void levenshtein_distance()
    {
        for(int i = 1; i < row_size; i++)
        {
            for(int j = 1; j < row_size; j++)
            {
                if(row[i-1].compareTo(column[j-1]) == 0){
                    cost = 0;
                }else
                {
                    cost = 1;
                }

                matriz[i][j] = Math.min(Math.min(matriz[i-1][j] + 1, matriz[i][j-1] + 1), matriz[i-1][j-1] + cost);
            }
        }
    }

    static void show()
    {
        System.out.println();

        for(int i = 0; i < row_size; i++)
        {
            for(int j = 0; j < column_size; j++)
            {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void init_first_line_and_column()
    {
        matriz[0][0] = 0;
        for(int i = 1; i < column_size; i++) matriz[0][i] = i-1;
        for(int i = 1; i < row_size; i++)    matriz[i][0] = i-1;
    }
}