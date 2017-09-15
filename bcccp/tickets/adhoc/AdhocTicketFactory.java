<<<<<<< HEAD
package bcccp.tickets.adhoc;

public class AdhocTicketFactory implements IAdhocTicketFactory {

	@Override
	public IAdhocTicket make(String carparkId, int ticketNo) {
		String barcode = "A" + Integer.toHexString(ticketNo);
		return new AdhocTicket(carparkId, ticketNo, barcode);
	}

}
=======
package bcccp.tickets.adhoc;

public class AdhocTicketFactory implements IAdhocTicketFactory {

	@Override
	public IAdhocTicket make(String carparkId, int ticketNo) {
		if(carparkId==null) throw new Exception("Invalid Carpark ID");
		else if(ticketNo<=0) throw new Exception("Error");
		else{
		String barcode = "A" + Integer.toHexString(ticketNo);
		return new AdhocTicket(carparkId, ticketNo, barcode);
	}
	}

}
>>>>>>> Harsimranjeet-kaur
