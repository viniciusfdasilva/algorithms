import java.util.Scanner;
import java.lang.Math;

/**
 * @version 0.1
 * @author Vinicius Silva
 * 
 * Introdução
 * 
 * O algoritmo LCS encontra entre duas sequencias a maior subsequencia comum entre elas.
 * Utilizado para verificação de diferenciação de textos, bioinformática, etc... o LCS
 * possui uma complexidade f(x) = m*n para duas sequências de tamanhos m e n.  
 * 
 */
public class Lcs{
    
    static int[][] matriz;

    static int column_size = 0;
    static int row_size    = 0; 
    
    static String[] column;
    static String[] row;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        column = scanner.nextLine().split(" ");
        row    = scanner.nextLine().split(" ");
    
        /*
         * Gera uma matriz (m+1)*(n+1) sendo m e 
         * n o tamanho das sequencias. Para inserir
         * 0 na primeira linha e primeira coluna.
         */
        column_size = column.length + 1;
        row_size    = row.length    + 1;
        matriz = new int[row_size][column_size];

        init_first_line_and_column();
        lcs();
        show();
    }

    static void lcs()
    {
        for(int i = 1; i < column_size; i++)
        {
            for(int j = 1; j < row_size; j++)
            {

                int up_element       = matriz[i-1][j];
                int left_element     = matriz[i][j-1];
                int diagonal_element = matriz[i-1][j-1];

                int max_value = Math.max(Math.max(up_element, left_element), diagonal_element);


                if(String.valueOf(column[j-1]).compareTo(String.valueOf(row[i-1])) == 0)
                {
                    matriz[i][j] = max_value+1;
                }else
                {
                    matriz[i][j] = max_value;
                }
            }
        }
    }

    static void init_first_line_and_column()
    {
        for(int i = 0; i < column_size; i++)
        {
             matriz[0][i] = 0;
             matriz[i][0] = 0;
        }
    }

    static void show()
    {
        System.out.println("");

        for(int i = 0; i < column_size; i++)
        {
            for(int j = 0; j < row_size; j++)
            {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        
    }

}

