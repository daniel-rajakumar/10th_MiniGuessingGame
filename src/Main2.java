/**
 * Date: May 22, 2020
 * Programmer: Daniel 
 * Mini guessing game 
 */
import java.util.Scanner;
import java.util.Arrays;

public class Main2 {

    //only for testing purposes! 
    boolean developerOption = false;//shows the database, after the guess !!!Warning for printing large data set!!!

    //Game settings
    int numberRange_From = 1;
    int numberRange_To = 12000;
    //loading effect
    boolean loadingProcess = true;
    int loadingSpeed = 50;//ms
    //typewrite effect
    boolean typeWriteEffect = true;
    int writeSpeed = 20;//ms



    final Scanner enter = new Scanner(System.in);

    Main2(){
        if(loadingProcess) load();
        run();
    }

    //member variables
    int amount;//difficulty 
    int guess;
    int[] list;
    boolean correct;
    byte myScore = 0;

    void run(){
        intro();
        //select level
        SelectDifficulty();
        //Play
        Ask_Generate_Sort_Search_Repeat_Display();   
    }

    void SelectDifficulty(){
        amount = askQuestion("(keep it under 1billion) Select your difficult level");
        drawLine();
    }

    void Ask_Generate_Sort_Search_Repeat_Display(){
        //Ask
        guess = askQuestion("(" + numberRange_From + " <= level <= " + numberRange_To + ") Guess a number");
        //Generate
        list = generateAListOfRandomNumbers(amount, numberRange_From, numberRange_To);
        //Sort
        list = new sort().quickSort(list);
        //Search
        correct = new search().binarySearch(list, guess);
        //For testing purposes
        if(developerOption) printList(list);
        //repeat, if needed...
        if(correct) {
            myScore++;
            typeWritingEffort("\nCorrect! (Score: " + myScore + ")\n");
            drawLine();
            Ask_Generate_Sort_Search_Repeat_Display();
        } else {
            typeWritingEffort("\nWrong :(\t("+ guess +" not in the database)\n");
            drawLine();
            //Display... score
            displayScore();
        }
    }

    void intro(){
        typeWritingEffort("Welcome to The Guessing Game of 2020!!!");
        typeWritingEffort("\n\nYour task:");
        typeWritingEffort("\n1) Select difficulty level [Tip: the bigger your difficulty level, the easier it is]");
        typeWritingEffort("\n2) Guess a number between (" + numberRange_From + " - " + numberRange_To + ")");
        typeWritingEffort("\n\nAbout game: ");
        typeWritingEffort("\nif Guess is right then, game will continues (+1 points)");
        typeWritingEffort("\nelse the game will ends... with scores displayed");
        typeWritingEffort("\n\nAbout guess: ");
        typeWritingEffort("\nYour guess should should at least match one value in the random generated database");
        typeWritingEffort("\nor else, you lose");
        System.out.println();

        drawLine();
    }

    void drawLine(){
        System.out.println("________________________________________________________________");
    }

    void displayScore(){
        typeWritingEffort("\n\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        typeWritingEffort("\nYour Score: " + myScore);
        typeWritingEffort("\n\nThanks for playing!");
    }

    int askQuestion(final String question){
        typeWritingEffort("\n" + question + ": ");
        int ans = enter.nextInt();
        enter.nextLine();
        return ans;
    }

    int[] generateAListOfRandomNumbers(int length, int from, int to){
        int[] list = new int[length];

        for (int i = 0; i < length; i++) 
            list[i] = GenerateRandomNumber(from, to);

        return list;
    }

    int GenerateRandomNumber(int from, int to){
        return (int)(Math.random()*((to - from)+1)) + from;
    }

    void printList(int[] list){
        System.out.print("\nDatabase: " + Arrays.toString(list) + "\n\n");
    }

    //sort
    class sort {
        int[] quickSort(int[] list){
            return quickSort(list, 0, list.length-1);
         }
     
         int[] quickSort(int[] list, int l, int r){
             if(l < r){
                 int p = partition(list, l, r);
                 quickSort(list, l, p-1);
                 quickSort(list, p+1, r);
             }
             return list;
         }
     
         int partition(int[] list, int l, int r){
             int pivot = GenerateRandomNumber(l, r);
             swap(list, pivot, r);
             pivot = r;
     
             int i = l-1;
             int j = l;
     
             while (j < pivot) {
                 if(list[j] < list[pivot]){
                     i++;
                     swap(list, j, i);
                 }
                 j++;
             }
     
             swap(list, i+1, pivot);
             return i+1;
         }
     
         void swap(int[] list, int a, int b){
             int temp = list[a];
             list[a] = list[b];
             list[b] = temp;
         }
    }

    //search
    class search{

        boolean binarySearch(int[] list, int key){  
            return binarySearch(list, 0, list.length-1, key);
        }

        boolean binarySearch(int[] list, int l, int r, int key){
            int m = l + (r - l)/2;
            
            if(l <= r){
                if(key < list[m]) return binarySearch(list, l, m-1, key);
                if(list[m] < key) return binarySearch(list, m+1, r, key);
                return true;
            }

            return false;
        }
    }












    void load(){
        char done = '+';
        char left = '-';

        String animationList= "|/-\\";
        String progressBar = "";
        String ProgressInfo = "";
        
        String ProgressLeft = "";

        for(int i = 0; i <= 100; i++)  ProgressLeft += left;

        

        for (int i = 0; i <= 100; i++) {// i = %
            progressBar += done;
            ProgressLeft = charRemoveAt(ProgressLeft, i);
            ProgressInfo = "\r" + animationList.charAt(i % animationList.length()) + " " + progressBar + ProgressLeft +  " [" + i + "%] ";
            System.out.print(ProgressInfo);
            try {Thread.sleep(loadingSpeed);} catch (Exception e) {}
        }

        System.out.println("\n");
    }

    String charRemoveAt(String str, int index) {  
        StringBuilder sb = new StringBuilder(str);
        sb.deleteCharAt(0);
        return sb.toString(); 
     }  



    void typeWritingEffort(String message){
        if(!typeWriteEffect) writeSpeed = 0;
        for (int i = 0; i < message.length(); i++){
            System.out.print(message.charAt(i));
            try{Thread.sleep(writeSpeed);} catch (InterruptedException e){}
        }
    }













    public static void main(String[] args) {
        new Main2();
    }
}
