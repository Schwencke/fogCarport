package web.commands;

import business.exceptions.UserException;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CommandSVGDraw extends CommandProtectedPage{
    SVG svg;

    public CommandSVGDraw(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, SQLException {
        svg = new SVG(100,100, "0 0 1000 800", 0, 0);
        svg.addBeams(request);
        svg.addRafters(request);
        svg.addPosts(request);
        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;
    }
}
