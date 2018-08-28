package test.endtoend.auctionsniper;

//import static auctionsniper.ui.MainWindow.NEW_ITEM_ID_NAME;
//import static auctionsniper.ui.MainWindow.NEW_ITEM_STOP_PRICE_NAME;
//import static com.objogate.wl.swing.matcher.IterableComponentsMatcher.matching;
//import static com.objogate.wl.swing.matcher.JLabelTextMatcher.withLabelText;
//import static java.lang.String.valueOf;
//
//import javax.swing.JButton;
//import javax.swing.JTextField;
//import javax.swing.table.JTableHeader;
//
import auctionsniper.ui.MainWindow;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.*;
import com.objogate.wl.swing.gesture.GesturePerformer;

import static org.hamcrest.core.IsEqual.equalTo;

public class AuctionSniperDriver extends JFrameDriver {

    public AuctionSniperDriver(int timeoutMillis) {
        super(new GesturePerformer(),
                JFrameDriver.topLevelFrame(
                        named(MainWindow.MAIN_WINDOW_NAME),
                        showingOnScreen()),
                new AWTEventQueueProber(timeoutMillis, 100));
    }
    public void showsSniperStatus(String statusText) {
        new JLabelDriver(
                this, named(MainWindow.SNIPER_STATUS_NAME)).hasText(equalTo(statusText));
    }
}
