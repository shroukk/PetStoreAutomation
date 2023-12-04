package api.endpoints;

import java.util.ResourceBundle;

public class BaseRoutes {
    public static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }
}
