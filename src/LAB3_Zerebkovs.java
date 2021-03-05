import java.util.Arrays;
import java.util.Scanner;


public class LAB3_Zerebkovs {


    public static void main(String[] args) {
        Utils utils = new Utils();
        Boolean closeProgram = false;
        long startTime = 0;
        long endTime = 0;

        int[] unsortedArr = utils.readArray("source1.txt");
        Scanner userChoice = new Scanner(System.in);

        if (unsortedArr == null) {

            System.out.println("source.txt NOT FOUND");

            unsortedArr = utils.randomArray(getArraySize(userChoice), 100000, -100000);
       }

        utils.showArray(unsortedArr);

        do {
            int[] arrayCopy = Arrays.copyOf(unsortedArr, unsortedArr.length);
            switch (choiceOfAlgorithm(userChoice)) {
                case 1:

                    startTime = System.nanoTime();
                    ShellSort(arrayCopy);
                    endTime = System.nanoTime();
                    break;
                case 2:
                    startTime = System.nanoTime();
                    QuickSort(arrayCopy,0,arrayCopy.length-1);
                    endTime = System.nanoTime();
                    break;
                case 3:
                    startTime = System.nanoTime();
                    ShellImproved(arrayCopy);
                    endTime = System.nanoTime();
                    break;
                case 4:

                    userChoice.close();
                    closeProgram = true;
            }

            if (!closeProgram) {
                utils.writeOnFile(arrayCopy, "result.txt");
                System.out.println("Printing sorted array");
                utils.showArray(arrayCopy);

                if (utils.isAscending(arrayCopy)) {
                    System.out.println("Array is sorted");
                } else {
                    System.out.println("Array is unsorted");
                }
                System.out.println("Sorting Time = " + (endTime - startTime) / 1000000 + " milliseconds");
            }
        }while (!closeProgram);

    }


    public static int choiceOfAlgorithm(Scanner sc){
        int choice = 0;
        Boolean isValid = false;

        do {
            System.out.println("1: Sort Array Using Shell Sort algorithm");
            System.out.println("2: Sort Array Using Quick sort algorithm");
            System.out.println("3: Sort Array Using Improved Shell Sort algorithm(using Knuth's Formula)");

            System.out.println("4: Exit program");
            System.out.print("Your choice ? =");
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                if(choice >=1 && choice<=4){
                    isValid = true;
                }else{
                    System.out.println("Wrong input.Choose one of choices or close program.");
                }

            }else{
                sc.nextLine();
                System.out.println("Wrong input.Choose one of choices or close program.");
            }

        }while (!isValid);


        return choice;
    }

    public static int getArraySize(Scanner sc){
        int arraySize = 0;
            boolean isValid = false;
        do{
            System.out.print("Array size [0...100000] : ");
            if(sc.hasNextInt()){
                arraySize = sc.nextInt();
                if(arraySize > 0 && arraySize <= 100000){
                    isValid = true;
                }else{
                    System.out.println("Input is negative or exceed 100000");
                }
            }else{
                sc.nextLine();
                System.out.println("Enter a valid Integer value");
            }
        }while(!isValid);

        return arraySize;
    }





    public static void ShellSort(int[] arr) {
        int n = arr.length;
        for (int h = n / 2; h > 0; h /= 2) {
            for (int i = h; i < n; i += 1) {
                int key = arr[i];
                int j;
                for (j = i; j >= h && arr[j - h] > key; j = j - h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = key;
            }
        }

    }

    public static void ShellImproved(int [] arr){
        int n = arr.length;

        int h = 1;
        while(h<n/3){
            h= 3*h+1;
        }
        while(h>=1){
            for (int i = h; i < n; i += 1) {
                int key = arr[i];
                int j;
                for (j = i; j >= h && arr[j - h] > key; j = j - h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = key;
            }
            h = h/3;
        }
    }


    public static void QuickSort(int[] arr,int L, int R) {
        int li = L;
        int ri = R;
        int pi = (L+R)/2;
        int p = arr[pi];
        while(li <=ri){

            while(arr[li] <p){
                li++;
            }

            while(arr[ri]>p){
                ri--;
            }
            if(li<=ri){
                swap(arr,li,ri);
                li++;
                ri--;
            }
        }

        if(L<ri){
            QuickSort(arr,L,ri);
        }
        if(li<R){
            QuickSort(arr,li,R);
        }

    }


    static void swap(int[] arr, int position1, int position2) {

        int temp = arr[position1];
        arr[position1] = arr[position2];
        arr[position2] = temp;

    }

}
