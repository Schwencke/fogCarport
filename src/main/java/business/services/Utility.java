package business.services;

import business.entities.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

public class Utility {

    // Return role name from role id
    public static String getRoleById(HttpServletRequest request, int userId) {
        ServletContext application = request.getServletContext();
        List<Role> roleList = (List<Role>) application.getAttribute("rolelist");

        return roleList.get(userId - 1).getName();
    }
}
