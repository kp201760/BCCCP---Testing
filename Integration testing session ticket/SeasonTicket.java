package bcccp.tickets.season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeasonTicket implements ISeasonTicket {
	
	private List<IUsageRecord> usages;
	private IUsageRecord currentUsage = null;
	
	private String ticketId;
	private String carparkId;
	private long startValidPeriod;
	private long endValidPeriod;
	
	public SeasonTicket (String ticketId, String carparkId, long startValidPeriod, long endValidPeriod) throws Exception{
		if(ticketId==null)
			throw new Exception("invalid ticketId")
			this.ticketId=ticketId;
		if(carparkId == null)
			throw new Exception("invalid carparkId")
			this.carparkId = carparkId;
		if(startValidPeriod<= 0)
			throw new Exception("invalid Start Date")
			this.startValidPeriod = startValidPeriod;
		if(endValidPeriod<= startValidPeriod)
			throw new Exception("invalid End Date")
			this.endValidPeriod = endValidPeriod;
		
		usages = new ArrayList<IUsageRecord>();
	}


	@Override
	public String getId() {
		return ticketId;
	}


	@Override
	public String getCarparkId() {
		return carparkId;
	}


	@Override
	public long getStartValidPeriod() {
		return startValidPeriod;
	}


	@Override
	public long getEndValidPeriod() {
		return endValidPeriod;
	}


	@Override
	public boolean inUse() {
		if(currentUsage != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	@Override
	public void recordUsage(IUsageRecord record) throws Exception{
		currentUsage = record;
		if(currentUsage==null)
			throw new Exception("invalid user record")
		if (!usages.contains(currentUsage) ) {
			usages.add(currentUsage);
		}
		
	}


	@Override
	public IUsageRecord getCurrentUsageRecord() {
		
		if(currentUsage!=null)
		{
			return currentUsage;
		}
		else
		{
			return null;
		}
	}


	@Override
	public List<IUsageRecord> getUsageRecords() {
		if(usages!=null)
		{
			return Collections.unmodifiableList(usages);
		}
		else
		{
			return Collections.unmodifiableList();
		}
	}


	@Override
	public void endUsage(long dateTime) {
		currentUsage.finalise(dateTime);
		if (currentUsage == null) throw new RuntimeException("SeasonTicket.endUsage : ticket is not in use");
		if(dateTime<= startValidPeriod)
			throw new RuntimeException("invalid End Date");		
	}


	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Carpark    : " + carparkId + "\n" +
		       "Ticket No  : " + ticketId + "\n" );
		for (IUsageRecord usage : usages) {
			builder.append(usage.toString() + "\n");
		}
		return builder.toString();
	}


}
