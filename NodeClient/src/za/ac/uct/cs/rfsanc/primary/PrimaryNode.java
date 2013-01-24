package za.ac.uct.cs.rfsanc.primary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 * Primary-node implementation, with various server-side methods.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public class PrimaryNode extends Node {

    /**
     * Web service context for a primary node
     */
    private WebService primaryNodeService;
    /**
     * REST resource URI for query-node request
     */
    private final String PATH_QUERY_NODE = "#";
    /**
     * REST resource URI for submit-allocation request
     */
    private final String PATH_SUBMIT_ALLOC = "#/submit_allocation";
    /**
     * REST resource URI for submit-auction request
     */
    private final String PATH_SUBMIT_AUCTION = "#/submit_auction";
    /**
     * REST resource URI for reclaim-auction request
     */
    private final String PATH_RECLAIM_ALLOC = "#/reclaim_allocation?alloc=$1";

    public PrimaryNode(String username, String password, String nodeID, String baseURI) {
        super(username, password, nodeID, baseURI);
        primaryNodeService = new WebService(baseURI, "node/primary");
        primaryNodeService.setUsernamePassword(username, password);
    }

    public String queryNode() {
        return primaryNodeService.doGet(String.class, insertPathID(PATH_QUERY_NODE));
    }

    public String submitAllocation(String allocationJSON) {
        return primaryNodeService.doPut(String.class, insertPathID(PATH_SUBMIT_ALLOC), allocationJSON);
    }

    public String submitAuction(String auctionJSON) {
        return primaryNodeService.doPut(String.class, insertPathID(PATH_SUBMIT_AUCTION), auctionJSON);
    }

    public String reclaimAllocation(String allocID) {
        return primaryNodeService.doGet(String.class, insertQueryParamArgs(insertPathID(PATH_RECLAIM_ALLOC), allocID));
    }

    @Override
    public void runDaemon() {
    }
}