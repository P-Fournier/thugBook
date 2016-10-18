package domaine.messages;

import java.util.Date;

public class MessageDelai extends MessageOption{
	
	private Date date ; 

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
