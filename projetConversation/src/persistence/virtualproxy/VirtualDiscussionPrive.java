package persistence.virtualproxy;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.MessageMapper;

import domaine.messages.DiscussionPrive;
import domaine.messages.Message;

public class VirtualDiscussionPrive extends DiscussionPrive {

	public VirtualDiscussionPrive(int id) {
		super(id);
		this.messages = null;
	}
	
	@Override
	public ArrayList<Message> getMessages(){
		if (messages == null){
			try {
				messages = MessageMapper.getInstance().findByIdDiscussion(this.id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return messages;
	}

}
