package business.services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Utility {

    public static String getNameById(HttpServletRequest request, String name, int id) {
        ServletContext application = request.getServletContext();
        HashMap<Integer, String> names = (HashMap<Integer, String>) application.getAttribute(name);
        return names.get(id);
    }
}
