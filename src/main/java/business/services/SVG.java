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

    private final String headerTemplate = "<svg " +
            "height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\" " +
            "y=\"%d\" " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";

    public SVG(int canvasWidth, int canvasHeight, String viewBox, int posX, int posY) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.viewBox = viewBox;
        this.posX = posX;
        this.posY = posY;
        svg.append(String.format(headerTemplate, canvasWidth, canvasHeight, viewBox, posX, posY));
    }

    // Spær
    public void drawRafter(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("rafterlist");

        double posX = 0;
        double posY = 0;
        double dimH = (double) materials.get(0).getHeight() / 10;
        double height = ((double) order.getCarportLength() - dimH) / (double) (materials.get(0).getQuantity() - 1);

        for (int i = 0; i < ((materials.get(0).getQuantity())); i++) {
            svg.append(String.format(rectTemplate, posX + (height * i), posY, (double) order.getCarportWidth(), dimH));
        }
    }

    // Remme
    public void drawBeam(HttpServletRequest request) {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("beamlist");

        double posX = 0;
        double posY = 0;
        double offsetW1 = 35;
        double dimH = (double) materials.get(0).getHeight() / 10;
        double width = ((((double) order.getCarportWidth() - (offsetW1 * 2)) / (materials.get(0).getQuantity() - 1)));

        for (int i = 0; i < materials.get(0).getQuantity(); i++) {
            svg.append(String.format(rectTemplate, posX, posY + (width * i) + offsetW1 - (dimH * 0.5), dimH, (double) order.getCarportLength()));
        }
    }

    // Stolper
    public void drawPost(HttpServletRequest request) {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("postList");

        double posX = 0;
        double posY = 0;
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
                svg.append(String.format(rectTemplate, posX + (length * i) + offsetL1 - (dimH * 0.5), posY + (width * j) + offsetW1 - (dimH * 0.5), dimH, dimW));
            }
        }
    }

    /*
        public void addRect(double x, double y, double height, double width) {
            svg.append(String.format(rectTemplate, x, y, height, width));
        }

        public void addLine(int x1, int y1, int x2, int y2) {
            svg.append(String.format(lineTemplate, x1, y1, x2, y2));
        }

        public void addSvg(SVG innerSVG) {
            svg.append(innerSVG.toString());
        }
    */
    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}
