package domaine.messages;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JLabel;

public class DelaiExpiration extends Option{
	private String dateExpiration;

	@Override
	public JLabel decorate(JLabel message) {
		String dateCourante=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.FRANCE).format(new Date());
		this.dateExpiration = dateExpiration.substring(0, 19);
		dateCourante = dateCourante.substring(0,19);
		if (dateCourante.compareTo(dateExpiration)>0){
			return null;
		}else{
			return message;
		}
	}
}
