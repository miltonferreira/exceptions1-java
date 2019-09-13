package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainExceptions;

public class Program {

	public static void main(String[] args) {

		/*
		 * ----- Coisas novas aprendidas ----- checkIn.before(now) e checkIn.after(now)
		 * 
		 */

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			
			show("Room number: ");
			int number = sc.nextInt();

			show("Check-in date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next());	//pode acontecer uma excecao

			show("Check-out date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());	//pode acontecer uma excecao

			Reservation reservation = new Reservation(number, checkIn, checkOut);
			showln("Reservation: " + reservation);

			System.out.println();
			showln("Enter data to update the reservation:");

			show("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());			//pode acontecer uma excecao

			show("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());		//pode acontecer uma excecao

			// mostra a entrada e saida atualizada
			reservation.updateDates(checkIn, checkOut);

			showln("Reservation: " + reservation);

		} catch (ParseException e) {
			//pode acontecer uma excecao ao usar parse
			showln("Invalid date format");
		}catch (DomainExceptions e) {
			//caso o passe uma data errada
			showln("Error in reservation: " + e.getMessage());	//mostra o tipo de error em string
		}catch (RuntimeException e) {
			showln("Unexpected error");
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
