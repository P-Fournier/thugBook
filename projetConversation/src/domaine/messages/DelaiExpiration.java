package domaine.messages;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JLabel;

public class DelaiExpiration extends Option{
	
	private String dateExpiration;

	public DelaiExpiration (String dateExpiration){
		this.dateExpiration = dateExpiration;
	}
	
	@Override
	public JLabel decorate(JLabel message) {
		String dateCourante=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(new Date());
		if (dateCourante.compareTo(dateExpiration)>0){
			return null;
		}else{
			return message;
		}
	}
}
