package bcccp.tickets.adhoc;

public class AdhocTicketFactory implements IAdhocTicketFactory {

	@Override
	public IAdhocTicket make(String carparkId, int ticketNo) throws Exception {
		if(carparkId == null || carparkId == "")
			throw Exception("invalid carparkId");
		if (ticketNo <= 0) 
			throw Exception("invalid ticketNo");
	
		String barcode = "A" + Integer.toHexString(ticketNo);
		return new AdhocTicket(carparkId, ticketNo, barcode);
	}

}
