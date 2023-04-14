import java.util.ArrayList;
class Main {
    public static void main(String[] args) {
        // testing genMatrix()
        ArrayList<ArrayList<Integer>> test = genMatrix();
        // testing genMatrix() + printMatrix()
        printMatrix(test);
        // testing sumMatrix()

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
        int size = row ? mat.size() : mat.get(0).size();
        ArrayList<Integer> sum = new ArrayList<Integer>(size);
            
    }
}