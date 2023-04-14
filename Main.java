import java.util.ArrayList;
class Main {
    public static void main(String[] args) {
        // testing genMatrix()
        ArrayList<ArrayList<Integer>> test = genMatrix();
        // testing genMatrix() + printMatrix()
        printMatrix(test);
        // testing sumMatrix()
        for(int e : sumMatrix(test, true))
            System.out.println(e);
        for(int e : sumMatrix(test, false))
            System.out.print(e + " ");
        System.out.println();
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
}