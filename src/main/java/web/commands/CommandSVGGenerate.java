package web.commands;

import business.exceptions.UserException;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CommandSVGGenerate extends CommandProtectedPage {
    SVG svg;

    public CommandSVGGenerate(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, SQLException {

        svg = new SVG(1, 80, "0 0 1000 880", 0, 0);
        svg.SVGDefs();
        svg.SVGNest(100, 100, "0 0 1000 1", 0, 0);
        svg.drawRoof(request);
        svg.drawBeam(request);
        svg.drawRafter(request);
        svg.drawPost(request);
        svg.SVGClose();
        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;
    }
}
