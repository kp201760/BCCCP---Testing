package bcccp.tickets.adhoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdhocTicketDAO  implements IAdhocTicketDAO  {

	private Map<String, IAdhocTicket> currentTickets;
	private IAdhocTicketFactory adhocTicketFactory_;
	private int currentTicketNo;

	
	
	public AdhocTicketDAO(IAdhocTicketFactory adhocTicketFactory_) throws Exception {
		if (adhocTicketFactory_ == null)
			throw Exception("invalid ticket factory");
			this.adhocTicketFactory_ = adhocTicketFactory;
		currentTickets = new HashMap<>();		
	}

	
	
	@Override
	public IAdhocTicket createTicket(String carparkId) throws Exception {
		if(carparkId == null || carparkId == "")
			throw Exception ("invalid carparkId");
			IAdhocTicket ticket = adhocTicketFactory_.make(carparkId, ++currentTicketNo);
			currentTickets.put(ticket.getBarcode(), ticket);
			return ticket;	
	}
	
	
	
	@Override
	public IAdhocTicket findTicketByBarcode(String barcode) {
		return currentTickets.get(barcode);
	}	

	
	
	@Override
	public List<IAdhocTicket> getCurrentTickets() {		
		return Collections.unmodifiableList(new ArrayList<IAdhocTicket>(currentTickets.values()));
	}



}