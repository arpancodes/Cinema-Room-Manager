import java.util.Scanner;

public class Cinema {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // Write your code here

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();

        char[][] arr = new char[rows][seats];

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < seats; j++)
                arr[i][j] = 'S';

        menu(arr, rows, seats);

    }

    public static void menu(char[][] arr, int rows, int seats){
        int op;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            op = sc.nextInt();
            switch(op){
                case 1: showSeats(arr, rows, seats); break;
                case 2: buyTicket(arr, rows, seats); break;
                case 3: stats(arr, rows, seats); break;
            }

        }while(op != 0);
    }

    public static void showSeats(char[][] arr, int rows, int seats){
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++)
            System.out.print(i + " ");
        System.out.println("");

        for (int i = 0; i < rows; i++){
            System.out.print(i + 1);
            for (int j = 0; j < seats; j++){
                System.out.print(" " + arr[i][j]);
            }
            System.out.println("");
        }

    }

    public static void buyTicket(char[][] arr, int rows, int seats){
        int rowNo, seatNo;

        while(true){
            System.out.println("Enter a row number:");
            rowNo = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNo = sc.nextInt();

            if ((rowNo < 1 || rowNo > rows) || (seatNo < 1 || seatNo > seats)){
                System.out.println("Wrong input!");
            } else if (arr[rowNo - 1][seatNo - 1] == 'B'){
                System.out.println("That ticket has already been purchased!");
            }else{
                break;
            }
        }
        int totalSeats = rows * seats;
        int profit = 0;
        if(totalSeats < 60){
            profit = 10;
        }else{
            int front = rows/2;
            int back = rows-front;
            if (rowNo <= front)
                profit = 10;
            else
                profit = 8;
        }
        System.out.println("Ticket price: $" + profit);

        arr[rowNo-1][seatNo-1] = 'B';
    }

    public static void stats(char[][] arr, int rows, int seats){
        int totalProfit = 0, front = 0, back = 0, currentProfit = 0, seatsBooked = 0;
        int totalSeats = rows * seats;
        if (totalSeats < 60){
            totalProfit = 10 * totalSeats;
        } else {
            front = rows/2;
            back = rows-front;
            totalProfit = (front * seats * 10) + (back * seats * 8);
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < seats; j++){
                if (arr[i][j] == 'B'){
                    seatsBooked++;
                    if(totalSeats < 60)
                        currentProfit += 10;
                    else if(i + 1 <= front)
                        currentProfit += 10;
                    else
                        currentProfit += 8;
                }
            }
        }

        float percent = ((float)seatsBooked/totalSeats) * 100;

        System.out.println("Number of purchased tickets: " + seatsBooked);
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.println("Current income: $" + currentProfit);
        System.out.println("Total income: $" + totalProfit);

    }
}