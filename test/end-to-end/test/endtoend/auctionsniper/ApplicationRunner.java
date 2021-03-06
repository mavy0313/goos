package test.endtoend.auctionsniper;

//import static auctionsniper.ui.SnipersTableModel.textFor;
//import static org.hamcrest.Matchers.containsString;
import static auctionsniper.ui.MainWindow.*;
import static test.endtoend.auctionsniper.FakeAuctionServer.XMPP_HOSTNAME;

//import java.io.IOException;

//import javax.swing.SwingUtilities;

import auctionsniper.Main;
import auctionsniper.ui.MainWindow;
//import auctionsniper.SniperState;
//import auctionsniper.ui.MainWindow;


public class ApplicationRunner {

    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PASSWORD = "sniper";
    public static final String SNIPER_XMPP_ID = SNIPER_ID + "@" + XMPP_HOSTNAME + "/Auction";

    private AuctionSniperDriver driver;

    public void startBiddingIn(final FakeAuctionServer auction) {
        Thread thread = new Thread("Test Application") {
            @Override public void run() {
                try {
                    Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PASSWORD, auction.getItemId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        driver = new AuctionSniperDriver(1000);
        driver.showsSniperStatus(STATUS_JOINING);
    }

    public void hasShownSniperIsBidding() {
        driver.showsSniperStatus(STATUS_BIDDING);
    }

    public void showsSniperHasLostAuction() {
        driver.showsSniperStatus(STATUS_LOST);
    }

    public void stop() {
        if (driver != null) {
            driver.dispose();
        }
    }
}
