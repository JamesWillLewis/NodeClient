package za.ac.uct.cs.rfsanc.secondary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 *
 * @author James
 */
public class SecondaryNode extends Node {

    private WebService secondaryNodeService;

    public SecondaryNode(String username, String password, String nodeID, String baseURI) {
        super(username, password, nodeID, baseURI);
        secondaryNodeService = new WebService(baseURI, "node/secondary");
        secondaryNodeService.setUsernamePassword(username, password);
    }
    
    public void submitBid(){
        
    }

    @Override
    public void runDaemon() {
    }
}
