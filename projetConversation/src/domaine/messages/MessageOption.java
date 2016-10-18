package domaine.messages;

public abstract class MessageOption {
	
	private Message messageInside ; 
	
	public abstract void delivrer() ;

	public Message getMessageInside() {
		return messageInside;
	}

	public void setMessageInside(Message messageInside) {
		this.messageInside = messageInside;
	} 
}
