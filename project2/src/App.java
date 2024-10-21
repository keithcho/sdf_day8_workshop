import java.util.*;

public class App {
    static void permute(List<Character> arr, int k){
        for(int i = k; i < arr.size(); i++){
            // System.out.printf("i: %d, k: %d\n", i, k);
            System.out.printf("Swapping %c and %c\n", arr.get(i), arr.get(k));
            Collections.swap(arr, i, k);
            permute(arr, k+1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            System.out.println(Arrays.toString(arr.toArray()));
        }
    }
    public static void main(String[] args) {
        String input = args[0];
        List<Character> arr = new ArrayList<>();
        
        for (int i = 0; i < input.length(); i++) {
            arr.add(input.charAt(i));
        }

        App.permute(arr, 0);
    }
}