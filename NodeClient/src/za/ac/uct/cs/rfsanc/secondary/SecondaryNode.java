package za.ac.uct.cs.rfsanc.secondary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 * Secondary-node implementation, with various server-side methods.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public class SecondaryNode extends Node {

    /**
     * Web service context for a secondary node
     */
    private WebService secondaryNodeService;
    /**
     * REST resource URI for submit-bid request
     */
    private final String PATH_SUBMIT_BID = "#/submit_bid";

    public SecondaryNode(String username, String password, String nodeID, String baseURI) {
        super(username, password, nodeID, baseURI);
        secondaryNodeService = new WebService(baseURI, "node/secondary");
        secondaryNodeService.setUsernamePassword(username, password);
    }

    public String submitBid(String bidJSON) {
       return secondaryNodeService.doPut(String.class, insertPathID(PATH_SUBMIT_BID), bidJSON);
    }

    @Override
    public void runDaemon() {
    }
}