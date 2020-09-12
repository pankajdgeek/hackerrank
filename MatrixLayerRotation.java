import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MatrixLayerRotation { 

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int move) {
            int round =0;
            int column =0;
            int row = 0;
            int count =0 ;
            int direction =1 ;
            List<Integer> sorted = new ArrayList();
            int c = matrix.get(0).size()-1;
            int r = matrix.size()-1;

            while(((c+1)*(r+1))>count){
                sorted.add(matrix.get(row).get(column));
                if(direction==1){
                    ++column;
                    if(column==(c-round))
                        ++direction;
                }else if(direction==2){
                    ++row;
                    if(row==(r-round))
                        ++direction;
                }else if(direction==3){
                    --column;
                    if(column==round)
                        ++direction;
                }else if(direction==4){
                    --row;
                    if(row==round+1){
                        direction=1;     
                        ++round;                   
                    }
                }
                ++count;
            }
            round =0;
            column =0;
            row = 0;
            count =0 ;
            direction =1 ;
            int tempRound= 0;
            int objInRound=(((c+1)-(2*tempRound))*2)+((((r+1)-(2*tempRound))-2)*2);
            int startIndex= 0;
            int[][] result =new int[r+1][c+1];
            int moveTemp=0;
            if(move>=objInRound){
                moveTemp=move%objInRound;
            }else{
                moveTemp=move;
            }
            while(((c+1)*(r+1))>count){
                if(row==column&&count==startIndex+objInRound){
                    tempRound=row;
                    startIndex+=objInRound;
                    objInRound = (((c+1)-(2*tempRound))*2)+((((r+1)-(2*tempRound))-2)*2);
                    if(move>=objInRound){
                        moveTemp=move%objInRound;
                    }else{
                        moveTemp=move;
                        }
                }
                int temp = count+moveTemp;
                if(temp>=(startIndex+objInRound)){
                    temp=startIndex+(temp-(startIndex+objInRound));
                }
                result[row][column]=sorted.get(temp);
                if(direction==1){
                    ++column;
                    if(column==(c-round))
                        ++direction;
                }else if(direction==2){
                    ++row;
                    if(row==(r-round))
                        ++direction;
                }else if(direction==3){
                    --column;
                    if(column==round)
                        ++direction;
                }else if(direction==4){
                    --row;
                    if(row==round+1){
                        direction=1;     
                        ++round;                   
                    }
                }
                ++count;
            }
        for(int i=0;i<r+1;i++){
            for(int j=0;j<c+1;j++){
                System.out.print(result[i][j]+" ");
            }
            System.out.println("");
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
