package za.ac.uct.cs.rfsanc.primary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 *
 * @author James
 */
public class PrimaryNode extends Node {

    private WebService primaryNodeService;

    public PrimaryNode(String username, String password, String nodeID, String baseURI) {
        super(username, password, nodeID, baseURI);
        primaryNodeService = new WebService(baseURI, "node/primary");
        primaryNodeService.setUsernamePassword(username, password);
    }
    
    public void queryNode(){
        
    }
    
    public void submitAllocation(){
        
    }
    
    public void submitAuction(){
        
    }
    
    public void reclaimAllocation(){
        
    }

    @Override
    public void runDaemon() {
    }
}
