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

import com.google.gwt.dom.client.Element;

/**
 * Native implementation associated with {@link org.RatingCSS.gwt.user.client.CSS}.
 */
public class RatingCSSImpl {

  public String getFloatAttribute() {
    return "cssFloat";
  }

  public void setInlineBlock(Element e) {
    e.getStyle().setProperty("display", "inline-block");
  }

  public void setOpacity(Element e, float opacity) {
    e.getStyle().setProperty("opacity", "" + opacity);
  }

  public void setSelectable(Element e, boolean selectable) {
    e.getStyle().setProperty("userSelect", selectable ? "" : "none");
    e.setPropertyString("unselectable", selectable ? "" : "on");
  }
}
