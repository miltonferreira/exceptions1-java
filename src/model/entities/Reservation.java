package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainExceptions;

public class Reservation {

	/* 		----- Coisas novas aprendidas -----
	 * 		long diff = checkOut.getTime() - checkIn.getTime();
	 *		TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	 * 
	 */

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainExceptions{
		
		if(!checkOut.after(checkIn)){
			//se saida nao for depois da entrada, mostra mensagem de erro
			throw new DomainExceptions("Check-out date must be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	//Metodo vai propagar/lançar o erro para o main, onde lá vai ser tratado o erro
	public void updateDates(Date checkIn, Date checkOut) throws DomainExceptions{
		
		Date now = new Date();	//cria uma data pegando do sistema
		//se entrada ou saida for antes da data do sistema, entra na mensagem de erro
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainExceptions("Reservation dates for update must be future dates");
		}
		if(!checkOut.after(checkIn)) {
			//se saida nao for depois da entrada, mostra mensagem de erro
			throw new DomainExceptions("Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		return "Room "
			+ roomNumber
			+ ", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out: "
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
	}
}
