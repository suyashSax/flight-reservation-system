/*
Acceptable Login Details:

username:   guest   password:   qwerty
username:   alpha   password:   12345
username:   teacher password:   beta
*/
import java.util.Scanner;
public class AirlineReservationSystem
{
 public static void main()
 {
    Scanner sc = new Scanner(System.in);
    boolean login = true;
    
    //Flight Data
    
    int flightNumber[] = {159,951};
    String flightName[] = {"Emirates EK ", "British BA "};
    int totalSeats[] = {5,10};//multiple of 5 for seat map generation
    String depart[] = {"DXB", "LHR"};
    String arrive[] = {"LHR", "DXB"};
    int reservedSeats[] ={0,0};
    int vacantSeats[] = {totalSeats[0]-reservedSeats[0], totalSeats[1]-reservedSeats[1]};
    
    //Seat Maps
    
    String seat_1[] = new String[totalSeats[0]];
    String seat_2[] = new String[totalSeats[1]];
    
    for(int i=0; i<seat_1.length; i++) //Initialises Seat Map for Flight#1
    {
        seat_1[i] = String.valueOf(i+1);
    }
    
    for(int i=0; i<seat_2.length; i++) //Initialises Seat Map for Flight#2
    {
        seat_2[i] = String.valueOf(i+1);
    }
            
    //Passenger Data
            
    String passName[] = new String[totalSeats[0]+totalSeats[1]];
    int passAge[] = new int[totalSeats[0]+totalSeats[1]];
    String passport[] = new String[totalSeats[0]+totalSeats[1]];
    String flight[] = new String[totalSeats[0]+totalSeats[1]];
    String nation[] = new String[totalSeats[0]+totalSeats[1]];
    int passNo[] = new int[totalSeats[0]+totalSeats[1]];
    int seatNo[] = new int[totalSeats[0]+totalSeats[1]];
    
    int count = -1;//Index of list of Passenger Details
    int passCount = 0;//Count of Number of Passengers (also Passenger Number)

    for(int i = 0; i<(totalSeats[0]+totalSeats[1]); i++)//Initialises default values to all elements in all passenger data arrays
    {
        passName[i] = "";
        passport[i] = "";
        flight[i] =  "";
        nation[i] = "";
        passNo[i] = 0;
        passAge[i] = 0;
        seatNo[i] = 0;
    }
         
    do//Runs only when authorised user has logged in
    {
        /*System.out.println("Welcome...\n\nUsername:");
        String user = sc.nextLine();
        System.out.println("\nPassword:");
        String pass = sc.nextLine();*/
        //if((user.equals("guest") && pass.equals("qwerty")) || (user.equals("alpha") && pass.equals("12345")) || (user.equals("teacher") && pass.equals("beta")))
        if(true)
        {
            login = true;//Login successfull, so the program will run...
            System.out.println("\nYou have successfully logged into the Airline Reservation Portal\n\n1. Book a Ticket\n2. Cancel a Ticket\n3. Print Details\n4. Sort Records\n5. Display Seat Availability\n6. Exit\n\nEnter Selection:");
            int menuChoice = sc.nextInt();
            switch(menuChoice)//Accepts menu option from user and considers case-by-case result
            //switch(sc.nextInt())
            {
                case 1://Book Ticket
                {
                   //Displays available flights
                   System.out.println("\nThere are " + flightNumber.length + " flights available to book on this portal: ");
                   System.out.println("\n1. " + flightName[0] + flightNumber[0] + " from " + depart[0] + " to " + arrive[0] + " with " + vacantSeats[0] + " available seats");
                   System.out.println("2. " + flightName[1] + flightNumber[1] + " from " + depart[1] + " to " + arrive[1] + " with " + vacantSeats[1] + " available seats\n\nPick a Flight and Enter Option Number:");
                   int option = sc.nextInt() - 1;//Stores flight choice such that direct reference to flight data arrays can be made
                   sc.nextLine();
                   if(1 <= vacantSeats[option])//checks if there is a seat vacant
                   {
                       if(option == 0)//for Flight 1
                           {
                                System.out.println("\nSeat Map - Economy");

                                for(int j=0; j<seat_1.length; j = j+5)//Displays flight seat map in a graphical interface
                                {
                                    System.out.println("\n| " + seat_1[j] + " | " + seat_1[j+1] + " | " + seat_1[j+2] + " | " + "   | " + seat_1[j+3] + " | " + seat_1[j+4] + " |");
                                    //System.out.println("----------------------------");
                                }
                                
                                boolean check = true;//flags completion of seat booking to control if menu should be rerun.
                                
                                do
                                {
                                    System.out.println("\nEnter Seat Number to Book:");
                                    int selection = sc.nextInt(); sc.nextLine();//stores seat selection number
                                    if(selection>seat_1.length){System.out.println("Invalid Entry..."); break;}//confirms the seat number exists
                                    
                                    for(int j=0; j<seat_1.length; j++)//loops through seat map array
                                    {
                                        if(seat_1[selection-1].equals(String.valueOf(selection)))//checks if seat selection is vacant on seat map
                                        {
                                            count++;//Accumulates passenger data index
                                            passCount++;//Accumulates total number of tickets booked, also passenger number (does not change even if other passengers cancel/book more tickets)
                                            
                                            //Accepts passenger data
                                            System.out.println("\nPassenger Name:");
                                            passName[count] = sc.nextLine();
                                            flight[count] = flightName[0] + flightNumber[0] + " " + depart[0] + " to " + arrive[0];
                                            System.out.println("\nPassport Number:");
                                            passport[count] = sc.nextLine();
                                            System.out.println("\nAge:");
                                            passAge[count] = sc.nextInt();
                                            sc.nextLine();
                                            System.out.println("\nNationality:");
                                            nation[count] = sc.nextLine();
                                            
                                            reservedSeats[0]++;//adds 1 to number of reserved seats in Flight 1
                                            vacantSeats[0]--;//reduces 1 to number of vacant seats in Flight 1
                                            
                                            seatNo[count] = selection;//assigns selected seat as Seat Number of Passenger
                                            seat_1[selection-1] = "X";//marks selected seat on map with a 'X' signalling the seat has been booked
                                            passNo[count] = passCount;//assigns passenger number to passenger
                                            
                                            System.out.println("\nSeat No. " + selection + " has been booked for you!");//Announces successful booking
                                            check = true;//Confirms successful booking
                                            break;//once seat has been booked, other vacant seats are not considered
                                        }
                                        else//runs when the selected seat has been booked with a 'X' on the seat map
                                        {
                                            System.out.println("\nThis seat is booked...");
                                            check=false;//Seat booking unsuccessful... Repeat
                                            break;
                                         }
                                    }
                                    
                                }while(check==false);//Checks if Booking operation has been successful
                                    
                                    //Prints new seat map
                                    
                                    System.out.println("\nNew Seat Map - Economy");
                                    
                                    for(int k=0; k<seat_1.length; k = k+5)
                                    {
                                        System.out.println("\n| " + seat_1[k] + " | " + seat_1[k+1] + " | " + seat_1[k+2] + " | " + "   | " + seat_1[k+3] + " | " + seat_1[k+4] + " |");
                                        //System.out.println("----------------------------");
                                    }

                           }
                           else if(option == 1)//Same operation for Flight 2
                           {
                                
                                System.out.println("\nSeat Map - Economy");

                                for(int j=0; j<seat_2.length; j = j+5)
                                {
                                    System.out.println("\n| " + seat_2[j] + " | " + seat_2[j+1] + " | " + seat_2[j+2] + " | " + "   | " + seat_2[j+3] + " | " + seat_2[j+4] + " |");
                                    //System.out.println("----------------------------");
                                }
                                
                                boolean check = true;
                                
                                do
                                {
                                    System.out.println("\nEnter Seat Number to Book");
                                    int selection = sc.nextInt();
                                    sc.nextLine();
                                    if(selection>seat_2.length){System.out.println("Invalid Entry..."); break;}
                                    for(int j=0; j<seat_2.length; j++)
                                    {
                                        if(seat_2[selection-1].equals(String.valueOf(selection)))
                                        {
                                            count++;
                                            passCount++;
                                            System.out.println("\nPassenger Name:");
                                            passName[count] = sc.nextLine();
                                            flight[count] = flightName[1] + flightNumber[1] + " " + depart[1] + " to " + arrive[1];
                                            reservedSeats[1]++;
                                            vacantSeats[1]--;
                                            System.out.println("\nPassport Number:");
                                            passport[count] = sc.nextLine();
                                            System.out.println("\nAge:");
                                            passAge[count] = sc.nextInt();
                                            sc.nextLine();
                                            System.out.println("\nNationality:");
                                            nation[count] = sc.nextLine();
                                            System.out.println("\nSeat No. " + selection + " has been booked for you!");
                                            seatNo[count] = selection;
                                            seat_2[selection-1] = "X";
                                            passNo[count] = passCount;
                                            //System.out.println("\nEnter for Passenger #" + i);

                                            check = true;
                                            break;                                      
                                        
                                        }
                                        else
                                        {
                                            System.out.println("\nThis seat is booked...");
                                            check=false;
                                            break;
                                         }
                                    }
                                    
                                }while(check==false);
                                    
                                    System.out.println("\nNew Seat Map - Economy");
                                    for(int k=0; k<seat_2.length; k = k+5)
                                    {
                                        System.out.println("\n| " + seat_2[k] + " | " + seat_2[k+1] + " | " + seat_2[k+2] + " | " + "   | " + seat_2[k+3] + " | " + seat_2[k+4] + " |");
                                        //System.out.println("----------------------------");
                                    }
                                    
                                    //System.out.println("\nAgain? T/F");
                                    //check = sc.nextLine().equals("T")?true:false;
                                //}while(check);
                           }
                       
                       
                       System.out.println("\nReturning to Root Menu..."); break;//returns to menu after booking completed
                    }
                    else//runs when there are too few seats vacant
                    {
                        System.out.println("\nSorry, this flight has less than 1 ticket available... Retry"); break;
                    }
                }
                case 3://Printing Ticket from Search of Passenger Name
                {
                   System.out.println("\nEnter Name of Passenger to Print Ticket Details:");
                   sc.nextLine();
                   String inputName = sc.nextLine();
                   boolean check2 = false;//controls completion state of operation
                   for(int i = 0; i<(totalSeats[0]+totalSeats[1]); i++)//loops through array of passenger names
                   {
                       if(inputName.equalsIgnoreCase(passName[i]))//searches for match with inputted name
                       {
                           //printing ticket details
                           System.out.println("\nBooking Status: CONFIRMED\nPassenger: " + passName[i] + "\nFlight: " + flight[i] + "\nPassport Number: " + passport[i] + "\nAge: " + passAge[i] + "\nNationality: " + nation[i] +"\nSeat Number: " + seatNo[i] + "\nPassenger Number: " + passNo[i]);
                           check2 = true;//operation successful
                           break;
                       }
                   }
                   if(check2==false)//error handling
                   {
                      System.out.println("\nThis Passenger Doesn't Have a Booking"); 
                   }
                   break;
                }
                case 5://Seat Availability
                {
                   //Displays flight data and number of seats reserved
                   System.out.println("\nThere are " + flightNumber.length + " flights available  on this portal: ");
                   System.out.println("\n1. " + flightName[0] + flightNumber[0] + " from " + depart[0] + " to " + arrive[0] + " with " + vacantSeats[0] + " available seats");
                   System.out.println("2. " + flightName[1] + flightNumber[1] + " from " + depart[1] + " to " + arrive[1] + " with " + vacantSeats[1] + " available seats\n\nPick a Flight to View its Seat Map");
                   //Accepts flight for which seat map is to be displayed
                   int input = sc.nextInt();
                   sc.nextLine();
                   if(input==1)//displays seat map for Flight 1
                   {
                      System.out.println("\nThere are " + vacantSeats[0] + " available seats as follows:");

                      for(int j=0; j<seat_1.length; j = j+5)
                      {
                        System.out.println("\n| " + seat_1[j] + " | " + seat_1[j+1] + " | " + seat_1[j+2] + " | " + "   | " + seat_1[j+3] + " | " + seat_1[j+4] + " |");
                      }
                      break;
                   }
                   else if(input==2)//displays seat map for Flight 2
                   {
                      System.out.println("\nThere are " + vacantSeats[1] + " available seats as follows:");

                      for(int j=0; j<seat_2.length; j = j+5)
                      {
                        System.out.println("\n| " + seat_2[j] + " | " + seat_2[j+1] + " | " + seat_2[j+2] + " | " + "   | " + seat_2[j+3] + " | " + seat_2[j+4] + " |");
                      }
                      break;
                   }
                   else//error handling
                   {
                      System.out.println("Enter Valid Statement...");
                      break;
                   }
                }
                case 4://Sorts all records in alphabetical order of Passenger name
                { 
                    int N = reservedSeats[0] + reservedSeats[1];//maximum length of passenger list array

                    String tempString = new String("");//temporary variable for sorting String elements
                    int tempInt = 0;//temporary variable for sorting integer elements
                    
                    System.out.println("\nSorted Records");
                    
                    //Existing passenger records are duplicated in temporary arrays using clone method.
                    //This preserves original passenger record indexes without disturbing functionality of other options in menu system
                    
                    String tempName[] = passName.clone();
                    int tempAge[] = passAge.clone();
                    String tempPassport[] = passport.clone();
                    String tempFlight[] = flight.clone();
                    String tempNation[] = nation.clone();
                    int tempPassNo[] = passNo.clone();
                    int tempSeatNo[] = seatNo.clone();
                    
                    //Bubble Sort Algorithm
                    for(int i=0;i<N;i++)
                    {
                        for(int j=0;j<N-1;j++)
                        {
                            if((tempName[j].compareTo(tempName[j+1]))>0)//swaps passenger data in A-Z alphabetical order of Passenger name
                            {
                                tempString=tempName[j];
                                tempName[j]=tempName[j+1];
                                tempName[j+1]=tempString;
                                
                                tempString=tempFlight[j];
                                tempFlight[j]=tempFlight[j+1];
                                tempFlight[j+1]=tempString;
                                
                                tempInt=tempAge[j];
                                tempAge[j]=tempAge[j+1];
                                tempAge[j+1]=tempInt;
                                
                                tempString=tempNation[j];
                                tempNation[j]=tempNation[j+1];
                                tempNation[j+1]=tempString;
                                
                                tempString=tempPassport[j];
                                tempPassport[j]=tempPassport[j+1];
                                tempPassport[j+1]=tempString;
                                
                                tempInt=tempPassNo[j];
                                tempPassNo[j]=tempPassNo[j+1];
                                tempPassNo[j+1]=tempInt;
                                
                                tempInt=tempSeatNo[j];
                                tempSeatNo[j]=tempSeatNo[j+1];
                                tempSeatNo[j+1]=tempInt; 
                            }
                        }
                        //Prints record in tabular format
                        System.out.println("\n"+tempName[i]+"\t\t"+tempFlight[i]+"\t\tPassenger #"+tempPassNo[i]+"\t\tSeat #"+tempSeatNo[i]+"\t\t"+tempPassport[i]+"\t\tAge: "+tempAge[i]+"\t\t"+tempNation[i]);
                   
                    } break; }
                
                case 2://Ticket Cancellation
                {
                   //Displays basic flight information
                   System.out.println("\nThere are " + flightNumber.length + " flights managed on this portal:");
                   System.out.println("\n1. " + flightName[0] + flightNumber[0] + " from " + depart[0] + " to " + arrive[0] + " with " + reservedSeats[0] + " reserved seats");
                   System.out.println("2. " + flightName[1] + flightNumber[1] + " from " + depart[1] + " to " + arrive[1] + " with " + reservedSeats[1] + " reserved seats\n\nOn what flight do you have a current reservation?");
                   int option = sc.nextInt();//accepts flight option for which ticket is to be cancelled
                   sc.nextLine();
                   System.out.println("\nEnter Name of Passenger to Cancel Booking:");
                   String inputName = sc.nextLine();//accepts name of passenger whose ticket is to be cancelled
                   
                   boolean check3 = false;//checks completion state of cancellation operation
                   
                   for(int i = 0; i < passName.length; i++)//loops through array list of passengers
                       {
                           //Flight 1
                           if(inputName.equalsIgnoreCase(passName[i]) && option == 1 && flight[i].startsWith(flightName[0]))//matches inputted name to passenger with same name and flight option
                           {
                              reservedSeats[0]--;//the number of reserved seats on flight 1 is reduced by 1
                              vacantSeats[0]++;//the number of vacant seats on flight 1 is reduced by 1
                              count--;//the index of new passengers will decrease by 1, on account of removal of one passenger
                              
                              seat_1[seatNo[i]-1] = String.valueOf(seatNo[i]);//Switched booked state 'X' with original state number on flight map
                              
                              for (int j = i; j < passName.length-1; j++)//loops from index of element to be removed to end of passenger data lists
                              {
                                   //Each element after the element to be removed (cancelled ticket) is shifted by one index to the left.
                                   //This is done for all arrays holding passenger data
                                   
                                   passName[j] = passName[j+1];
                                   passNo[j] = passNo[j+1];
                                   seatNo[j] = seatNo[j+1];

                                   passAge[j] = passAge[j+1]; 
                                   passport[j] = passport[j+1];
                                   nation[j] = nation[j+1];
                                   flight[j] = flight[j+1];

                              }
                              
                              //The last index of all arrays containing passenger data is initialised to default value on shift of all elements to previous index
                              passName[passName.length-1] = "";
                              flight[passName.length-1] = "";
                              passNo[passName.length-1] = 0;
                              seatNo[passName.length-1] = 0;

                              System.out.println("\nYour ticket has been cancelled");
                              check3 = true;//updates completion state of cancellation operation
                              break;
                           }
                           else if(inputName.equalsIgnoreCase(passName[i]) && option == 2 && flight[i].startsWith(flightName[1]))//same operation for flight 2
                           {
                              reservedSeats[1]--;
                              vacantSeats[1]++;
                              count--;
                              
                              //System.out.println("Seat Number" + seatNo[i]);
                              seat_2[seatNo[i]-1] = String.valueOf(seatNo[i]);
                              
                              //Creating Space 
                              passName[passName.length-1] = "";
                              flight[passName.length-1] = "";
                              passNo[passName.length-1] = 0;
                              seatNo[passName.length-1] = 0;

                              for (int j = i; j < passName.length-1; j++)
                              {
                                   
                                   passName[j] = passName[j+1];
                                   passNo[j] = passNo[j+1];
                                   //seatNo[j] = seatNo[j+1];
                                   
                                   passAge[j] = passAge[j+1]; 
                                   passport[j] = passport[j+1];
                                   nation[j] = nation[j+1];
                                   flight[j] = flight[j+1];

                              }
                              
                              
                              
                              System.out.println("\nYour ticket has been cancelled");
                              check3 = true;
                              break;
                           }
                       }
                       if(check3==false)//error handling
                       {
                              System.out.println("\nThis passenger does not have a booking on this flight... Retry"); break;
                       }
                       break;
                   }
                case 6: {System.out.println("\nExiting System..."); login = false; break;}//Exits the menu driven program
            }
        }    
        else//executes if user has entered incorrect login details
        {
            System.out.println("Incorrect Login Details... Rebooting");
        }
    }while(login);//confirms user is logged in
}}