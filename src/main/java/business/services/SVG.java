package business.services;

import business.entities.Material;
import business.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SVG {
    StringBuilder svg = new StringBuilder();
    Order order;

    private int canvasWidth;
    private int canvasHeight;
    private String viewBox;
    private int posX;
    private int posY;

    private final String templateHeader = "<svg " +
            "height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\" " +
            "y=\"%d\" " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String templateFooter = "</svg>";
    private final String templateRectangle = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    //private final String templateLine = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String templateLineArrow = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke: #000; marker-start: url(#beginArrow); marker-end: url(#endArrow);\"/>";
    private final String templateText = "<text style=\"text-anchor: middle\" transform=\"translate(%f, %f) rotate(%d)\">%s</text>";
    private final String defArrowBegin = "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\"><path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000;\"/></marker>";
    private final String defArrowEnd = "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\"><path d=\"M0,0 L12,6 L0,12 L0,0\" style=\"fill: #000;\"/></marker>";

    public SVG(int canvasWidth, int canvasHeight, String viewBox, int posX, int posY) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.viewBox = viewBox;
        this.posX = posX;
        this.posY = posY;
        svg.append(String.format(templateHeader, canvasWidth, canvasHeight, viewBox, posX, posY));
    }

    public void SVGNest(int canvasWidth, int canvasHeight, String viewBox, int posX, int posY) {
        svg.append(String.format(templateHeader, canvasWidth, canvasHeight, viewBox, posX, posY));
    }

    public void SVGClose() {
        svg.append(String.format(templateFooter));
    }

    public void SVGDefs() {
        svg.append("<defs>");
        svg.append(String.format(defArrowBegin));
        svg.append(String.format(defArrowEnd));
        svg.append("</defs>");
    }

    // Spær
    public void drawRafter(HttpServletRequest request) {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("rafterlist");

        double posX = 120;
        double posY = 60;
        double dimH = (double) materials.get(0).getHeight() / 10;
        double height = ((double) order.getCarportLength() - dimH) / (double) (materials.get(0).getQuantity() - 1);

        for (int i = 0; i < ((materials.get(0).getQuantity())); i++) {
            svg.append(String.format(templateRectangle, posX + (height * i), posY, (double) order.getCarportWidth(), dimH));
        }

        // Arrow (left)
        double arrowX = 20;
        svg.append(String.format(templateLineArrow, arrowX, posY - (dimH * 0.5), arrowX, posY + (double) order.getCarportWidth() + (dimH * 0.5)));
        svg.append(String.format(templateText, arrowX - 5, posY + ((double) order.getCarportWidth() * 0.5), -90, order.getCarportWidth() + " cm"));
    }

    // Remme
    public void drawBeam(HttpServletRequest request) {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("beamlist");

        double posX = 120;
        double posY = 60;
        double offsetW1 = 35;
        double dimH = (double) materials.get(0).getHeight() / 10;
        double width = ((((double) order.getCarportWidth() - (offsetW1 * 2)) / (materials.get(0).getQuantity() - 1)));

        for (int i = 0; i < materials.get(0).getQuantity(); i++) {
            svg.append(String.format(templateRectangle, posX, posY + (width * i) + offsetW1 - (dimH * 0.5), dimH, (double) order.getCarportLength()));
        }

        // Arrow (left)
        double arrowX = 70;
        svg.append(String.format(templateLineArrow,
                arrowX,
                posY + offsetW1 - (dimH * 0.5),
                arrowX,
                posY + (double) order.getCarportWidth() - offsetW1 + (dimH * 0.5)));

        svg.append(String.format(templateText, arrowX - 5, posY + ((double) order.getCarportWidth() * 0.5), -90, (int) (order.getCarportWidth() - offsetW1) + " cm"));

        // Arrow (right)
        if (materials.get(0).getQuantity() > 2) {
            for (int i = 0; i < (materials.get(0).getQuantity() - 1); i++) {
                svg.append(String.format(templateLineArrow,
                        posX + (double) order.getCarportLength() + 50,
                        posY + offsetW1 + (width * i) - (dimH * 0.5),
                        posX + (double) order.getCarportLength() + 50,
                        posY + (offsetW1 + width * (i + 1)) + (dimH * 0.5) * i));

                svg.append(String.format(templateText, posX + (double) order.getCarportLength() + 67, posY + offsetW1 + (width * 0.5) + (width * i), -90, ((order.getCarportWidth() - offsetW1) * 0.5) + " cm"));
            }
        }

        // Arrow (bottom)
        double arrowY = 40;
        svg.append(String.format(templateLineArrow,
                posX - (dimH * 0.5),
                posY + arrowY + (double) order.getCarportWidth(),
                posX + (dimH * 0.5) + (double) order.getCarportLength(),
                posY + arrowY + (double) order.getCarportWidth()));

        svg.append(String.format(templateText, posX + ((double) order.getCarportLength() * 0.5), posY + arrowY + 15 + (double) order.getCarportWidth(), 0, (order.getCarportLength() + " cm")));
    }

    // Stolper
    public void drawPost(HttpServletRequest request) {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("postList");

        double posX = 120;
        double posY = 60;
        double offsetW1 = 35;
        double offsetL1 = 110;
        double offsetL2 = 25;

        double dimH = (double) materials.get(0).getHeight() / 10;
        double dimW = (double) materials.get(0).getWidth() / 10;

        int maxWidth = 600;
        int divByW = 2;
        if (order.getCarportWidth() > maxWidth) {
            divByW++;
        }

        int maxLength = 480;
        int divByL = 3;
        if (order.getCarportLength() < maxLength) {
            divByL--;
        }

        double width = ((double) order.getCarportWidth() - (offsetW1 * 2)) / ((materials.get(0).getQuantity() / divByL) - 1);
        double length = (((double) order.getCarportLength() - (offsetL1 + offsetL2)) / ((materials.get(0).getQuantity() / divByW) - 1));

        for (int i = 0; i < (materials.get(0).getQuantity() / divByW); i++) {
            for (int j = 0; j < divByW; j++) {
                svg.append(String.format(templateRectangle, posX + (length * i) + offsetL1 - (dimH * 0.5), posY + (width * j) + offsetW1 - (dimH * 0.5), dimH, dimW));
            }
        }
    }

    @Override
    public String toString() {
        return svg.toString() + templateFooter;
    }
}
