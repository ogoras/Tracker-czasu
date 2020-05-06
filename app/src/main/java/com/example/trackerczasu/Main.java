package text_interface;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.Savepoint;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static UserActivities useractivities1 = new UserActivities();
	
	public static int vertical_size = 25; // "WYSOKOŒÆ" NASZEGO "WYŒWIETLACZA W TELEFONIE"
	
	static String [] screen1 = new String[vertical_size]; //3 TABLICE STRINGÓW NA 3 ZAK£ADKI (WYSTARCZY£ABY JEDNA THOUGH)
	static String [] screen2 = new String[vertical_size];
	static String [] screen3 = new String[vertical_size];
	
	public static void saveData()
	{
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(useractivities1);
		
		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadData ()
	{
		ArrayList<Object> deserialized = new ArrayList<Object>();
		
		try {
			FileInputStream fileIn =  new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserialized = (ArrayList<Object>) in.readObject();
			in.close();
			fileIn.close();
			
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		UserActivities retrievedUserActivities = (UserActivities) deserialized.get(0);
		useractivities1 = retrievedUserActivities;
		
	}
	
	
	public static int getIntegerValue(int min, int max)
	{
		Scanner menu_input = new Scanner(System.in);
		int input = 0;
		try {
			input = Integer.parseInt(menu_input.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("Not a valid number\n");
			System.out.printf("Enter a number between "+ min +" and "+ max + ": ");
			return getIntegerValue(min, max);
		}
		if ((input<min)||(input>max))
		{
			System.out.printf("Enter a number between "+ min +" and "+ max + ": ");
			return getIntegerValue(min, max);
		}
		else return input;
			
	}
	
	public static void activityEdit(int activity_number_chosen)
	{
		System.out.println("What to do? 1. edit type 2.edit time 3.delete activity 4.split into two 5.add tag 6.add comment");

		
		switch (getIntegerValue(1, 6))
		{
		case 1:
		{
		
		break;}
			
		case 2:{
			
		break;}
			
			
		case 3:
		{
			System.out.printf(" Are you sure to delete activity " + useractivities1.List.get(activity_number_chosen).type+
					" done for "+ useractivities1.List.get(activity_number_chosen).getDuration() + " seconds? (1 - yes )");
			if (getIntegerValue(-1000, 1) == 1)
				useractivities1.deleteActivity(useractivities1.List.get(activity_number_chosen));
			
		break;
		}
			
			
		case 4:{
			
		break;}
			
			
		case 5:{
			
		break;}

			
		case 6:{
			
		break;}

			
		case 7:{
			
		break;}

			
		default:
		{
			break;
		}
		
	}
		saveData();
	}
	
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
				saveData();
				display(screen1,1);
				menu(1);
				
				}
				else
				{
					useractivities1.endActivity(useractivities1.getCurrentActivity());
					saveData();
					display(screen1,1);
					menu(1);
					
				}
				break;
			}
			
			case 5:
			{
				if (useractivities1.List.size() < 1)
					System.out.println("Nothing to edit");
				else
				{
					System.out.printf("Which one to edit?");
					for (int i=0; i<useractivities1.size; i++)
					{
						System.out.printf(" "+ (i+1) + ". " + useractivities1.List.get(i).type);
					}
					
					menu_input = new Scanner(System.in);
					menu_input_number = 0;		
					action_num = 0;
					try {
						menu_input_number = Integer.parseInt(menu_input.nextLine());
						action_num = menu_input_number;
					}
					catch (NumberFormatException e) {
						System.out.println("Not a valid number\n");
					}
					
					int activity_number_chosen = action_num - 1;
					
					activityEdit(activity_number_chosen);
					
					display(screen1,1);
					menu(1);
					
					
					
				
				}
				break;
			}
			case 6:
			{
				System.out.println("Saving data");
				saveData();
				break;
			}
			case 7:
			{
				System.out.println("loading data...");
				loadData();
				display(screen1,1);
				menu(1);
				break;				
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
		loadData();
		

			
		switch (screenNumber)
		{
		case 1: //TEXT FOR ACTIVITIES TAB
		{
			for (int i=0; i< screen1.length; i++)
			screen1[i] = " ";
			
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
			for (int i=0; i< screen2.length; i++)
				screen2[i] = " ";
			
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
		{	for (int i=0; i< screen3.length; i++)
			screen3[i] = " ";
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
