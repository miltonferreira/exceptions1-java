package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		show("Room number: ");
		int number = sc.nextInt();
		
		show("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		
		show("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
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
			
			Date now = new Date();	//cria uma data pegando do sistema
			if(checkIn.before(now) || checkOut.before(now)) {
				showln("Error in reservation: Reservation dates for update must be future dates");
			}else if(!checkOut.after(checkIn)) {
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			}else {
				reservation.updateDates(checkIn, checkOut);
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
