package com.scurab.web.drifmaps.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter.State;

public class StateChangeEvent extends GwtEvent<StateChangeEventHandler>
{

	public static Type<StateChangeEventHandler> TYPE = new Type<StateChangeEventHandler>();
	private State mState = null;
	
	public StateChangeEvent(State newState) {
		mState = newState;
	}

	public State getState()
	{
		return mState;
	}

	@Override
	protected void dispatch(StateChangeEventHandler handler)
	{
		handler.onStateChange(mState);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<StateChangeEventHandler> getAssociatedType()
	{
		return TYPE;
	}

}
