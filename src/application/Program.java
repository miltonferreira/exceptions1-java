package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

public static void main(String[] args) throws ParseException {
		
		/* ----- Coisas novas aprendidas -----
		 * 		checkIn.before(now) e checkIn.after(now)
		 * 
		 */
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		show("Room number: ");
		int number = sc.nextInt();
		
		show("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		
		show("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		//Se saida nao for depois de entrada, entra na mensagem de erro 
		if(!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		}else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			showln("Reservation: " + reservation);
			
			System.out.println();
			showln("Enter data to update the reservation:");
			
			show("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			
			show("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			//mostra a entrada e saida atualizada
			String error = reservation.updateDates(checkIn, checkOut);
			
			if(error != null) {
				showln("Error in reservation: " + error);
			}else {
				showln("Reservation: " + reservation);
			}
			
		}
			
		sc.close();
	}
	
	public static void show(String str) {
		System.out.print(str);
	}
	public static void showln(String str) {
		System.out.println(str);
	}

}
