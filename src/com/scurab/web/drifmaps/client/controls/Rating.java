/*
 * Copyright 2007-2009 Hilbrand Bouwkamp, hs@bouwkamp.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.scurab.web.drifmaps.client.controls;

import java.util.Iterator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ListenerWrapper;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Rating Widget provides an intuitive rating experience that allows users
 * to select the number of stars (or other custom images) that represents their
 * rating. At construction the initial rating, the maximum rating to allow, the
 * orientation of the stars, and custom images for the different states a star
 * can have can be set. The Rating Widget can be made read only, and the
 * behavior to have the current selection remain visible during hovering can be
 * set. Rating also supports event handling that allows custom code to run after
 * the user has rated something.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul class='css'>
 * <li>.cbg-Rating { the Rating Widget itself }</li>
 * <li>.cbg-RatingItem { the image item, e.g. the star}</li>
 * </ul>
 * 
 * <h3>Default Rating Images</h3> When no custom image is used the default
 * images are used. The base image (original name star.png) is from Silk Icons,
 * <a href="http://www.famfamfam.com/lab/icons/silk/">
 * http://www.famfamfam.com/lab/icons/silk/</a> a great set of icons licensed
 * under a Creative Commons Attribution 2.5 License. Available images:
 * <ul>
 * <li><code>cbg-star.png</code> the selected item.</li>
 * <li><code>cbg-stardeselected.png</code> the unselected item.</li>
 * <li><code>cbg-starhover.png</code> the item hovered.</li>
 * </ul>
 * The <code>cbg-stardeselected.png</code> was created from the base image by
 * setting lightness to 80 and <code>cbg-starhover.png</code> was created by
 * setting lightness to -80.
 */
@SuppressWarnings("deprecation")
public class Rating extends FocusPanel implements HasValue<Integer>, HasMouseOutHandlers, HasMouseOverHandlers
{
	/*
	 * Copyright 2007-2009 Hilbrand Bouwkamp, hs@bouwkamp.com
	 * 
	 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
	 * use this file except in compliance with the License. You may obtain a copy of
	 * the License at
	 * 
	 * http://www.apache.org/licenses/LICENSE-2.0
	 * 
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
	 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
	 * License for the specific language governing permissions and limitations under
	 * the License.
	 */
	/**
	 * @deprecated Use the following handlers:
	 * For {@link #onSelect(Rating, int)} use {@link ValueChangeHandler}.
	 * For {@link #onHover(Rating, int)} use {@link MouseOverHandler} and
	 * {@link MouseOutHandler}.
	 * 
	 * No new Handler was introduced to replace the onHover method, instead it was
	 * implemented using 2 native mouse event handlers. This was just a design
	 * decision to limit the number of handlers. 
	 * 
	 * Event listener interface for Rating widget events.
	 */
	@Deprecated
	public interface RatingListener extends EventListener {

	  /**
	   * Called when user hovers over a {@link Rating} item with the rating
	   * index. If the index is -1 it means the hover left the Rating widget, this 
	   * can be used to remove anything that was displayed on hover. Index starts
	   * with 1 for lowest value.
	   * 
	   * @param sender {@link Rating} being hovered
	   * @param index Index of hovered item or -1 if left {@link Rating}
	   */
	  @Deprecated
	  void onHover(Rating sender, int index);
	  
	  /**
	   * Called when user selects a rating on a {@link Rating}. The selection
	   * is in the index. The index starts with 1 for lowest value.
	   * 
	   * @param sender {@link Rating} being selected
	   * @param index Index of selected item
	   */
	  @Deprecated
	  void onSelect(Rating sender, int index);
	  
	}

	/**
	 * Orientation of Rating widget Horizontal from Left to Right (default).
	 * With lowest rating left and highest rating right.
	 */
	public final static int LTR = 1;
	/**
	 * Orientation of Rating widget Horizontal from Right to Left. With highest
	 * rating left and lowest rating right.
	 */
	public final static int RTL = 2;
	/**
	 * Orientation of Rating widget Vertical from Top to Bottom. With lowest
	 * rating at top and highest rating at bottom.
	 */
	public final static int TTB = 4;
	/**
	 * Orientation of Rating widget Vertical from Top to Bottom. With lowest
	 * rating at top and highest rating at bottom.
	 */
	public final static int BTT = 8;

	private final FlowPanel panel;

	private Hidden input = null;

	private boolean readOnly = false;

	private boolean selectedVisible;

	private Integer currentRating;

	private Integer hoverValue = -1;

	/**
	 * Class for the individual rating items. The image is placed as background
	 * image.
	 */
	private class RatingItem extends Widget
	{

		private final int index;

		private final String selectedImg;

		private final String deselectedImg;

		private final String hoverImg;

		public RatingItem(int index, int orientation, String selectedImg, String deselectedImg, String hoverImg, int width, int height)
		{
			final SpanElement star = Document.get().createSpanElement();

			setElement(star);
			sinkEvents(Event.MOUSEEVENTS | Event.ONCLICK);
			this.index = index;
			this.deselectedImg = "url(" + deselectedImg + ")";
			this.selectedImg = "url(" + selectedImg + ")";
			this.hoverImg = "".equals(hoverImg) ? this.selectedImg : "url(" + hoverImg + ")";
			if ((orientation & (LTR | RTL)) > 0)
			{
				star.getStyle().setProperty(RatingCSS.A.FLOAT, RatingCSS.V.FLOAT.LEFT);
			}
			setStyleName("cbg-RatingItem");
			getElement().getStyle().setPropertyPx(RatingCSS.A.WIDTH, width);
			getElement().getStyle().setPropertyPx(RatingCSS.A.HEIGHT, height);
			star.getStyle().setPropertyPx(RatingCSS.A.FONT_SIZE, 0);
			star.getStyle().setProperty(RatingCSS.A.BACKGROUND_REPEAT, RatingCSS.V.BACKGROUND_REPEAT.NO_REPEAT);
			star.getStyle().setProperty(RatingCSS.A.DISPLAY, RatingCSS.V.DISPLAY.BLOCK);
		}

		public void deselect()
		{
			setImage(deselectedImg);
		}

		public int getIndex()
		{
			return index;
		}

		public void hover()
		{
			setImage(hoverImg);
		}

		@Override
		public void onBrowserEvent(Event event)
		{
			if (!readOnly)
			{
				switch (event.getTypeInt())
				{
				case Event.ONMOUSEOVER:
					hoverValue = getIndex();
					setItemsState(hoverValue);
					DomEvent.fireNativeEvent(event, Rating.this);
					break;
				case Event.ONMOUSEOUT:
					hoverValue = -1;
					setItemsState(hoverValue);
					DomEvent.fireNativeEvent(event, Rating.this);
					break;
				case Event.ONCLICK:
					setValue(getIndex());
					break;
				default:
					super.onBrowserEvent(event);
				}
			}
		}

		public void select()
		{
			setImage(selectedImg);
		}

		@Deprecated
		private Rating getRatingWidget()
		{
			return Rating.this;
		}

		private void setImage(String img)
		{
			getElement().getStyle().setProperty(RatingCSS.A.BACKGROUND_IMAGE, img);
		}
	}

	@Deprecated
	private static class WrappedRatingListener extends ListenerWrapper<RatingListener> implements ValueChangeHandler<Integer>, MouseOutHandler,
			MouseOverHandler
	{

		@Deprecated
		public static void remove(Widget eventSource, RatingListener listener)
		{
//			baseRemove(eventSource, listener, ValueChangeEvent.getType());
		}

		public WrappedRatingListener(RatingListener listener)
		{
			super(listener);
		}

		@Override
		public void onValueChange(ValueChangeEvent<Integer> event)
		{
			final Rating rating = (Rating) getSource(event);
			getListener().onSelect(rating, rating.currentRating.intValue());
		}

		@Override
		public void onMouseOut(MouseOutEvent event)
		{
			getListener().onHover((Rating) getSource(event), -1);
		}

		@Override
		public void onMouseOver(MouseOverEvent event)
		{
			final RatingItem ratingItem = (RatingItem) getSource(event);
			getListener().onHover(ratingItem.getRatingWidget(), ratingItem.getIndex());
		}
	}

	/**
	 * Default constructor. Creates a 5 star LTR rating widget.
	 */
	public Rating()
	{
		this(0, 5);
	}

	/**
	 * Creates a Horizontal Left to Right (LTR) widget which contains the number
	 * of selections <code>maxRating</code> with initial value
	 * <code>initRating</code> and uses the default rating icons.
	 * 
	 * @param initRating
	 *            Initial value of the rating
	 * @param maxRating
	 *            Maximum rating value
	 */
	public Rating(int initRating, int maxRating)
	{
		this(initRating, maxRating, LTR);
	}

	/**
	 * Creates a custom oriented rating widget which contains the number of
	 * selections <code>maxRating</code> with initial value
	 * <code>initRating</code> and uses the default rating icons.
	 * 
	 * @param initRating
	 *            Initial value of the rating
	 * @param maxRating
	 *            Maximum rating value
	 * @param orientation
	 *            Orientation of widget: {@link #LTR}, {@link #RTL},
	 *            {@link #TTB} or {@link #TTB}.
	 */
	
	private static String imgPrefix = "/icons/";
	public Rating(int initRating, int maxRating, int orientation)
	{
		this(initRating, maxRating, orientation, imgPrefix + "cbg-star.png", imgPrefix + "cbg-stardeselected.png", imgPrefix + "cbg-starhover.png", 16, 16);
	}

	/**
	 * Creates a custom oriented rating widget which contains the number of
	 * selections <code>maxRating</code> with initial value
	 * <code>initRating</code> and uses the specified images as indicators, with
	 * the given <code>width</code> and <code>height</code>, which should apply
	 * for all three images.
	 * 
	 * @param initRating
	 *            Initial value of the rating
	 * @param maxRating
	 *            Maximum rating value
	 * @param orientation
	 *            Orientation of widget: {@link #LTR}, {@link #RTL},
	 *            {@link #TTB} or {@link #TTB}.
	 * @param selectedImg
	 *            Image on Rating item when selected
	 * @param deselectedImg
	 *            Image on Rating item when not selected
	 * @param hoverImg
	 *            Image on selected Rating item when hovered, when an empty
	 *            string <code>""</code> is passed the behavior of the widget is
	 *            to hide the selection on hover and follow the hovering over
	 *            the widget
	 * @param width
	 *            Width of image in px (applies to all three images)
	 * @param height
	 *            Height of image in px (applies to all three images)
	 */
	public Rating(int initRating, int maxRating, int orientation, String selectedImg, String deselectedImg, String hoverImg, int width, int height)
	{
		super(new FlowPanel());
		
		panel = (FlowPanel) getWidget();
		currentRating = new Integer(initRating);
		selectedVisible = !"".equals(hoverImg);
		setStyleName("cbg-Rating");
		panel.setWidth(width * maxRating + "px");
		getElement().getStyle().setProperty(RatingCSS.A.CURSOR, RatingCSS.V.CURSOR.POINTER);
		final int offset = (orientation & (LTR | TTB)) > 0 ? 1 : maxRating;
		final int mlt = (orientation & (LTR | TTB)) > 0 ? 1 : -1;

		for (int i = 0; i < maxRating; ++i)
		{
			panel.add(new RatingItem(offset + (mlt * i), orientation, selectedImg, deselectedImg, hoverImg, width, height));
		}
		setItemsState(-1);
	}

	/**
	 * Adds a hidden <code>input</code> tag to this widget with the attribute
	 * <code>name</code>. Adding an input tag makes it possible to use this
	 * widget in a <code>form</code> context. The <code>name</code> is the name
	 * as visible within the <code>form</code> context.
	 * 
	 * @param name
	 *            Name for the input property
	 */
	public void addInputTag(String name)
	{
		input = new Hidden(name, "" + currentRating);
		panel.insert(input, 0);
	}

	/**
	 * @deprecated Use {@link #addValueChangeHandler(ValueChangeHandler)}
	 *             instead
	 */
	@Deprecated
	public void addRatingListerner(RatingListener listener)
	{
		addValueChangeHandler(new WrappedRatingListener(listener));
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler)
	{
		return addHandler(handler, ValueChangeEvent.getType());
	}

	/**
	 * @deprecated Use {@link #getValue()}. Returns the current rating.
	 * 
	 * @return Current rating
	 */
	@Deprecated
	public int getRating()
	{
		return currentRating.intValue();
	}

	/**
	 * Returns the rating corresponding with image the user hovers over. In case
	 * no hovering takes place -1 is returned.
	 * 
	 * @return The rating corresponding with image the user hovers over
	 */
	public Integer getHoverValue()
	{
		return hoverValue;
	}

	/**
	 * Returns the current rating.
	 * 
	 * @return Current rating
	 */
	@Override
	public Integer getValue()
	{
		return currentRating;
	}

	/**
	 * Returns <code>true</code> if the widget is read only.
	 * 
	 * @return <code>true</code> if the widget is read only
	 */
	public boolean isReadOnly()
	{
		return readOnly;
	}

	@Override
	public void onBrowserEvent(Event event)
	{
		if (!readOnly)
		{
			super.onBrowserEvent(event);
		}
	}

	/**
	 * @deprecated Use the {@link HandlerRegistration#removeHandler()} method on
	 *             the object returned by
	 *             {@link #addValueChangeHandler(ValueChangeHandler)} instead
	 */
	@Deprecated
	public void removeRatingListerner(RatingListener listener)
	{
		WrappedRatingListener.remove(this, listener);
	}

	/**
	 * @deprecated Use {@link #setValue(Integer)}
	 */
	@Deprecated
	public void setRating(int rating)
	{
		setValue(rating);
	}

	/**
	 * Sets the rating selection to read only if argument is <code>true</code>,
	 * and if <code>false</code> enable setting the rating.
	 * 
	 * @param readOnly
	 *            If <code>true</code> make read only
	 */
	public void setReadOnly(boolean readOnly)
	{
		this.readOnly = readOnly;
		if (readOnly)
		{
			setItemsState(-1);
		}
		getElement().getStyle().setProperty(RatingCSS.A.CURSOR, readOnly ? "" : RatingCSS.V.CURSOR.POINTER);
	}

	/**
	 * This method changes the hover behavior of the widget. When
	 * <code>visible</code> is set to <code>true</code> when a user hovers over
	 * the widget the current selection remains visible and the rating hovered
	 * by the user is shown using the hover images (if present, otherwise the
	 * same behavior as setting <code>false</code>. When <code>visible</code> is
	 * set to <code>false</code> the current selection is not shown when the
	 * user hovers over the widget, instead the selection follows the hover. If
	 * a hover image is set this image is shown when hovering else the selection
	 * image is shown.
	 * 
	 * @param visible
	 *            If <code>true</code> current selection remains visible when
	 *            user hovers over widget
	 */
	public void setSelectedVisibileOnHover(boolean visible)
	{
		this.selectedVisible = visible;
	}

	/**
	 * Sets the titles on all rating items. The title will be visible to the
	 * user when the user hovers over a particular selection item. The String
	 * array must contain as many values as the number of possible ratings.
	 * 
	 * @param titles
	 *            Array of titles
	 */
	public void setTitles(String[] titles)
	{
		Iterator<Widget> i = panel.iterator();
		int idx = 0;

		if (null != input)
		{
			i.next(); // skip the hidden input tag
		}
		while (i.hasNext())
		{
			RatingItem sw = (RatingItem) i.next();

			sw.setTitle(titles[idx++]);
		}
	}

	/**
	 * Sets the rating, also when the widget is read only. If the rating set is
	 * out of range the widget will still use that value but show or empty (if
	 * the rating set is below zero) or full (if the rating set is above total
	 * number of items).
	 * 
	 * @param value
	 *            New rating value to set
	 */
	@Override
	public void setValue(Integer value)
	{
		setValue(value, true);
	}

	@Override
	public void setValue(Integer value, boolean fireEvents)
	{
		if(value == null)
			value = 0;
		final Integer oldValue = currentRating;
		currentRating = value;
		if (null != input)
		{
			input.setValue("" + value);
		}
		setItemsState(-1);
		if (fireEvents)
		{
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}
	}

	/**
	 * Private method sets the state of each rating item.
	 * <code>hoverIndex</code> is the item the user hovers over. In case the
	 * value is <code>-1</code> the hover state will not be shown. This is used
	 * to reset the hover state.
	 * 
	 * @param hoverIndex
	 *            Current item hovered or <code>-1</code> if no hover
	 */
	private void setItemsState(int hoverIndex)
	{
		Iterator<Widget> i = panel.iterator();

		if (null != input)
		{
			i.next(); // skip the hidden input field if present
		}
		while (i.hasNext())
		{
			RatingItem sw = (RatingItem) i.next();

			if (sw.getIndex() <= hoverIndex)
			{
				sw.hover();
			}
			else if ((hoverIndex == -1 || selectedVisible) && sw.getIndex() <= currentRating)
			{
				sw.select();
			}
			else
			{
				sw.deselect();
			}
		}
	}
}
