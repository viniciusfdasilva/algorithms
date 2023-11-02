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
public class Lcs
{
    
    // Matriz LCS
    static int[][] matriz;

    static int column_size = 0;
    static int row_size    = 0; 
    
    // Primeira sequencia que será a coluna da matriz LCS
    static String[] column;

    // Segunda sequencia que será a linha da matriz LCS
    static String[] row;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Exemplo de entrada: 1 2 3 5
        column  = scanner.nextLine().split(" ");
        row     = scanner.nextLine().split(" ");
    
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
        
        System.out.println(diff() ? "Os arquivos são iguais" : "Existe diferença entre os arquivos"); 
    }

    /**
     * Algoritmo que identifica a maior subsequencia
     * comum entre duas sequencias (LCS).
     * 
     * @param void
     * @return void
     */
    static void lcs()
    {
        for(int i = 1; i < column_size; i++)
        {
            for(int j = 1; j < row_size; j++)
            {

                int up_element       = matriz[i-1][j];
                int left_element     = matriz[i][j-1];
                int diagonal_element = matriz[i-1][j-1];

                // Pega o valor máximo entre o elemento acima, do lado esquerdo e da diagonal
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

    /**
     * Inicializa a primeira coluna e a
     * primeira linha com zero
     * 
     * @param void
     * @return void
     */
    static void init_first_line_and_column()
    {
        for(int i = 0; i < column_size; i++)
        {
            matriz[0][i] = 0;
            matriz[i][0] = 0;
        }
    }


    /**
     * Verifica a diagonal principal da matriz,
     * caso a matriz seja não crescente existe
     * pelo menos uma diferença no arquivo. Caso
     * contrário os arquivos são iguais.
     * 
     * @param void
     * @return true se os arquivos são iguais false
     * caso contrário.
     */
    static boolean diff()
    {
        int val = matriz[0][0];

        for(int i = 1; i < row_size; i++)
        {
            if(val >= matriz[i][i]) return false;
            val = matriz[i][i];
        }

        return true;
    }
}

