import java.util.ArrayList;
class Main {
    public static void main(String[] args) {
        // testing genMatrix()
        ArrayList<ArrayList<Integer>> test = genMatrix();
        // testing genMatrix() + printMatrix(int[][])
        printMatrix(test);
        // testing sumMatrix(int[][], boolean)
        System.out.println("Sum of rows:");
        for(int e : sumMatrix(test, true))
            System.out.println(e);
        System.out.println("Sum of columns:");
        for(int e : sumMatrix(test, false))
            System.out.print(e + " ");
        System.out.println("\n");
        // testing transposeMatrix(int[][])
        // System.out.println("Original matrix:");
        // printMatrix(test);
        System.out.println("Transposed matrix:");
        printMatrix(transposeMatrix(test));
        // testing sumMatrix(int[][])
        // System.out.println("Original matrix:");
        // printMatrix(test);
        System.out.print("sumMatrix(int[][]):");
        for(int e : sumMatrix(test))
            System.out.print(e + " ");
        System.out.println("\n");
        // testing sortMatrix(int[][], int, boolean)
        // System.out.println("Original matrix:");
        // printMatrix(test);
        System.out.println("Matrix after sorting row 0:");
        sortMatrix(test, 0, true);
        printMatrix(test);
        System.out.println("Matrix after sorting col 2:");
        sortMatrix(test, 2, false);
        printMatrix(test);
        // testing multiMatrix(int[][], int[][])
        ArrayList<ArrayList<Integer>> test1 = genMatrix();
        ArrayList<ArrayList<Integer>> test2;
        for(test2 = genMatrix() ; test1.get(0).size() != test2.size() ; test2 = genMatrix());
        System.out.println("Matrix A:");
        printMatrix(test1);
        System.out.println("Matrix B:");
        printMatrix(test2);
        System.out.println("Product of A * B:");
        printMatrix(multiMatrix(test1, test2));
    }

    public static ArrayList<ArrayList<Integer>> genMatrix(){
        int rows = (int)(Math.random() * 4) + 3;
        int cols = (int)(Math.random() * 4) + 3;
        ArrayList<ArrayList<Integer>> mat = new ArrayList<ArrayList<Integer>>(rows);
        for(int i = 0; i < rows ; i++){
            mat.add(new ArrayList<>(cols));
            for(int j = 0; j < cols ; j++)
                mat.get(i).add((int)(Math.random() * 10));
        }
        return mat;
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> mat){
        for(ArrayList<Integer> row : mat){
            for(int e : row)
                System.out.print(e + " ");
            System.out.println();
        }
        System.out.println();
    }

    // row == true ? returns a list containing sums of each row of mat 
    // row == false? returns a list containing sums of each column of mat
    public static ArrayList<Integer> sumMatrix(ArrayList<ArrayList<Integer>> mat, boolean row){
        // row == true ?    size = # of rows :    size = # of cols
        // row == true ? notSize = # of cols : notSize = # of rows
        int size =     row ? mat.size() : mat.get(0).size();
        int notSize = !row ? mat.size() : mat.get(0).size();
        ArrayList<Integer> sum = new ArrayList<Integer>(size);
        for(int i = 0; i < size ; i++){
            int total = 0;
            for(int j = 0 ; j < notSize ; j++)
                // i is constant for each iteration of this loop
                // therefore either the row or col depends on j which varies in each iteration
                total += mat.get(row ? i : j).get(row ? j : i);
            sum.add(total);
        }
        return sum;
    }

    public static ArrayList<ArrayList<Integer>> transposeMatrix(ArrayList<ArrayList<Integer>> mat){
        int trows = mat.get(0).size(); // # of cols of mat
        int tcols = mat.size(); // # of rows of mat
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<ArrayList<Integer>>(trows);
        for(int i = 0 ; i < trows ; i++){
            transpose.add(new ArrayList<Integer>(tcols));
            for(int j = 0 ; j < tcols ; j++)
                transpose.get(i).add(mat.get(j).get(i));
        }
        return transpose;
    }

    public static ArrayList<Integer> sumMatrix(ArrayList<ArrayList<Integer>> mat){
        int size = mat.size()+mat.get(0).size()-1;
        ArrayList<Integer> sum = new ArrayList<Integer>(size);
        for(int diag = 0 ; diag < size ; diag++){
            // **** Verbose way ****
            // int row, col;
            // if(diag < mat.size()){
            //     row = diag;
            //     col = 0;
            // }
            // else{
            //     row = mat.size() - 1;
            //     col = diag - mat.size() + 1;
            // }
            // **** Concise way ****
            int row = Math.min(diag, mat.size()-1);
            int col = diag < mat.size() ? 0 : diag - mat.size() + 1;
            int total = 0;
            while(row >= 0 && col < mat.get(0).size())
                total += mat.get(row--).get(col++);
            // this also works =)
            // for(total = 0 ; row < mat.size() && col < mat.get(0).size() ; total += mat.get(row--).get(col++)){}
            sum.add(total);
        }
        return sum;
    }

    public static void sortMatrix(ArrayList<ArrayList<Integer>> mat, int n, boolean row){
        // row == true ? size = # of cols :    size = # of rows
        int size = row ? mat.get(0).size() : mat.size();
        for(int i = 0; i < size ; i++){
            int bestIndex = i;
            for(int j = i ; j < size ; j++){
                int current = mat.get(row ? n : j).get(row ? j : n);
                int best = mat.get(row ? n : bestIndex).get(row ? bestIndex : n);
                if((row && (best > current)) || (!row && (best < current)))
                    bestIndex = j;
            }
            int temp = mat.get(row ? n : i).get(row ? i : n);
            mat.get(row ? n : i).set(row ? i : n, mat.get(row ? n : bestIndex).get(row ? bestIndex : n));
            mat.get(row ? n : bestIndex).set(row ? bestIndex : n, temp);
        }
    }

    // let a have dimensions i x j, and b have dimensions j x k
    // returns the product of matrix multiplication iff 
    // resultant matrix will have dimensions i x k
    public static ArrayList<ArrayList<Integer>> multiMatrix(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b){
        if (a.get(0).size() != b.size())
            return null; // impossible to multiply
        int mrows = a.size();
        int mcols = b.get(0).size();
        ArrayList<ArrayList<Integer>> multi = new ArrayList<ArrayList<Integer>>(mrows);
        for(int i = 0 ; i < mrows ; i++){
            multi.add(new ArrayList<Integer>(mcols));
            for(int j = 0 ; j < mcols ; j++){
                int total = 0;
                for(int k = 0 ; k < b.size() ; k++)
                    total += a.get(i).get(k) * b.get(k).get(j);
                multi.get(i).add(total);
            }
        }
        return multi;
    }
}