package assignment1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		ArrayList<DesignedList<? extends Object>> allList = new ArrayList<DesignedList<? extends Object>>();
		while(true){
			System.out.print("Please select one choice:\n1.Input integer list\n2.Input string list\n3.Test list method\n4.exit\n");
			Scanner kb = new Scanner(System.in);
			int input = kb.nextInt();
			if(input == 1){
				allList.add(inputIntegerList());
			}
			else if(input == 2){
				allList.add(inputStringList());
			}
			else if(input == 3){
				displayList(allList);
				System.out.println("Which list you will test? please enter number:");
				Scanner kc = new Scanner(System.in);
				int number = kc.nextInt();
				DesignedList<? extends Object> list = allList.get(number-1);
				System.out.print("Which method you will test?\n1.isInOrder\n2.everyThree\n3.simList\n4.Exit\n");
				int inputMethod = kc.nextInt();
				switch(inputMethod){
				case 1:
					System.out.print("Is in Order?\n"+list.isInOrder()+"\n");
					break;
				case 2:
					DesignedList<? extends Object> subList = list.everyThree();
					System.out.print("subList:");
					subList.display();
					System.out.println();
					break;
				case 3:
					System.out.print("which list do you wang to comepare to?\n");
					displayList(allList);
					int inputNumber = kc.nextInt();
					DesignedList<? extends Object> comeparedList = allList.get(inputNumber-1);
					System.out.print("Is similar?\n"+list.simList(comeparedList)+"\n");
					break;
				default:
					System.out.print("Exit\n");
					break;
				}
				System.out.println();
			}
			else{
				break;
			}
		}
		System.out.print("Exit successfully\n");
	}
	
	//Input Integer list
	public static DesignedList<Integer> inputIntegerList(){
		DesignedList<Integer> list = new DesignedList<Integer>();
			System.out.println("Please enter the integer list and separate the element with comma:");
			Scanner sr = new Scanner(System.in);
			String input = (String)sr.nextLine();
			String[] inputArray = input.split(",");
			for(int i=0;i<inputArray.length;i++)
				list.add(Integer.parseInt(inputArray[i]));
			
			return list;
	}
	
	//Input String list
	public static DesignedList<String> inputStringList(){
		DesignedList<String> list = new DesignedList<String>();
		System.out.println("Please enter the String list and separate the element with comma:");
		Scanner sr = new Scanner(System.in);
		String input = (String)sr.nextLine();
		String[] inputArray = input.split(",");
		for(int i=0;i<inputArray.length;i++)
			list.add(inputArray[i]);
		
		return list;
	}
	
	//Display list
	public static void displayList(ArrayList<DesignedList<? extends Object>> allList){
		int i = 1;
		Iterator<DesignedList<? extends Object>> itr = allList.iterator();
		while(itr.hasNext()){
			DesignedList<? extends Object> list = itr.next();
			System.out.print("List"+i+":");
			list.display();
			i++;
			System.out.print("\n");
		}
	}
}
