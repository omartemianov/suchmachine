public class RecursiveDeterminant {

    public static int[][] removeRow(int[][] matrix, int rowIndex)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] result = new int[rows-1][columns];
        int index = 0;
        for (int i = 0; i < rows; i++)
        {
            if (i == rowIndex)
                continue;
            result[index] = matrix[i];
            index++;
        }
        return result;
    }

    public static int[][] removeColumn(int[][] matrix, int colIndex)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] result = new int[rows][columns-1];

        for (int i = 0; i < rows; i++)
        {
            int index = 0;
            for (int j = 0; j < columns; j++) {
                if (j == colIndex)
                    continue;
                result[i][index] = matrix[i][j];
                index++;
            }
        }
        return result;
    }

    public static int det3x3(int[][] matrix)
    {

        int det = 0;
        int sign = 1;
        for (int i = 0; i < 3; i++) {
            det += sign * matrix[i][0] * det2x2(removeColumn(removeRow(matrix, i), 0));
            sign *= -1;
        }
        return det;
    }

    public static int det2x2(int[][] matrix)
    {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    public static int detNxN(int[][] matrix)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;
        if (rows != columns)
            return -1;
        if (rows == 2){
            return det2x2(matrix);
        }
        int det = 0;
        int sign = 1;
        for (int i = 0; i < rows; i++)
        {
            det += sign * matrix[i][0] * detNxN(removeColumn(removeRow(matrix, i), 0));
            sign *= -1;
        }
        return det;
    }
    public static void main(String[] args)
    {
        int[][] mx = new int[5][5];
        for(int i=0;i<mx.length;i++)
        {
            for(int k=0;k<mx[0].length;k++)
            {
                mx[i][k]=2;
            }
        }
        System.out.println("For 5x5 matrix filled with 2, Det should be zero. using method detNxN gives: ");
        int deter = detNxN(mx);
        System.out.println(deter);
        System.out.println("Thereout method works succesfully");
        System.out.println("For same matrix");
        for(int i=0;i<mx.length;i++)
        {
            for(int k=0;k<mx[0].length;k++)
            {
                System.out.print(mx[i][k] +" ");
            }
            System.out.println();
        }
        System.out.println("Remove column method with columnindex 1 gives");
        mx =removeColumn(mx,1);
        for(int i=0;i<mx.length;i++)
        {
            for(int k=0;k<mx[0].length;k++)
            {
                System.out.print(mx[i][k] +" ");
            }
            System.out.println();
        }
        mx = removeRow(mx,1);
        System.out.println("Remove row method with rowindex 1 gives");
        for(int i=0;i<mx.length;i++)
        {
            for(int k=0;k<mx[0].length;k++)
            {
                System.out.print(mx[i][k] +" ");
            }
            System.out.println();
        }
        System.out.println("Successfull");
    }

}
