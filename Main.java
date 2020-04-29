package text_interface;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static UserActivities useractivities1 = new UserActivities();
	
	public static int vertical_size = 25; // "WYSOKOŒÆ" NASZEGO "WYŒWIETLACZA W TELEFONIE"
	
	static String [] screen1 = new String[vertical_size]; //3 TABLICE STRINGÓW NA 3 ZAK£ADKI (WYSTARCZY£ABY JEDNA THOUGH)
	static String [] screen2 = new String[vertical_size];
	static String [] screen3 = new String[vertical_size];
	

	
	//FUNKCJA POMOCNICZ DO DRUKOWANIA NA ŒRODKU (C - CENTER)
	public static void print40c(String str)
	{
		int line_wide = 40;
		if (str != null && str.length()<=line_wide)
		{
			int stepleft = (line_wide - str.length())/2;
			int stepright = line_wide - (str.length() + stepleft);
			
			System.out.printf("|");
			for (int i=0; i<stepleft; i++)
				System.out.printf(" ");
			System.out.printf(str);
			for (int i=0; i<stepright; i++)
				System.out.printf(" ");
			System.out.printf("|\n");
		}
		else if (str == null)
		{
			System.out.printf("|");
			for (int i =0; i<40; i++) System.out.printf(" ");
			System.out.printf("|\n");
		}
		else 
			{
			System.out.printf("|");
			System.out.println(str);
			}
			
	}
	
	//FUNKCJA POMOCNICZ DO DRUKOWANIA od lewej (L -LEFT)
/*	public static void print40l(String str)
	{
		int line_wide = 40;
		if (str != null && str.length()<=line_wide)
		{
			int stepleft = 0;
			int stepright = line_wide - (str.length() + stepleft);
			
			System.out.printf("|");
			for (int i=0; i<stepleft; i++)
				System.out.printf(" ");
			System.out.printf(str);
			for (int i=0; i<stepright; i++)
				System.out.printf(" ");
			System.out.printf("|\n");
		}
		else if (str == null)
		{
			System.out.printf("|");
			for (int i =0; i<40; i++) System.out.printf(" ");
			System.out.printf("|\n");
		}
		else 
			{
			System.out.printf("|");
			System.out.println(str);
			}
			
	}
	*/
	
	public static void menu(int id) //MENU, CZYLI AKCJE DLA SCREENÓW 
	{
		System.out.println("What do you do: ");
		switch (id)
		{
		case 1: //1. JE¯ELI "KLIKNIEMY" ACTIVITIES
		{
			
			Scanner menu_input = new Scanner(System.in);
			int menu_input_number = 0;		
			int action_num = 0;
			try {
				menu_input_number = Integer.parseInt(menu_input.nextLine());
				action_num = menu_input_number;
			}
			catch (NumberFormatException e) {
				System.out.println("Not a valid number\n");
			}
			
			switch (action_num)
			{
			case 1:
			{
				display(screen1,1);
				menu(1);
				break;
			}
			case 2:
			{
				display (screen2,2);
				menu(2);
				break;
			}
			case 3:
			{
				display (screen3,3);
				menu(3);
				break;
			}
			case 4: //Wcisniecie [4.ADD NEW ACTIVITY]  
			{
				if (useractivities1.getCurrentActivity() == null)
				{
				System.out.printf("What type?: ");		
				Scanner input_name_scanner = new Scanner(System.in);
				String input_name = menu_input.nextLine();
	
				TActivity myactivity = new TActivity(input_name);
				useractivities1.addActivity(myactivity);
				display(screen1,1);
				menu(1);
				
				}
				else
				{
					useractivities1.endActivity(useractivities1.getCurrentActivity());
					display(screen1,1);
					menu(1);
					
				}
			}
			
			default: System.out.println("No such number or action undefined");
			}
		}
		case 2: //2.STATS
		{
			
			Scanner menu_input = new Scanner(System.in);
			int menu_input_number = 0;		
			int action_num = 0;
			try {
				menu_input_number = Integer.parseInt(menu_input.nextLine());
				action_num = menu_input_number;
			}
			catch (NumberFormatException e) {
				System.out.println("Not a valid number\n");
			}
			
			switch (action_num)
			{
			case 1:
			{
				display(screen1,1);
				menu(1);
				break;
			}
			case 2:
			{
				display (screen2,2);
				menu(2);
				break;
			}
			case 3:
			{
				display (screen3,3);
				menu(3);
				break;
			}
			
			default: System.out.println("No such number or action undefined");
			}
		}
		
		case 3: //3. GOALS
		{
			
			Scanner menu_input = new Scanner(System.in);
			int menu_input_number = 0;		
			int action_num = 0;
			try {
				menu_input_number = Integer.parseInt(menu_input.nextLine());
				action_num = menu_input_number;
			}
			catch (NumberFormatException e) {
				System.out.println("Not a valid number\n");
			}
			
			switch (action_num)
			{
			case 1:
			{
				display(screen1,1);
				menu(1);
				break;
			}
			case 2:
			{
				display (screen2,2);
				menu(2);
				break;
			}
			case 3:
			{
				display (screen3,3);
				menu(3);
				break;
			}
			
			default: System.out.println("No such number or action undefined");
			}
		}
			
		}
	}
	
	public static void display(String[] screen, int screenNumber) //DISPLAY, CZYLI TEKST (TO CO ZOSTANIE WYŒWIETLONE NA EKRANIE)
																	//- PODAJEMY TABLICÊ I ID (W ZASADZIE MOG£ABY BYÆ JEDNA TABLICA I TYLKO ID, ALE NO...)
	{
		print40c("TRACKER CZASU");
		switch (screenNumber)
		{
		case 1: //TEXT FOR ACTIVITIES TAB
		{
			
			screen1[0] = "|1.ACTIVITIES|";
			screen1[0] += "  ";
			screen1[0] += "|2.STATS|";
			screen1[0] += "  ";
			screen1[0] += "|3.GOALS |";
			
			screen1[1] = " ";
			screen1 [3] = "current activity: ";
				if ( useractivities1.getCurrentActivity() == null)
				{
					screen1[3] +=	"not set!";
					screen1[4] = "[4.ADD NEW ACTIVITY]";
				}
				else 
				{
					screen1[3] += useractivities1.getCurrentActivity().type;
					screen[4] = "already for " + useractivities1.getCurrentActivity().getDuration() + " seconds [4.STOP]";
				}
			
			
			screen1[6]= "statystyki z dzisiaj: ";
			
			
			screen1[7] = "---------------------"; 
			if (useractivities1.List.isEmpty())
				screen[8] = "No activities done for today!";
			else 
				for (int i = 0; i< useractivities1.List.size(); i++)
				{
					screen1[8+2*i]= useractivities1.List.get(i).type + " - for " + useractivities1.List.get(i).getDuration() + " seconds today.";
					screen1[8+2*i+1] = "---------------------";
				}
	
			break;
		}
		case 2: //TEXT FOR STATS TAB
		{
			screen2[0] = "|1.ACTIVITIES|";
			screen2[0] += "  ";
			screen2[0] += "|2.STATS|";
			screen2[0] += "  ";
			screen2[0] += "|3.GOALS |";
			
			screen2[1] = " ";
			screen2[2] = " STATS FOR TODAY";
			screen2[3] = "---------------------"; 
			
			long totalDuration = 0;
			if (useractivities1.List.isEmpty())
				screen[4] = "No activities done for today!";
			else 
			{
				for (int i = 0; i< useractivities1.List.size(); i++)
					totalDuration += useractivities1.List.get(i).getDuration();
				
				for (int i = 0; i< useractivities1.List.size(); i++)
				{
					float time_percentage = (useractivities1.List.get(i).getDuration()/(float)totalDuration)*100;
					screen2[4+2*i] = useractivities1.List.get(i).type + " - for " + time_percentage  + " percent of the measured time";
					screen2[4+2*i+1] = "---------------------";
				}
				
				
			}
			
		break;
		}
		case 3:
		{
			screen3[0] = "|1.ACTIVITIES|";
			screen3[0] += "  ";
			screen3[0] += "|2.STATS|";
			screen3[0] += "  ";
			screen3[0] += "|3.GOALS |";
		}
		default:
		}
		
		for (int i=0; i< vertical_size; i++) //PO WYPE£NIENIU TABLICY STRINGÓW ZOSTAJE ONA WYPISANA NA EKRAN
		{
				print40c(screen[i]);
		}
		

		
	}

	public static void main(String[] args) {
		


		
	while (true)
	{
		display(screen1,1);
		menu(1);
	}
		
		

	}

}
