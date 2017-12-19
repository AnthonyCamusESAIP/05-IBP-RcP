package beans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

public class MenuViewManager {
    private int projectId;  
    private Map<String,String> projects = new HashMap<String, String>();
    private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
    
    @PostConstruct
    public void init() {
    	projects = new HashMap<String, String>();
    	projects.put("New York", "New York");
    	projects.put("London","London");
    	projects.put("Paris","Paris");
    	projects.put("Barcelona","Barcelona");
    	projects.put("Istanbul","Istanbul");
    	projects.put("Berlin","Berlin");
    }
}
