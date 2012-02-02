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
 *
 * This class is based on the W3C document 'Cascading Style Sheets Level 2
 * Revision 1 (CSS 2.1) Specification, W3C Candidate Recommendation 19 July
 * 2007'. Copyright &copy; 1994-2007 W3C (Massachusetts Institute of Technology,
 * European Research Consortium for Informatics and Mathematics, Keio
 * University), All Rights Reserved.
 * <a href="http://www.w3.org/TR/2007/CR-CSS21-20070719">
 *     Cascading Style Sheets Level 2 Revision 1 (CSS 2.1) Specification</a>
 */
package com.scurab.web.drifmaps.client.controls;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>Defines a set of
 * <a href="http://www.w3.org/TR/CSS21/">CSS attributes, values</a> and
 * colors as a typesafe enumeration.
 *
 * <p>All static identifiers will be in-lined by the GWT compiler and unused
 * identifiers will be ignored. Which means no overhead is created by using
 * this class.
 *
 * <p>This class takes care of browser incompatibilities where possible, like
 * {@link RatingCSS.A#FLOAT} or otherwise provides information regarding other
 * solutions.
 *
 * <p>The support for browser incompatibilities is probably not complete, if
 * you find any issue please report.
 *
 * <p>This class is based on the W3C document 'Cascading Style Sheets Level 2
 * Revision 1 (CSS 2.1) Specification, W3C Candidate Recommendation 19 July
 * 2007'. Copyright &copy; 1994-2007 W3C (Massachusetts Institute of Technology,
 * European Research Consortium for Informatics and Mathematics, Keio
 * University), All Rights Reserved.
 * @see <a href="http://www.w3.org/TR/2007/CR-CSS21-20070719">Cascading Style Sheets Level 2 Revision 1 (CSS 2.1) Specification</a>
 * @see <a href="http://www.w3.org/TR/2007/CR-CSS21-20070719/propidx.html">W3C Appendix F. Full property table</a>
 */
public class RatingCSS {

  private static final RatingCSSImpl impl = GWT.create(RatingCSSImpl.class);

  /**
   * This class contains all CSS Attributes.
   */
  public static final class A {

    /**
     * AZIMUTH
     * <p>CSS: 'azimuth', javaScript: 'azimuth'
     * <br>Values: &lt;angle&gt; | [[ left-side | far-left | left | center-left
     * | center | center-right | right | far-right | right-side ] || behind ] |
     * leftwards | rightwards | inherit
     * <br>Initial value: center
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String AZIMUTH = "azimuth";

    /**
     * BACKGROUND_ATTACHMENT
     * <p>CSS: 'background-attachment', javaScript: 'backgroundAttachment'
     * <br>Values: scroll | fixed | inherit
     * <br>Initial value: scroll
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BACKGROUND_ATTACHMENT = "backgroundAttachment";

    /**
     * BACKGROUND_COLOR
     * <p>CSS: 'background-color', javaScript: 'backgroundColor'
     * <br>Values: &lt;color&gt; | transparent | inherit
     * <br>Initial value: transparent
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BACKGROUND_COLOR = "backgroundColor";

    /**
     * BACKGROUND_IMAGE
     * <p>CSS: 'background-image', javaScript: 'backgroundImage'
     * <br>Values: &lt;uri&gt; | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BACKGROUND_IMAGE = "backgroundImage";

    /**
     * BACKGROUND_POSITION
     * <p>CSS: 'background-position', javaScript: 'backgroundPosition'
     * <br>Values: [ [ &lt;percentage&gt; | &lt;length&gt; | left | center |
     * right ] [ &lt;percentage&gt; | &lt;length&gt; | top | center | bottom ]?
     * ] | [ [ left | center | right ] || [ top | center | bottom ] ] | inherit
     * <br>Initial value: 0% 0%
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Percentages: refer to the size of the box itself
     * <br>Media groups: visual
     */
    public static final String BACKGROUND_POSITION = "backgroundPosition";

    /**
     * BACKGROUND_REPEAT
     * <p>CSS: 'background-repeat', javaScript: 'backgroundRepeat'
     * <br>Values: repeat | repeat-x | repeat-y | no-repeat | inherit
     * <br>Initial value: repeat
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BACKGROUND_REPEAT = "backgroundRepeat";

    /**
     * BACKGROUND
     * <p>CSS: 'background', javaScript: 'background'
     * <br>Values: ['background-color' || 'background-image' ||
     * 'background-repeat' || 'background-attachment' || 'background-position']
     *  | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * Percentages: allowed on 'background-position'
     * <br>Media groups: visual
     */
    public static final String BACKGROUND = "background";

    /**
     * BORDER_COLLAPSE
     * <p>CSS: 'border-collapse', javaScript: 'borderCollapse'
     * <br>Values: collapse | separate | inherit
     * <br>Initial value: separate
     * <br>Applies to: 'table' and 'inline-table' elements
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String BORDER_COLLAPSE = "borderCollapse";

    /**
     * BORDER_COLOR
     * <p>CSS: 'border-color', javaScript: 'borderColor'
     * <br>Values: [ &lt;color&gt; | transparent ]{1,4} | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_COLOR = "borderColor";

    /**
     * BORDER_SPACING
     * <p>CSS: 'border-spacing', javaScript: 'borderSpacing'
     * <br>Values: &lt;length&gt; &lt;length&gt;? | inherit
     * <br>Initial value: 0
     * <br>Applies to: 'table' and 'inline-table' elements
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String BORDER_SPACING = "borderSpacing";

    /**
     * BORDER_STYLE
     * <p>CSS: 'border-style', javaScript: 'borderStyle'
     * <br>Values: [{none | hidden | dotted | dashed | solid | double | groove |
     * ridge | inset | outset]{1,4} | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_STYLE = "borderStyle";

    /**
     * BORDER_TOP
     * <p>CSS: 'border-top', javaScript: 'borderTop'
     * <br>Values: [ &lt;border-width&gt; || &lt;border-style&gt; ||
     * 'border-top-color' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_TOP = "borderTop";

    /**
     * BORDER_RIGHT
     * <p>CSS: 'border-right', javaScript: 'borderRight'
     * <br>Values: [ &lt;border-width&gt; || &lt;border-style&gt; ||
     * 'border-top-color' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_RIGHT = "borderRight";

    /**
     * BORDER_BOTTOM
     * <p>CSS: 'border-bottom', javaScript: 'borderBottom'
     * <br>Values: [ &lt;border-width&gt; || &lt;border-style&gt; ||
     * 'border-top-color' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_BOTTOM = "borderBottom";

    /**
     * BORDER_LEFT
     * <p>CSS: 'border-left', javaScript: 'borderLeft'
     * <br>Values: [ &lt;border-width&gt; || &lt;border-style&gt; ||
     * 'border-top-color' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_LEFT = "borderLeft";

    /**
     * BORDER_TOP_COLOR
     * <p>CSS: 'border-top-color', javaScript: 'borderTopColor'
     * <br>Values: &lt;color&gt; | transparent | inherit
     * <br>Initial value: the value of the 'color' property
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_TOP_COLOR = "borderTopColor";

    /**
     * BORDER_RIGHT_COLOR
     * <p>CSS: 'border-right-color', javaScript: 'borderRightColor'
     * <br>Values: &lt;color&gt; | transparent | inherit
     * <br>Initial value: the value of the 'color' property
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_RIGHT_COLOR = "borderRightColor";

    /**
     * BORDER_BOTTOM_COLOR
     * <p>CSS: 'border-bottom-color', javaScript: 'borderBottomColor'
     * <br>Values: &lt;color&gt; | transparent | inherit
     * <br>Initial value: the value of the 'color' property
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_BOTTOM_COLOR = "borderBottomColor";

    /**
     * BORDER_LEFT_COLOR
     * <p>CSS: 'border-left-color', javaScript: 'borderLeftColor'
     * <br>Values: &lt;color&gt; | transparent | inherit
     * <br>Initial value: the value of the 'color' property
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_LEFT_COLOR = "borderLeftColor";

    /**
     * BORDER_TOP_STYLE
     * <p>CSS: 'border-top-style', javaScript: 'borderTopStyle'
     * <br>Values: &lt;border-style&gt; | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_TOP_STYLE = "borderTopStyle";

    /**
     * BORDER_RIGHT_STYLE
     * <p>CSS: 'border-right-style', javaScript: 'borderRightStyle'
     * <br>Values: &lt;border-style&gt; | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_RIGHT_STYLE = "borderRightStyle";

    /**
     * BORDER_BOTTOM_STYLE
     * <p>CSS: 'border-bottom-style', javaScript: 'borderBottomStyle'
     * <br>Values: &lt;border-style&gt; | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_BOTTOM_STYLE = "borderBottomStyle";

    /**
     * BORDER_LEFT_STYLE
     * <p>CSS: 'border-left-style'', javaScript: 'borderLeftStyle'
     * <br>Values: &lt;border-style&gt; | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_LEFT_STYLE = "borderLeftStyle";

    /**
     * BORDER_TOP_WIDTH
     * <p>CSS: 'border-top-width', javaScript: 'borderTopWidth'
     * <br>Values: &lt;border-width&gt; | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_TOP_WIDTH = "borderTopWidth";

    /**
     * BORDER_RIGHT_WIDTH
     * <p>CSS: 'border-right-width', javaScript: 'borderRightWidth'
     * <br>Values: &lt;border-width&gt; | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_RIGHT_WIDTH = "borderRightWidth";

    /**
     * BORDER_BOTTOM_WIDTH
     * <p>CSS: 'border-bottom-width, javaScript: 'borderBottomWidth'
     * <br>Values: &lt;border-width&gt; | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_BOTTOM_WIDTH = "borderBottomWidth";

    /**
     * BORDER_LEFT_WIDTH
     * <p>CSS: 'border-left-width', javaScript: 'borderLeftWidth'
     * <br>Values: &lt;border-width&gt; | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_LEFT_WIDTH = "borderLeftWidth";

    /**
     * BORDER_WIDTH
     * <p>CSS: 'border-width', javaScript: 'borderWidth'
     * <br>Values: &lt;border-width&gt;{1,4} | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER_WIDTH = "borderWidth";

    /**
     * BORDER
     * <p>CSS: 'border', javaScript: 'border'
     * <br>Values: [ &lt;border-width&gt; || &lt;border-style&gt; ||
     * 'border-top-color' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String BORDER = "border";

    /**
     * BOTTOM
     * <p>CSS: 'bottom', javaScript: 'bottom'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: positioned elements
     * <br>Inherited?: no
     * Percentages: refer to height of containing block
     * <br>Media groups: visual
     */
    public static final String BOTTOM = "bottom";

    /**
     * CAPTION_SIDE
     * <p>CSS: 'caption-side', javaScript: 'captionSide'
     * <br>Values: top | bottom | inherit
     * <br>Initial value: top
     * <br>Applies to: 'table-caption' elements
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String CAPTION_SIDE = "captionSide";

    /**
     * CLEAR
     * <p>CSS: 'clear', javaScript: 'clear'
     * <br>Values: none | left | right | both | inherit
     * <br>Initial value: none
     * <br>Applies to: block-level elements
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String CLEAR = "clear";

    /**
     * CLIP
     * <p>CSS: 'clip', javaScript: 'clip'
     * <br>Values: &lt;shape&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: absolutely positioned elements
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String CLIP = "clip";

    /**
     * COLOR
     * <p>CSS: 'color', javaScript: 'color'
     * <br>Values: &lt;color&gt; | inherit
     * <br>Initial value: depends on user agent
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String COLOR = "color";

    /**
     * CONTENT
     * <p>CSS: 'content', javaScript: 'content'
     * <br>Values: normal | none | [ &lt;string&gt; | &lt;uri&gt; |
     * &lt;counter&gt; | attr(&lt;identifier&gt;) | open-quote | close-quote |
     *  no-open-quote | no-close-quote ]+ | inherit
     * <br>Initial value: normal
     * <br>Applies to: :before and :after pseudo-elements
     * <br>Inherited?: no
     * <br>Media groups: all
     */
    public static final String CONTENT = "content";

    /**
     * COUNTER_INCREMENT
     * <p>CSS: 'counter-increment', javaScript: 'counterIncrement'
     * <br>Values: [ &lt;identifier&gt; &lt;integer&gt;? ]+ | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: all
     */
    public static final String COUNTER_INCREMENT = "counterIncrement";

    /**
     * COUNTER_RESET
     * <p>CSS: 'counter-reset', javaScript: 'counterReset'
     * <br>Values: [ &lt;identifier&gt; &lt;integer&gt;? ]+ | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: all
     */
    public static final String COUNTER_RESET = "counterReset";

    /**
     * CUE_AFTER
     * <p>CSS: 'cue-after', javaScript: 'cueAfter'
     * <br>Values: &lt;uri&gt; | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: aural
     */
    public static final String CUE_AFTER = "cueAfter";

    /**
     * CUE_BEFORE
     * <p>CSS: 'cue-before', javaScript: 'cueBefore'
     * <br>Values: &lt;uri&gt; | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: aural
     */
    public static final String CUE_BEFORE = "cueBefore";

    /**
     * CUE
     * <p>CSS: 'cue', javaScript: 'cue'
     * <br>Values: [ 'cue-before' || 'cue-after' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: aural
     */
    public static final String CUE = "cue";

    /**
     * CURSOR
     * <p>CSS: 'cursor', javaScript: 'cursor'
     * <br>Values: [ [&lt;uri&gt; ,]* [ auto | crosshair | default | pointer |
     * move | e-resize | ne-resize | nw-resize | n-resize | se-resize |
     * sw-resize | s-resize | w-resize | text | wait | help | progress ] ] |
     * inherit. Microsoft specific extensions: [ all-scroll | col-resize |
     * no-drop | not-allowed | row-resize | url | vertical-text ].
     * IE 5 and IE 5.5 only no hand, is the equivalent of pointer, which should
     * be used instead of hand.
     * <br>Initial value: auto
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual, interactive
     *
     * @see <a href=http://www.quirksmode.org/css/cursor.html">http://www.quirksmode.org/css/cursor.html</a>
     */
    public static final String CURSOR = "cursor";

    /**
     * DIRECTION
     * <p>CSS: 'direction', javaScript: 'direction'
     * <br>Values: ltr | rtl | inherit
     * <br>Initial value: ltr
     * <br>Applies to: all elements, but see prose
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String DIRECTION = "direction";

    /**
     * DISPLAY
     * <p>Four display values are consistently supported on all browsers. block,
     *  inline, none and list-item are safe the others work partly or not.
     *
     * <p>CSS: 'display', javaScript: 'display'
     * <br>Values: inline | block | list-item | run-in | inline-block | table |
     * inline-table | table-row-group | table-header-group | table-footer-group
     * | table-row | table-column-group | table-column | table-cell |
     * table-caption | none | inherit
     * <br>Initial value: inline
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: all
     *
     * @see <a href="http://www.quirksmode.org/css/display.html">http://www.quirksmode.org/css/display.html</a>
     */
    public static final String DISPLAY = "display";

    /**
     * ELEVATION
     * <p>CSS: 'elevation', javaScript: 'elevation'
     * <br>Values: &lt;angle&gt;  | below | level | above | higher | lower |
     * inherit
     * <br>Initial value: level
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String ELEVATION = "elevation";

    /**
     * EMPTY_CELLS
     * <p>CSS: 'empty-cells', javaScript: 'emptyCells'
     * <br>Values: show | hide | inherit
     * <br>Initial value: show
     * <br>Applies to: 'table-cell' elements
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String EMPTY_CELLS = "emptyCells";

    /**
     * FLOAT
     * <p>'float' is a reserved keyword in javaScript. Therefore the name can't
     * be used. So the value 'cssFloat' is defined. However, in IE this name
     * is called 'styleFloat'. This implementation takes care of that.
     *
     * <p>CSS: 'float', javaScript IE:'styleFloat', other: 'cssFloat'
     * <br>Values: left | right | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all, but see 9.7
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String FLOAT = impl.getFloatAttribute();

    /**
     * FONT_FAMILY
     * <p>CSS: 'font-family', javaScript: 'fontFamily'
     * <br>Values: [[ &lt;family-name&gt; | &lt;generic-family&gt; ]
     * [, &lt;family-name&gt;| &lt;generic-family&gt;]* ] | inherit
     * <br>Initial value: depends on user agent
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String FONT_FAMILY = "fontFamily";

    /**
     * FONT_SIZE
     * <p>CSS: 'font-size', javaScript: 'fontSize'
     * <br>Values: &lt;absolute-size&gt; | &lt;relative-size&gt; |
     * &lt;length&gt; | &lt;percentage&gt; | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: yes
     * Percentages: refer to parent element's font size
     * <br>Media groups: visual
     */
    public static final String FONT_SIZE = "fontSize";

    /**
     * FONT_STYLE
     * <p>CSS: 'font-style', javaScript: 'fontStyle'
     * <br>Values: normal | italic | oblique | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String FONT_STYLE = "fontStyle";

    /**
     * FONT_VARIANT
     * <p>CSS: 'font-variant', javaScript: 'fontVariant'
     * <br>Values: normal | small-caps | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String FONT_VARIANT = "fontVariant";

    /**
     * FONT_WEIGHT
     * <p>CSS: 'font-weight', javaScript: 'fontWeight'
     * <br>Values: normal | bold | bolder | lighter | 100 | 200 | 300 | 400 |
     * 500 | 600 | 700 | 800 | 900 | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String FONT_WEIGHT = "fontWeight";

    /**
     * FONT
     * <p>CSS: 'font', javaScript: 'font'
     * <br>Values: [ [ 'font-style' || 'font-variant' || 'font-weight' ]?
     * 'font-size' [ / 'line-height' ]? 'font-family' ] | caption | icon |
     *  menu | message-box | small-caption | status-bar | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: yes
     * Percentages: see individual properties
     * <br>Media groups: visual
     */
    public static final String FONT = "font";

    /**
     * HEIGHT
     * <p>CSS: 'height', javaScript: 'height'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: all elements but non-replaced inline elements, table
     * columns, and column groups
     * <br>Inherited?: no
     * Percentages: see prose
     * <br>Media groups: visual
     */
    public static final String HEIGHT = "height";

    /**
     * LEFT
     * <p>CSS: 'left', javaScript: 'left'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: positioned elements
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String LEFT = "left";

    /**
     * LETTER_SPACING
     * <p>CSS: 'letter-spacing', javaScript: 'letterSpacing'
     * <br>Values: normal | &lt;length&gt; | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String LETTER_SPACING = "letterSpacing";

    /**
     * LINE_HEIGHT
     * <p>CSS: 'line-height', javaScript: 'lineHeight'
     * <br>Values: normal | &lt;number&gt; | &lt;length&gt; | &lt;percentage&gt;
     *  | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * Percentages: refer to the font size of the element itself
     * <br>Media groups: visual
     */
    public static final String LINE_HEIGHT = "lineHeight";

    /**
     * LIST_STYLE_IMAGE
     * <p>CSS: 'list-style-image', javaScript: 'listStyleImage'
     * <br>Values: &lt;uri&gt; | none | inherit
     * <br>Initial value: none
     * <br>Applies to: elements with 'display: list-item'
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String LIST_STYLE_IMAGE = "listStyleImage";

    /**
     * LIST_STYLE_POSITION
     * <p>CSS: 'list-style-position', javaScript: 'listStylePosition'
     * <br>Values: inside | outside | inherit
     * <br>Initial value: outside
     * <br>Applies to: elements with 'display: list-item'
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String LIST_STYLE_POSITION = "listStylePosition";

    /**
     * LIST_STYLE_TYPE
     * <p>CSS: 'list-style-type', javaScript: 'listStyleType'
     * <br>Values: disc | circle | square | decimal | decimal-leading-zero |
     * lower-roman | upper-roman | lower-greek |  lower-latin | upper-latin |
     * armenian | georgian |  lower-alpha | upper-alpha |  none | inherit
     * <br>Initial value: disc
     * <br>Applies to: elements with 'display: list-item'
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String LIST_STYLE_TYPE = "listStyleType";

    /**
     * LIST_STYLE
     * <p>CSS: 'list-style', javaScript: 'listStyle'
     * <br>Values: [ 'list-style-type' || 'list-style-position' ||
     * 'list-style-image' ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: elements with 'display: list-item'
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String LIST_STYLE = "listStyle";

    /**
     * MARGIN_RIGHT
     * <p>CSS: 'margin-right', javaScript: 'marginRight'
     * <br>Values: &lt;margin-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except elements with table display types
     * other than table-caption, table and inline-table
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MARGIN_RIGHT = "marginRight";

    /**
     * MARGIN_LEFT
     * <p>CSS: 'margin-left', javaScript: 'marginLeft'
     * <br>Values: &lt;margin-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except elements with table display types
     * other than table-caption, table and inline-table
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MARGIN_LEFT = "marginLeft";

    /**
     * MARGIN_TOP
     * <p>CSS: 'margin-top', javaScript: 'marginTop'
     * <br>Values: &lt;margin-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except elements with table display types
     * other than table-caption, table and inline-table
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MARGIN_TOP = "marginTop";

    /**
     * MARGIN_BOTTOM
     * <p>CSS: 'margin-bottom', javaScript: 'marginBottom'
     * <br>Values: &lt;margin-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except elements with table display types
     * other than table-caption, table and inline-table
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MARGIN_BOTTOM = "marginBottom";

    /**
     * MARGIN
     * <p>CSS: 'margin', javaScript: 'margin'
     * <br>Values: &lt;margin-width&gt;{1,4} | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all elements except elements with table display types
     * other than table-caption, table and inline-table
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MARGIN = "margin";

    /**
     * MAX_HEIGHT
     * <p>CSS: 'max-height', javaScript: 'maxHeight'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all elements but non-replaced inline elements, table
     * columns, and column groups
     * <br>Inherited?: no
     * Percentages: see prose
     * <br>Media groups: visual
     */
    public static final String MAX_HEIGHT = "maxHeight";

    /**
     * MAX_WIDTH
     * <p>CSS: 'max-width', javaScript: 'maxWidth'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all elements but non-replaced inline elements, table
     * rows, and row groups
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MAX_WIDTH = "maxWidth";

    /**
     * MIN_HEIGHT
     * <p>CSS: 'min-height', javaScript: 'minHeight'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements but non-replaced inline elements, table
     * columns, and column groups
     * <br>Inherited?: no
     * Percentages: see prose
     * <br>Media groups: visual
     */
    public static final String MIN_HEIGHT = "minHeight";

    /**
     * MIN_WIDTH
     * <p>CSS: 'min-width', javaScript: 'minWidth'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements but non-replaced inline elements, table
     * rows, and row groups
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String MIN_WIDTH = "minWidth";

    /**
     * ORPHANS
     * <p>CSS: 'orphans', javaScript: 'orphans'
     * <br>Values: &lt;integer&gt; | inherit
     * <br>Initial value: 2
     * <br>Applies to: block-level elements
     * <br>Inherited?: yes
     * <br>Media groups: visual, paged
     */
    public static final String ORPHANS = "orphans";

    /**
     * OUTLINE
     * <p>
     * <p>Supported: Not supported by IE6 & 7.
     * <p>CSS: 'outline', javaScript: 'outline'
     * <br>Values: [ 'outline-color' || 'outline-style' || 'outline-width' ]
     * | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual, interactive
     */
    public static final String OUTLINE = "outline";

    /**
     * OUTLINE_COLOR
     * <p>CSS: 'outline-color', javaScript: 'outlineColor'
     * <br>Values: &lt;color&gt; | invert | inherit
     * <br>Initial value: invert
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual, interactive
     */
    public static final String OUTLINE_COLOR = "outlineColor";

    /**
     * OUTLINE_STYLE
     * <p>CSS: 'outline-style', javaScript: 'outlineStyle'
     * <br>Values: &lt;border-style&gt; | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual, interactive
     */
    public static final String OUTLINE_STYLE = "outlineStyle";

    /**
     * OUTLINE_WIDTH
     * <p>CSS: 'outline-width', javaScript: 'outlineWidth'
     * <br>Values: &lt;border-width&gt; | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual, interactive
     */
    public static final String OUTLINE_WIDTH = "outlineWidth";

    /**
     * OVERFLOW
     * <p>CSS: 'overflow', javaScript: 'overflow'
     * <br>Values: visible | hidden | scroll | auto | inherit
     * <br>Initial value: visible
     * <br>Applies to: non-replaced block-level elements, table cells, and
     * inline-block elements
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String OVERFLOW = "overflow";

    /**
     * PADDING_TOP
     * <p>CSS: 'padding-top', javaScript: 'paddingTop'
     * <br>Values: &lt;padding-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except table-row-group, table-header-group,
     * table-footer-group, table-row, table-column-group and table-column
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String PADDING_TOP = "paddingTop";

    /**
     * PADDING_RIGHT
     * <p>CSS: 'padding-right', javaScript: 'paddingRight'
     * <br>Values: &lt;padding-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except table-row-group, table-header-group,
     *  table-footer-group, table-row, table-column-group and table-column
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String PADDING_RIGHT = "paddingRight";

    /**
     * PADDING_BOTTOM
     * <p>CSS: 'padding-bottom', javaScript: 'paddingBottom'
     * <br>Values: &lt;padding-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except table-row-group, table-header-group,
     *  table-footer-group, table-row, table-column-group and table-column
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String PADDING_BOTTOM = "paddingBottom";

    /**
     * PADDING_LEFT
     * <p>CSS: 'padding-left', javaScript: 'paddingLeft'
     * <br>Values: &lt;padding-width&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all elements except table-row-group, table-header-group,
     * table-footer-group, table-row, table-column-group and table-column
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String PADDING_LEFT = "paddingLeft";

    /**
     * PADDING
     * <p>CSS: 'padding', javaScript: 'padding'
     * <br>Values: &lt;padding-width&gt;{1,4} | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all elements except table-row-group, table-header-group,
     * table-footer-group, table-row, table-column-group and table-column
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String PADDING = "padding";

    /**
     * PAGE_BREAK_AFTER
     * <p>CSS: 'page-break-after', javaScript: 'pageBreakAfter'
     * <br>Values: auto | always | avoid | left | right | inherit
     * <br>Initial value: auto
     * <br>Applies to: block-level elements
     * <br>Inherited?: no
     * <br>Media groups: visual, paged
     */
    public static final String PAGE_BREAK_AFTER = "pageBreakAfter";

    /**
     * PAGE_BREAK_BEFORE
     * <p>CSS: 'page-break-before', javaScript: 'pageBreakBefore'
     * <br>Values: auto | always | avoid | left | right | inherit
     * <br>Initial value: auto
     * <br>Applies to: block-level elements
     * <br>Inherited?: no
     * <br>Media groups: visual, paged
     */
    public static final String PAGE_BREAK_BEFORE = "pageBreakBefore";

    /**
     * PAGE_BREAK_INSIDE
     * <p>CSS: 'page-break-inside', javaScript: 'pageBreakInside'
     * <br>Values: avoid | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: block-level elements
     * <br>Inherited?: yes
     * <br>Media groups: visual, paged
     */
    public static final String PAGE_BREAK_INSIDE = "pageBreakInside";

    /**
     * PAUSE_AFTER
     * <p>CSS: 'pause-after', javaScript: 'pauseAfter'
     * <br>Values: &lt;time&gt; | &lt;percentage&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all
     * <br>Inherited?: no
     * Percentages: see prose
     * <br>Media groups: aural
     */
    public static final String PAUSE_AFTER = "pauseAfter";

    /**
     * PAUSE_BEFORE
     * <p>CSS: 'pause-before', javaScript: 'pauseBefore'
     * <br>Values: &lt;time&gt; | &lt;percentage&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: all
     * <br>Inherited?: no
     * Percentages: see prose
     * <br>Media groups: aural
     */
    public static final String PAUSE_BEFORE = "pauseBefore";

    /**
     * PAUSE
     * <p>CSS: 'pause', javaScript: 'pause'
     * <br>Values: [ [&lt;time&gt; | &lt;percentage&gt;]{1,2} ] | inherit
     * <br>Initial value: see individual properties
     * <br>Applies to: all
     * <br>Inherited?: no
     * Percentages: see descriptions of 'pause-before' and 'pause-after'
     * <br>Media groups: aural
     */
    public static final String PAUSE = "pause";

    /**
     * PITCH_RANGE
     * <p>CSS: 'pitch-range', javaScript: 'pitchRange'
     * <br>Values: &lt;number&gt; | inherit
     * <br>Initial value: 50
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String PITCH_RANGE = "pitchRange";

    /**
     * PITCH
     * <p>CSS: 'pitch', javaScript: 'pitch'
     * <br>Values: &lt;frequency&gt; | x-low | low | medium | high | x-high |
     * inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String PITCH = "pitch";

    /**
     * PLAY_DURING
     * <p>CSS: 'play-during', javaScript: 'playDuring'
     * <br>Values: &lt;uri&gt; [ mix || repeat ]? | auto | none | inherit
     * <br>Initial value: auto
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: aural
     */
    public static final String PLAY_DURING = "playDuring";

    /**
     * POSITION
     * <p>CSS: 'position', javaScript: 'position'
     * <br>Values: static | relative | absolute | fixed | inherit
     * <br>Initial value: static
     * <br>Applies to: all
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String POSITION = "position";

    /**
     * QUOTES
     * <p>CSS: 'quotes', javaScript: 'quotes'
     * <br>Values: [&lt;string&gt; &lt;string&gt;]+ | none | inherit
     * <br>Initial value: depends on user agent
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String QUOTES = "quotes";

    /**
     * RICHNESS
     * <p>CSS: 'richness', javaScript: 'richness'
     * <br>Values: &lt;number&gt; | inherit
     * <br>Initial value: 50
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String RICHNESS = "richness";

    /**
     * RIGHT
     * <p>CSS: 'right', javaScript: 'right'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: positioned elements
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String RIGHT = "right";

    /**
     * SPEAK_HEADER
     * <p>CSS: 'speak-header', javaScript: 'speakHeader'
     * <br>Values: once | always | inherit
     * <br>Initial value: once
     * <br>Applies to: elements that have table header information
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String SPEAK_HEADER = "speakHeader";

    /**
     * SPEAK_NUMERAL
     * <p>CSS: 'speak-numeral', javaScript: 'speakNumeral'
     * <br>Values: digits | continuous | inherit
     * <br>Initial value: continuous
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String SPEAK_NUMERAL = "speakNumeral";

    /**
     * SPEAK_PUNCTUATION
     * <p>CSS: 'speak-punctuation', javaScript: 'speakPunctuation'
     * <br>Values: code | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String SPEAK_PUNCTUATION = "speakPunctuation";

    /**
     * SPEAK
     * <p>CSS: 'speak', javaScript: 'speak'
     * <br>Values: normal | none | spell-out | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String SPEAK = "speak";

    /**
     * SPEECH_RATE
     * <p>CSS: 'speech-rate', javaScript: 'speechRate'
     * <br>Values: &lt;number&gt;  | x-slow | slow | medium | fast | x-fast |
     * faster | slower | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String SPEECH_RATE = "speechRate";

    /**
     * STRESS
     * <p>CSS: 'stress', javaScript: 'stress'
     * <br>Values: &lt;number&gt; | inherit
     * <br>Initial value: 50
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String STRESS = "stress";

    /**
     * TABLE_LAYOUT
     * <p>CSS: 'table-layout', javaScript: 'tableLayout'
     * <br>Values: auto | fixed | inherit
     * <br>Initial value: auto
     * <br>Applies to: 'table' and 'inline-table' elements
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String TABLE_LAYOUT = "tableLayout";

    /**
     * TEXT_ALIGN
     * <p>CSS: 'text-align', javaScript: 'textAlign'
     * <br>Values: left | right | center | justify | inherit
     * <br>Initial value: a nameless value that acts as 'left' if 'direction'
     * is 'ltr', 'right' if 'direction' is 'rtl'
     * <br>Applies to: block-level elements, table cells and inline blocks
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String TEXT_ALIGN = "textAlign";

    /**
     * TEXT_DECORATION
     * <p>CSS: 'text-decoration', javaScript: 'textDecoration'
     * <br>Values: none | [ underline || overline || line-through || blink ]
     * | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: no (see prose)
     * <br>Media groups: visual
     */
    public static final String TEXT_DECORATION = "textDecoration";

    /**
     * TEXT_INDENT
     * <p>CSS: 'text-indent', javaScript: 'textIndent'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | inherit
     * <br>Initial value: 0
     * <br>Applies to: block-level elements, table cells and inline blocks
     * <br>Inherited?: yes
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String TEXT_INDENT = "textIndent";

    /**
     * TEXT_TRANSFORM
     * <p>CSS: 'text-transform', javaScript: 'textTransform'
     * <br>Values: capitalize | uppercase | lowercase | none | inherit
     * <br>Initial value: none
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String TEXT_TRANSFORM = "textTransform";

    /**
     * TOP
     * <p>CSS: 'top', javaScript: 'top'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: positioned elements
     * <br>Inherited?: no
     * Percentages: refer to height of containing block
     * <br>Media groups: visual
     */
    public static final String TOP = "top";

    /**
     * UNICODE_BIDI
     * <p>CSS: 'unicode-bidi', javaScript: 'unicodeBidi'
     * <br>Values: normal | embed | bidi-override | inherit
     * <br>Initial value: normal
     * <br>Applies to: all elements, but see prose
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String UNICODE_BIDI = "unicodeBidi";

    /**
     * VERTICAL_ALIGN
     * <p>CSS: 'vertical-align', javaScript: 'verticalAlign'
     * <br>Values: baseline | sub | super | top | text-top | middle | bottom |
     * text-bottom | &lt;percentage&gt; | &lt;length&gt; | inherit
     * <br>Initial value: baseline
     * <br>Applies to: inline-level and 'table-cell' elements
     * <br>Inherited?: no
     * Percentages: refer to the 'line-height' of the element itself
     * <br>Media groups: visual
     */
    public static final String VERTICAL_ALIGN = "verticalAlign";

    /**
     * VISIBILITY
     * <p>CSS: 'visibility', javaScript: 'visibility'
     * <br>Values: visible | hidden | collapse | inherit
     * <br>Initial value: visible
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String VISIBILITY = "visibility";

    /**
     * VOICE_FAMILY
     * <p>CSS: 'voice-family', javaScript: 'voiceFamily'
     * <br>Values: [[&lt;specific-voice&gt;  | &lt;generic-voice&gt; ],]*
     * [&lt;specific-voice&gt;  | &lt;generic-voice&gt; ] | inherit
     * <br>Initial value: depends on user agent
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: aural
     */
    public static final String VOICE_FAMILY = "voiceFamily";

    /**
     * VOLUME
     * <p>CSS: 'volume', javaScript: 'volume'
     * <br>Values: &lt;number&gt; | &lt;percentage&gt; | silent | x-soft | soft
     * | medium | loud | x-loud | inherit
     * <br>Initial value: medium
     * <br>Applies to: all
     * <br>Inherited?: yes
     * Percentages: refer to inherited value
     * <br>Media groups: aural
     */
    public static final String VOLUME = "volume";

    /**
     * WHITE_SPACE
     * <p>CSS: 'white-space', javaScript: 'whiteSpace'
     * <br>Values: normal | pre | nowrap | pre-wrap | pre-line | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String WHITE_SPACE = "whiteSpace";

    /**
     * WIDOWS
     * <p>CSS: 'widows', javaScript: 'widows'
     * <br>Values: &lt;integer&gt; | inherit
     * <br>Initial value: 2
     * <br>Applies to: block-level elements
     * <br>Inherited?: yes
     * <br>Media groups: visual, paged
     */
    public static final String WIDOWS = "widows";

    /**
     * WIDTH
     * <p>CSS: 'width', javaScript: 'width'
     * <br>Values: &lt;length&gt; | &lt;percentage&gt; | auto | inherit
     * <br>Initial value: auto
     * <br>Applies to: all elements but non-replaced inline elements, table
     * rows, and row groups
     * <br>Inherited?: no
     * Percentages: refer to width of containing block
     * <br>Media groups: visual
     */
    public static final String WIDTH = "width";

    /**
     * WORD_SPACING
     * <p>CSS: 'word-spacing', javaScript: 'wordSpacing'
     * <br>Values: normal | &lt;length&gt; | inherit
     * <br>Initial value: normal
     * <br>Applies to: all
     * <br>Inherited?: yes
     * <br>Media groups: visual
     */
    public static final String WORD_SPACING = "wordSpacing";

    /**
     * Z_INDEX
     * <p>CSS: 'z-index', javaScript: 'zIndex'
     * <br>Values: auto | &lt;integer&gt; | inherit
     * <br>Initial value: auto
     * <br>Applies to: positioned elements
     * <br>Inherited?: no
     * <br>Media groups: visual
     */
    public static final String Z_INDEX = "zIndex";
  }


  /**
   * This class contains all named (i.e. String) values grouped by the
   * property. The values {@link V#AUTO}, {@link V#NONE} and {@link V#INHERIT}
   * are supported on multiple properties and are not separately put in a
   * class when only the property and other numeric property values are valid.
   */
  public static final class V {

    /**
     * AUTO
     * <p>Can be used with a number CSS properties, see properties for detail.
     */
    public static final String AUTO = "auto";

    /**
     * INHERIT
     * <p>Can be used with all CSS properties.
     */
    public static final String INHERIT = "inherit";

    /**
     * NONE
     * <p>Can be used with a number CSS properties, see properties for detail.
     */
    public static final String NONE = "none";

    /**
     * CSS property {@link A#AZIMUTH} values.
     */
    public static final class AZIMUTH {
      public static final String FAR_LEFT = "far-left";
      public static final String LEFT = "left";
      public static final String CENTER_LEFT = "center-left";

      /**
       * Default value for {@link A#AZIMUTH}.
       */
      public static final String CENTER = "center";
      public static final String CENTER_RIGHT = "center-right";
      public static final String RIGHT = "right";
      public static final String FAR_RIGHT = "far-right";
      public static final String RIGHT_SIDE = "right-side";
      public static final String BEHIND = "behind";
      public static final String LEFTWARDS = "leftwards";
      public static final String RIGHTWARDS = "rightwards";
    }


    /**
     * CSS property {@link A#BACKGROUND_ATTACHMENT} values.
     */
    public static final class BACKGROUND_ATTACHMENT {

      /**
       * Default value for {@link A#BACKGROUND_ATTACHMENT}.
       */
      public static final String SCROLL = "scroll";
      public static final String FIXED = "fixed";
    }


    /**
     * CSS property {@link A#BACKGROUND_POSITION} values.
     */
    public static final class BACKGROUND_POSITION {
      public static final String LEFT = "left";
      public static final String CENTER = "center";
      public static final String RIGHT = "right";
      public static final String TOP = "top";
    }

    /**
     * CSS property {@link A#BACKGROUND_REPEAT} values.
     */
    public static final class BACKGROUND_REPEAT {

      /**
       * Default value for {@link A#BACKGROUND_REPEAT }.
       */
      public static final String REPEAT = "repeat";
      public static final String REPEAT_X = "repeat-x";
      public static final String REPEAT_Y = "repeat-y";
      public static final String NO_REPEAT = "no-repeat";
    }


    /**
     * CSS property {@link A#BORDER_COLLAPSE} values.
     */
    public static final class BORDER_COLLAPSE {
      public static final String COLLAPSE = "collapse";

      /**
       * Default value for {@link A#BORDER_COLLAPSE }.
       */
      public static final String SEPARATE = "separate";
    }


    /**
     * CSS property {@link A#BORDER_STYLE} values.
     */
    public static final class BORDER_STYLE {

      /**
       * No border; the computed border width is zero.
       */
      public static final String NONE = "none";

      /**
       * Same as 'none', except in terms of border conflict resolution for
       * table elements.
       */
      public static final String HIDDEN = "hidden";

      /**
       * The border is a series of dots.
       */
      public static final String DOTTED = "dotted";

      /**
       * The border is a series of short line segments.
       */
      public static final String DASHED = "dashed";

      /**
       * The border is a single line segment.
       */
      public static final String SOLID = "solid";

      /**
       * The border is two solid lines. The sum of the two lines and the space
       *  between them equals the value of 'border-width'.
       */
      public static final String DOUBLE = "double";

      /**
       * The border looks as though it were carved into the canvas.
       */
      public static final String GROOVE = "groove";

      /**
       * The opposite of 'groove': the border looks as though it were coming
       * out of the canvas.
       */
      public static final String RIDGE = "ridge";

      /**
       * The border makes the box look as though it were embedded in the canvas.
       */
      public static final String INSET = "inset";

      /**
       * The opposite of 'inset': the border makes the box look as though it
       * were coming out of the canvas.
       */
      public static final String OUTSET = "outset";
    }


    /**
     * CSS property {@link A#BORDER_WIDTH} values.
     */
    public static final class BORDER_WIDTH {

      /**
       * A thin border.
       */
      public static final String THIN = "thin";

      /**
       * A medium border.
       */
      public static final String MEDIUM = "medium";

      /**
       * A thick border.
       */
      public static final String THICK = "thick";
    }


    /**
     * CSS property {@link A#CAPTION_SIDE} values.
     */
    public static final class CAPTION_SIDE {
      public static final String BOTTOM = "bottom";

      /**
       * Default value for {@link A#CAPTION_SIDE}.
       */
      public static final String TOP = "top";
    }


    /**
     * CSS property {@link A#CLEAR} values.
     */
    public static final class CLEAR {

      /**
       * Default value for {@link A#CLEAR}.
       */
      public static final String NONE = "none";
      public static final String LEFT = "left";
      public static final String RIGHT = "right";
      public static final String BOTH = "both";
    }


    /**
     * CSS property {@link A#CONTENT} values.
     */
    public static final class CONTENT {

      /**
       * Default value for {@link A#CONTENT}.
       */
      public static final String NORMAL = "normal";
      public static final String NONE = "none";
      // public static final String ATTR(<SPAN >&LT;IDENTIFIER&GT;</SPAN>) = "attr(<span >&lt;identifier&gt;</span>)";
      public static final String OPEN_QUOTE = "open-quote";
      public static final String CLOSE_QUOTE = "close-quote";
      public static final String NO_OPEN_QUOTE = "no-open-quote";
      public static final String NO_CLOSE_QUOTE = "no-close-quote";
    }


    /**
     * CSS property {@link A#CURSOR} values.
     */
    public static final class CURSOR {

      /**
       * Default value for {@link A#CURSOR}.
       */

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String ALL_SCROLL = "all-scroll";
      public static final String AUTO = "auto";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String COL_RESIZE = "col-resize";
      public static final String CROSSHAIR = "crosshair";
      public static final String DEFAULT = "default";

      /**
       * @deprecated Microsoft specific, required for IE 5 and IE 5.5, but both
       * browsers are not supported by GWT. Use {@link #POINTER} which is the
       * equivalent of <code>hand</code> and works in all browsers.
       */
      public static final String HAND = "hand";
      public static final String HELP = "help";
      public static final String MOVE = "move";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String NO_DROP = "no-drop";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String NOT_ALLOWED = "not-allowed";
      public static final String POINTER = "pointer";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String PROGRESS = "progress";
      public static final String E_RESIZE = "e-resize";
      public static final String NE_RESIZE = "ne-resize";
      public static final String NW_RESIZE = "nw-resize";
      public static final String N_RESIZE = "n-resize";
      public static final String SE_RESIZE = "se-resize";
      public static final String SW_RESIZE = "sw-resize";
      public static final String S_RESIZE = "s-resize";
      public static final String W_RESIZE = "w-resize";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String ROW_RESIZE = "row-resize";
      public static final String TEXT = "text";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String VERTICAL_TEXT = "vertical-text";

      /**
       * Microsoft specific, preferable avoid.
       */
      public static final String WAIT = "wait";
    }


    /**
     * CSS property {@link A#DIRECTION} values.
     */
    public static final class DIRECTION {

      /**
       * Direction left to right.
       * Default value for {@link A#DIRECTION}.
       */
      public static final String LTR = "ltr";

      /**
       * Direction right to left.
       */
      public static final String RTL = "rtl";
    }


    /**
     * CSS property {@link A#DISPLAY} values.
     */
    public static final class DISPLAY {

      /**
       * This value causes an element to generate a block box.
       */
      public static final String BLOCK = "block";

      /**
       * This value causes an element to generate one or more inline boxes.
       * Default value for {@link A#DISPLAY}.
       * <p>Note that although the initial value of 'display' is 'inline', rules
       * in the user agent's default style sheet may override this value.
       */
      public static final String INLINE = "inline";

      /**
       * NOTE: The property 'inline-block' is not fully supported by
       * Internet Explorer 6 and 7. To obtain the effect the static method
       * {@link RatingCSS#setInlineBlock(Element)} should be used. This method sets
       * the property in a browser dependent way.
       *
       * <p>This value causes an element to generate a block box, which itself
       * is flowed as a single inline box, similar to a replaced element. The
       * inside of an inline-block is formatted as a block box, and the element
       * itself is formatted as an inline replaced element.
       *
       * @see <a href="http://www.brunildo.org/test/InlineBlockLayout.html">http://www.brunildo.org/test/InlineBlockLayout.html</a>
       */
      public static final String INLINE_BLOCK = "inline-block";

      /**
       * This value causes an element (e.g., LI in HTML) to generate a principal
       * block box and a list-item inline box.
       */
      public static final String LIST_ITEM = "list-item";

      /**
       * This value causes an element to generate no boxes in the formatting
       * structure (i.e., the element has no effect on layout). Descendant
       * elements do not generate any boxes either; this behavior cannot be
       * overridden by setting the 'display' property on the descendants.
       * <p>Please note that a display of 'none' does not create an invisible
       * box; it creates no box at all. CSS includes mechanisms that enable an
       * element to generate boxes in the formatting structure that affect
       * formatting but are not visible themselves. Please consult the section
       * on visibility for details.
       */
      public static final String NONE = "none";
      public static final String RUN_IN = "run-in";
      public static final String TABLE = "table";
      public static final String INLINE_TABLE = "inline-table";
      public static final String TABLE_ROW_GROUP = "table-row-group";
      public static final String TABLE_HEADER_GROUP = "table-header-group";
      public static final String TABLE_FOOTER_GROUP = "table-footer-group";
      public static final String TABLE_ROW = "table-row";
      public static final String TABLE_COLUMN_GROUP = "table-column-group";
      public static final String TABLE_COLUMN = "table-column";
      public static final String TABLE_CELL = "table-cell";
      public static final String TABLE_CAPTION = "table-caption";
    }


    /**
     * CSS property {@link A#ELEVATION} values.
     */
    public static final class ELEVATION {
      public static final String ABOVE = "above";
      public static final String BELOW = "below";
      public static final String HIGHER = "higher";

      /**
       * Default value for {@link A#ELEVATION}.
       */
      public static final String LEVEL = "level";
      public static final String LOWER = "lower";
    }


    /**
     * CSS property {@link A#EMPTY_CELLS} values.
     */
    public static final class EMPTY_CELLS {

      /**
       * Default value for {@link A#EMPTY_CELLS}.
       */
      public static final String SHOW = "show";
      public static final String HIDE = "hide";
    }


    /**
     * CSS property {@link A#FLOAT} values.
     */
    public static final class FLOAT {
      public static final String LEFT = "left";

      /**
       * Default value for {@link A#FLOAT}.
       */
      public static final String NONE = "none";
      public static final String RIGHT = "right";
    }


    /**
     * CSS property {@link A#FONT_FAMILY} values. These are the generic font
     * families and are a fallback mechanism.
     */
    public static final class FONT_FAMILY {
      public static final String CURSIVE = "cursive";
      public static final String FANTASY = "fantasy";
      public static final String MONOSPACE = "monospace";
      public static final String SANS_SERIF = "sans-serif";
      public static final String SERIF = "serif";
    }


    /**
     * CSS property {@link A#FONT_SIZE} values.
     *
     * <p>Avoid using font size set as named values, like the ones present in
     * this class, e.g. small, etc., better should be avoided because different
     * browsers will display font's in different sizes. In other words small in
     * one browser isn't the same size as in another browser.
     */
    public static final class FONT_SIZE {
      public static final String XX_SMALL = "xx-small";
      public static final String X_SMALL = "x-small";
      public static final String SMALL = "small";

      /**
       * Default value for {@link A#FONT_SIZE}.
       */
      public static final String MEDIUM = "medium";
      public static final String LARGE = "large";
      public static final String X_LARGE = "x-large";
      public static final String XX_LARGE = "xx-large";
    }


    /**
     * CSS property {@link A#FONT_STYLE} values.
     */
    public static final class FONT_STYLE {
      public static final String ITALIC = "italic";

      /**
       * Default value for {@link A#FONT_STYLE}.
       */
      public static final String NORMAL = "normal";
      public static final String OBLIQUE = "oblique";
    }


    /**
     * CSS property {@link A#FONT_VARIANT} values.
     */
    public static final class FONT_VARIANT {

      /**
       * Default value for {@link A#FONT_VARIANT}.
       */
      public static final String NORMAL = "normal";
      public static final String SMALL_CAPS = "small-caps";
    }


    /**
     * CSS property {@link A#FONT_WEIGHT} values.
     */
    public static final class FONT_WEIGHT {

      /**
       * Value 'normal' is synonymous with '400'.
       * Default value for {@link A#FONT_WEIGHT}.
       */
      public static final String NORMAL = "normal";

      /**
       * Value 'bold' is synonymous with '700'.
       */
      public static final String BOLD = "bold";
      public static final String BOLDER = "bolder";
      public static final String LIGHTER = "lighter";
      public static final String W_100 = "100";
      public static final String W_200 = "200";
      public static final String W_300 = "300";

      /**
       * Value '400' is synonymous with 'normal'.
       */
      public static final String W_400 = "400";
      public static final String W_500 = "500";
      public static final String W_600 = "600";

      /**
       * Value '700' is synonymous with 'bold'.
       */
      public static final String W_700 = "700";
      public static final String W_800 = "800";
      public static final String W_900 = "900";
    }


    /**
     * CSS property {@link A#FONT} values.
     */
    public static final class FONT {

      /**
       * The font used for captioned controls (e.g., buttons, drop-downs, etc.).
       */
      public static final String CAPTION = "caption";

      /**
       * The font used to label icons.
       */
      public static final String ICON = "icon";

      /**
       * The font used in menus (e.g., drop-down menus and menu lists).
       */
      public static final String MENU = "menu";

      /**
       * The font used in dialog boxes.
       */
      public static final String MESSAGE_BOX = "message-box";

      /**
       * The font used for labeling small controls.
       */
      public static final String SMALL_CAPTION = "small-caption";

      /**
       * The font used in window status bars.
       */
      public static final String STATUS_BAR = "status-bar";
    }


    /**
     * CSS property {@link A#LETTER_SPACING} values.
     */
    public static final class LETTER_SPACING {

      /**
       * Default value for {@link A#LETTER_SPACING}.
       */
      public static final String NORMAL = "normal";
    }


    /**
     * CSS property {@link A#LINE_HEIGHT} values.
     */
    public static final class LINE_HEIGHT {

      /**
       * Default value for {@link A#LINE_HEIGHT}.
       */
      public static final String NORMAL = "normal";
    }


    /**
     * CSS property {@link A#LIST_STYLE_POSITION} values.
     */
    public static final class LIST_STYLE_POSITION {
      public static final String INSIDE = "inside";

      /**
       * Default value for {@link A#LIST_STYLE_POSITION}.
       */
      public static final String OUTSIDE = "outside";
    }


    /**
     * CSS property {@link A#LIST_STYLE_TYPE} values.
     */
    public static final class LIST_STYLE_TYPE {

      /**
       * Default value for {@link A#LIST_STYLE_TYPE}.
       */

      /**
       *
       */
      public static final String DISC = "disc";

      /**
       *
       */
      public static final String CIRCLE = "circle";

      /**
       *
       */
      public static final String SQUARE = "square";

      /**
       * Decimal numbers, beginning with 1.
       */
      public static final String DECIMAL = "decimal";

      /**
       * Decimal numbers padded by initial zeros (e.g., 01, 02, ..., 98, 99).
       */
      public static final String DECIMAL_LEADING_ZERO = "decimal-leading-zero";

      /**
       * Lowercase roman numerals (i, ii, iii, iv, v, etc.).
       */
      public static final String LOWER_ROMAN = "lower-roman";

      /**
       * Uppercase roman numerals (I, II, III, IV, V, etc.).
       */
      public static final String UPPER_ROMAN = "upper-roman";

      /**
       * Lowercase classical Greek alpha, beta, ... (&alpha;, &beta;, ?, ...).
       */
      public static final String LOWER_GREEK = "lower-greek";

      /**
       * Lowercase ASCII letters (a, b, c, ... z).
       */
      public static final String LOWER_LATIN = "lower-latin";

      /**
       * Uppercase ASCII letters (A, B, C, ... Z).
       */
      public static final String UPPER_LATIN = "upper-latin";

      /**
       * Traditional Armenian numbering.
       */
      public static final String ARMENIAN = "armenian";

      /**
       * Traditional Georgian numbering (an, ban, gan, ..., in, in-an, ...).
       */
      public static final String GEORGIAN = "georgian";

      /**
       * Lowercase ASCII letters (a, b, c, ... z).
       */
      public static final String LOWER_ALPHA = "lower-alpha";

      /**
       * Uppercase ASCII letters (A, B, C, ... Z).
       */
      public static final String UPPER_ALPHA = "upper-alpha";

      /**
       *
       */
      public static final String NONE = "none";
    }


    /**
     * CSS property {@link A#OUTLINE_COLOR} values.
     */
    public static final class OUTLINE_COLOR {

      /**
       * Default value for {@link A#OUTLINE_COLOR}.
       */
      public static final String INVERT = "invert";
    }


    /**
     * CSS property {@link A#OVERFLOW} values.
     */
    public static final class OVERFLOW {
      public static final String AUTO = "auto";
      public static final String HIDDEN = "hidden";
      public static final String SCROLL = "scroll";

      /**
       * Default value for {@link A#OVERFLOW}.
       */
      public static final String VISIBLE = "visible";
    }


    /**
     * CSS property {@link A#PAGE_BREAK_AFTER} values.
     */
    public static final class PAGE_BREAK_AFTER {
      public static final String ALWAYS = "always";

      /**
       * Default value for {@link A#PAGE_BREAK_AFTER}.
       */
      public static final String AUTO = "auto";
      public static final String AVOID = "avoid";
      public static final String LEFT = "left";
      public static final String RIGHT = "right";
    }


    /**
     * CSS property {@link A#PAGE_BREAK_BEFORE} values.
     */
    public static final class PAGE_BREAK_BEFORE {
      public static final String ALWAYS = "always";

      /**
       * Default value for {@link A#PAGE_BREAK_BEFORE}.
       */
      public static final String AUTO = "auto";
      public static final String AVOID = "avoid";
      public static final String LEFT = "left";
      public static final String RIGHT = "right";
    }


    /**
     * CSS property {@link A#PAGE_BREAK_INSIDE} values.
     */
    public static final class PAGE_BREAK_INSIDE {

      /**
       * Default value for {@link A#PAGE_BREAK_INSIDE}.
       */
      public static final String AUTO = "auto";
      public static final String AVOID = "avoid";
    }


    /**
     * CSS property {@link A#PITCH} values.
     */
    public static final class PITCH {
      public static final String X_LOW = "x-low";
      public static final String LOW = "low";

      /**
       * Default value for {@link A#PITCH}.
       */
      public static final String MEDIUM = "medium";
      public static final String HIGH = "high";
      public static final String X_HIGH = "x-high";
    }


    /**
     * CSS property {@link A#PLAY_DURING} values.
     */
    public static final class PLAY_DURING {

      /**
       * Default value for {@link A#PLAY_DURING}.
       */
      public static final String AUTO = "auto";
      public static final String NONE = "none";
    }


    /**
     * CSS property {@link A#POSITION} values.
     */
    public static final class POSITION {
      public static final String ABSOLUTE = "absolute";
      public static final String FIXED = "fixed";
      public static final String RELATIVE = "relative";

      /**
       * Default value for {@link A#POSITION}.
       */
      public static final String STATIC = "static";
    }


    /**
     * CSS property {@link A#SPEAK_HEADER} values.
     */
    public static final class SPEAK_HEADER {
      public static final String ALWAYS = "always";

      /**
       * Default value for {@link A#SPEAK_HEADER}.
       */
      public static final String ONCE = "once";
    }


    /**
     * CSS property {@link A#SPEAK_NUMERAL} values.
     */
    public static final class SPEAK_NUMERAL {
      public static final String DIGITS = "digits";

      /**
       * Default value for {@link A#SPEAK_NUMERAL}.
       */
      public static final String CONTINUOUS = "continuous";
    }


    /**
     * CSS property {@link A#SPEAK_PUNCTUATION} values.
     */
    public static final class SPEAK_PUNCTUATION {
      public static final String CODE = "code";

      /**
       * Default value for {@link A#SPEAK_PUNCTUATION}.
       */
      public static final String NONE = "none";
    }


    /**
     * CSS property {@link A#SPEAK} values.
     */
    public static final class SPEAK {
      public static final String NONE = "none";

      /**
       * Default value for {@link A#SPEAK}.
       */
      public static final String NORMAL = "normal";
      public static final String SPELL_OUT = "spell-out";
    }


    /**
     * CSS property {@link A#SPEECH_RATE} values.
     */
    public static final class SPEECH_RATE {
      public static final String X_SLOW = "x-slow";
      public static final String SLOW = "slow";

      /**
       * Default value for {@link A#SPEECH_RATE}.
       */
      public static final String MEDIUM = "medium";
      public static final String FAST = "fast";
      public static final String X_FAST = "x-fast";
      public static final String FASTER = "faster";
      public static final String SLOWER = "slower";
    }


    /**
     * CSS property {@link A#TABLE_LAYOUT} values.
     */
    public static final class TABLE_LAYOUT {

      /**
       * Default value for {@link A#TABLE_LAYOUT}.
       */
      public static final String AUTO = "auto";
      public static final String FIXED = "fixed";
    }


    /**
     * CSS property {@link A#TEXT_ALIGN} values.
     */
    public static final class TEXT_ALIGN {
      public static final String CENTER = "center";
      public static final String JUSTIFY = "justify";

      /**
       * Implicit (i.e. real value is empty) default value for
       * {@link A#TEXT_ALIGN} if 'direction' is 'ltr'
       */
      public static final String LEFT = "left";

      /**
       * Implicit (i.e. real value is empty) default value for
       * {@link A#TEXT_ALIGN} if 'direction' is 'rtl'.
       */
      public static final String RIGHT = "right";
    }


    /**
     * CSS property {@link A#TEXT_DECORATION} values.
     */
    public static final class TEXT_DECORATION {
      public static final String BLINK = "blink";
      public static final String LINE_THROUGH = "line-through";

      /**
       * Default value for {@link A#TEXT_DECORATION}.
       */
      public static final String NONE = "none";
      public static final String UNDERLINE = "underline";
      public static final String OVERLINE = "overline";
    }


    /**
     * CSS property {@link A#TEXT_TRANSFORM} values.
     */
    public static final class TEXT_TRANSFORM {
      public static final String CAPITALIZE = "capitalize";
      public static final String LOWERCASE = "lowercase";

      /**
       * Default value for {@link A#TEXT_TRANSFORM}.
       */
      public static final String NONE = "none";
      public static final String UPPERCASE = "uppercase";
    }


    /**
     * CSS property {@link A#UNICODE_BIDI} values.
     */
    public static final class UNICODE_BIDI {
      public static final String BIDI_OVERRIDE = "bidi-override";
      public static final String EMBED = "embed";

      /**
       * Default value for {@link A#UNICODE_BIDI}.
       */
      public static final String NORMAL = "normal";
    }


    /**
     * CSS property {@link A#VERTICAL_ALIGN} values.
     */
    public static final class VERTICAL_ALIGN {

      /**
       * Default value for {@link A#VERTICAL_ALIGN}.
       */
      public static final String BASELINE = "baseline";
      public static final String SUB = "sub";
      public static final String SUPER = "super";
      public static final String TOP = "top";
      public static final String TEXT_TOP = "text-top";
      public static final String MIDDLE = "middle";
      public static final String BOTTOM = "bottom";
      public static final String TEXT_BOTTOM = "text-bottom";
    }


    /**
     * CSS property {@link A#VISIBILITY} values.
     */
    public static final class VISIBILITY {
      public static final String COLLAPSE = "collapse";
      public static final String HIDDEN = "hidden";

      /**
       * Default value for {@link A#VISIBILITY}.
       */
      public static final String VISIBLE = "visible";
    }


    /**
     * CSS property {@link A#VOICE_FAMILY} values.
     */
    public static final class VOICE_FAMILY {}


    /**
     * CSS property {@link A#VOLUME} values.
     */
    public static final class VOLUME {
      public static final String SILENT = "silent";
      public static final String X_SOFT = "x-soft";
      public static final String SOFT = "soft";

      /**
       * Default value for {@link A#VOLUME}.
       */
      public static final String MEDIUM = "medium";
      public static final String LOUD = "loud";
      public static final String X_LOUD = "x-loud";
    }


    /**
     * CSS property {@link A#WHITE_SPACE} values.
     */
    public static final class WHITE_SPACE {

      /**
       * Default value for {@link A#WHITE_SPACE}.
       */
      public static final String NORMAL = "normal";
      public static final String PRE = "pre";
      public static final String NOWRAP = "nowrap";
      public static final String PRE_WRAP = "pre-wrap";
      public static final String PRE_LINE = "pre-line";
    }
  }

  /**
   * Convenience method to set a style property on an element.
   *
   * <p>The GWT compiler will optimize this method away, meaning there are no
   * additional costs of an extra method call when using method.
   *
   * @param element Element to set the property on
   * @param name Name of the property
   * @param value Value of the property
   */
  public static void setProperty(Element element, String name, String value) {
    element.getStyle().setProperty(name, value);
  }

  /**
   * Convenience method to set a style property on a widget.
   *
   * <p>The GWT compiler will optimize this method away, meaning there are no
   * additional costs of an extra method call when using method.
   *
   * @param widget Widget to set the property on
   * @param name Name of the property
   * @param value Value of the property
   */
  public static void setProperty(Widget widget, String name, String value) {
    widget.getElement().getStyle().setProperty(name, value);
  }

  /**
   * Convenience method to set a style pixel property on an element.
   *
   * <p>The GWT compiler will optimize this method away, meaning there are no
   * additional costs of an extra method call when using method.
   *
   * @param element Element to set the property on
   * @param name Name of the property
   * @param value Value of the property in pixels
   */
  public static void setPropertyPx(Element element, String name, int value) {
    element.getStyle().setPropertyPx(name, value);
  }

  /**
   * Convenience method to set a style pixel property on an element.
   *
   * <p>The GWT compiler will optimize this method away, meaning there are no
   * additional costs of an extra method call when using method.
   *
   * @param widget Widget to set the property on
   * @param name Name of the property
   * @param value Value of the property in pixels
   */
  public static void setPropertyPx(Widget widget, String name, int value) {
    widget.getElement().getStyle().setPropertyPx(name, value);
  }

  /**
   * Method to handle the browser specific implementation requirements for the
   * property value 'inline-block' of the property 'display'.
   *
   * @see <a href="http://www.brunildo.org/test/InlineBlockLayout.html">http://www.brunildo.org/test/InlineBlockLayout.html</a>
   * @see <a href="http://www.tanfa.co.uk/archives/show.asp?var=300">http://www.tanfa.co.uk/archives/show.asp?var=300</a>
   * @see <a href="http://www.brunildo.org/test/inline-block.html">http://www.brunildo.org/test/inline-block.html</a>
   *
   * @param element Element to set the display property inline-block on
   */
  public static void setInlineBlock(Element element) {
    impl.setInlineBlock(element);
  }

  /**
   * Set the Opacity on an element taking care of browser specific
   * implementations.
   *
   * @param element Element to set opacity
   * @param opacity value between 0 and 1
   */
  public static void setOpacity(Element element, float opacity) {
    impl.setOpacity(element, opacity);
  }

  /**
   * Make an element unselectable by the user and takes care of the browser
   * specific implementations.
   *
   * <p>This method will <i>not</i> protect you from people who want to copy
   * content and the technique should preferable only be used on specific
   * elements like a button.
   *
   * @param element Element to make unselectable
   * @param selectable If <code>true</code> elements becomes unselectable,
   *                   if <code>false</code> it becomes selectable.
   */
  public static void setSelectable(Element element, boolean selectable) {
    impl.setSelectable(element, selectable);
  }
}
