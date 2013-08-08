package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.ui.IPartListener;
import sk.stuba.fiit.perconik.core.Listener;

// TODO use IPartListener2 !!!
//
//org.eclipse.ui.IPartListener2
//
//
//Interface for listening to part lifecycle events. 
//
//This is a replacement for IPartListener. 
//
//As of 3.5, if the implementation of this listener also implements IPageChangedListener then it will also be notified about PageChangedEvents from parts that implement IPageChangeProvider. 
//
//This interface may be implemented by clients. 

public interface PartListener extends Listener, IPartListener
{
}
