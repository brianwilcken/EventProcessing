package eventsregistryapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndexedEventSourcesQueryParams extends IndexedDocumentsQuery {
	private String[] eventUris;
	private int[] numDaysPrevious;
	private String[] startDate;
	private String[] endDate;
	private int[] rows;
	
	public String getQuery() {
		return getTimeRangeQuery("articleDate", startDate, endDate, numDaysPrevious);
	}
	
	public String[] getFilterQueries() {
		List<String> fqs = new ArrayList<String>();

		fqs.add("-eventState:*"); //ensure that only event sources are returned, as these objects will not have the eventState parameter
		fqs.add(getFilterQuery("eventUri", eventUris));
		
		return fqs.toArray(new String[fqs.size()]);
	}
	
	public int getQueryRows() {
		int rows;
		if (getRows() != null && getRows().length > 0) {
			rows = getRows()[0];
		} else {
			rows = Integer.MAX_VALUE;
		}
		return rows;
	}
	
	public String[] getStartDate() {
		return startDate;
	}
	public void setStartDate(String[] startDate) {
		this.startDate = startDate;
	}
	public String[] getEndDate() {
		return endDate;
	}
	public void setEndDate(String[] endDate) {
		this.endDate = endDate;
	}

	public int[] getRows() {
		return rows;
	}

	public void setRows(int[] rows) {
		this.rows = rows;
	}

	public String[] getEventUris() {
		return eventUris;
	}

	public void setEventUris(String[] eventUris) {
		this.eventUris = eventUris;
	}
	public int[] getNumDaysPrevious() {
		return numDaysPrevious;
	}
	public void setNumDaysPrevious(int[] numDaysPrevious) {
		this.numDaysPrevious = numDaysPrevious;
	}
}
