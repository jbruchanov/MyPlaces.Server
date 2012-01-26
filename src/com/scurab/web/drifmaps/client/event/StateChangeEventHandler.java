package com.scurab.web.drifmaps.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter.State;

public interface StateChangeEventHandler extends EventHandler
{
	void onStateChange(State currentState);
}
