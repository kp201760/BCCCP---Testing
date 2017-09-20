<<<<<<< HEAD
package bcccp.tickets.season;

public interface ISeasonTicketDAO {
	
	public void registerTicket(ISeasonTicket ticket);
	public void deregisterTicket(ISeasonTicket ticket);
	public int getNumberOfTickets();
	
	public ISeasonTicket findTicketById(String ticketId);
	public void recordTicketEntry(String ticketId );
	public void recordTicketExit(String ticketId);
}
=======
package bcccp.tickets.season;

public interface ISeasonTicketDAO {
	
	public void registerTicket(ISeasonTicket ticket);
	public void deregisterTicket(ISeasonTicket ticket);
	public int getNumberOfTickets();
	
	public ISeasonTicket findTicketById(String ticketId);
	public void recordTicketEntry(String ticketId );
	public void recordTicketExit(String ticketId);
}
>>>>>>> f1e9947ccf057e1bb0fc48ba9487d1d32f150dfe
