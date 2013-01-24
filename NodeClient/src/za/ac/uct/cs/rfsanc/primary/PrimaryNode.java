package za.ac.uct.cs.rfsanc.primary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 * Primary-node implementation, with various server-side methods.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public class PrimaryNode extends Node {

    private WebService primaryNodeService;
    private final String PATH_QUERY_NODE = "#";
    private final String PATH_SUBMIT_ALLOC = "#/submit_allocation";
    private final String PATH_SUBMIT_AUCTION = "#/submit_auction";
    private final String PATH_RECLAIM_ALLOC = "#/reclaim_allocation?alloc=$1";

    public PrimaryNode(String username, String password, String nodeID, String baseURI) {
        super(username, password, nodeID, baseURI);
        primaryNodeService = new WebService(baseURI, "node/primary");
        primaryNodeService.setUsernamePassword(username, password);
    }

    public void queryNode() {
        primaryNodeService.doGet(String.class, insertPathID(PATH_QUERY_NODE));
    }

    public void submitAllocation(String allocationJSON) {
        primaryNodeService.doPut(String.class, insertPathID(PATH_SUBMIT_ALLOC), allocationJSON);
    }

    public void submitAuction(String auctionJSON) {
        primaryNodeService.doPut(String.class, insertPathID(PATH_SUBMIT_AUCTION), auctionJSON);
    }

    public void reclaimAllocation(String allocID) {
        primaryNodeService.doGet(String.class, setPathQueryParams(insertPathID(PATH_RECLAIM_ALLOC), allocID));
    }

    @Override
    public void runDaemon() {
    }
}