package application;

import javafx.event.Event;
import javafx.event.EventType;

@SuppressWarnings("serial")
public class OnShowEvent extends Event {

	public static final EventType<OnShowEvent> SHOW_EVENT = new EventType<OnShowEvent>(ANY); 
	
	public OnShowEvent() {
		super(SHOW_EVENT);
	}

}
