package bcccp.tickets.season;

public class UsageRecordFactory implements IUsageRecordFactory {

	@Override
	public IUsageRecord make(String ticketId, long startDateTime) {
		if(ticketId==null && ticketId<=0) throw new Exception("Invalid Ticket ID");
		return new UsageRecord(ticketId, startDateTime);
	}

}
