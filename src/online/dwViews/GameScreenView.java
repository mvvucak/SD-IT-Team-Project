package online.dwViews;

import io.dropwizard.views.View;
import logic.Game;

/**
 * Each HTML page that is specified in GameWebPagesResource first needs a class that extends
 * View, which is Dropwizard's internal representation of the page. This then points to a
 * separate file found in the resource directory that contains the actual HTML/Javascript.
 * 
 * The HTML/Javascript page for this View can be found in resources/dwViews/GameScreen.ftl
 * 
 * You do not need to edit this class. You will need to complete GameScreen.ftl.
 * 
 * Note: The HTML/Javascript file is actially a freemarker file (https://freemarker.apache.org/),
 * however we do not expect you to use the additional functionality that freemarker provides.
 */
public class GameScreenView extends View {
	/**
	 * Simple Constructor method, it simply specifies where the HTML page is to return.
	 */
    public GameScreenView() {
        super("GameScreen.ftl");
    }

}
