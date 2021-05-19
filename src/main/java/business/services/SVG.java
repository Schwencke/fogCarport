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

//    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
//    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";

    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />"; //TODO: double to INT

    public SVG(int canvasWidth, int canvasHeight, String viewBox, int posX, int posY) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.viewBox = viewBox;
        this.posX = posX;
        this.posY = posY;
        svg.append(String.format(headerTemplate, canvasWidth, canvasHeight, viewBox, posX, posY));
    }

    // Spær
    public void addRafters(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("rafterlist");

        int posX = 0;
        int posY = 0;
        int dimH = materials.get(0).getHeight() / 10;
        //int width = ((materials.get(0).getWidth() / 10) / materials.get(0).getQuantity() - 1);
        int height = (order.getCarportLength() / (materials.get(0).getQuantity() - 1));

        for (int i = 0; i < ((materials.get(0).getQuantity())); i++) {
            svg.append(String.format(rectTemplate, posX + (height * i), posY, order.getCarportWidth(), dimH));
        }
    }

    // Remme
    public void addBeams(HttpServletRequest request) {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> materials = (List<Material>) session.getAttribute("beamlist");

        int posX = 0;
        int posY = 0;
        int offsetY = 35;
        int dimH = materials.get(0).getHeight() / 10;
        int width = (order.getCarportWidth() - (offsetY * 2)) / (materials.get(0).getQuantity() - 1);

        for (int i = 0; i < materials.get(0).getQuantity(); i++) {
            svg.append(String.format(rectTemplate, posX, posY + (width * i) + offsetY, dimH, order.getCarportLength()));
        }
    }

    public void addPosts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        List<Material> postList = (List<Material>) session.getAttribute("postList");
        int overhangSides = 35;
        int overhangFront = 110;
        int overhangBack = 25;
        int start = 100 + overhangSides; // starting distance from viewbox
        int second = order.getCarportWidth() + overhangSides;
        int finish = order.getCarportLength() - overhangBack;
        int distance = 100 + overhangFront; //starting distance from viewbox increments to get space between posts
        int quant;
        for (Material material : postList) {
            int height = material.getHeight() / 10;
            int width = material.getWidth() / 10;
            quant = material.getQuantity();
            for (int i = 1; i < quant + 1; i++) {
                if (i <= quant / 2) {
                    if (i < quant / 2) {
                        svg.append(String.format(rectTemplate, (distance - width / 2), start - 2, height, width));
                        distance += 300;
                    } else {
                        distance = (finish + 100) - height;
                        svg.append(String.format(rectTemplate, (distance - width / 2), start - 2, height, width));
                        distance = 100 + overhangFront;
                    }
                }

                if (i > quant / 2) {
                    if (i == quant) {
                        distance = (finish + 100) - height;
                        svg.append(String.format(rectTemplate, (distance - width / 2), second - 2, height, width));

                    } else {
                        svg.append(String.format(rectTemplate, (distance - width / 2), second - 2, height, width));
                        distance += 300;
                    }
                }
            }
        }
    }

    public void addRect(double x, double y, double height, double width) {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}
