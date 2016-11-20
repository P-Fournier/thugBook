package domaine.messages;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JLabel;

import domaine.VisiteurOption;

public class DelaiExpiration extends Option{
	
	private String dateExpiration;

	public DelaiExpiration (String dateExpiration){
		this.dateExpiration = dateExpiration;
	}
	
	@Override
	public JLabel decorate(JLabel message) {
		String dateCourante=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(new Date());
		if (dateCourante.compareTo(dateExpiration)>0){
			message.setText("Délai d'expiration dépassé");
		}
		return message;
	}

	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	
}
