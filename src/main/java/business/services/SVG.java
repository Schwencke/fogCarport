package business.services;

import business.entities.Material;
import business.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.geom.Arc2D;
import java.util.List;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(double x, double y, double height, double width) {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    //lægter
    public void addRafters(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> rafterlist = (List<Material>) session.getAttribute("rafterList");
        double start = 100.0; // starting distance from viewbox
        double distance = 100.0; //starting distance from viewbox increments to get space between rafters
        for (Material material : rafterlist) {
            double height = (double) material.getHeight() / 10;
            double length = (double) material.getLength() / 10;
            for (int i = 0; i < material.getQuantity(); i++) {
                svg.append(String.format(rectTemplate, distance, start, length, height));
                distance += ((order.getCarportLength() - height) / (material.getQuantity() - 1.0));
            }
        }
    }

    //remme
    public void addBeams(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> beamsList = (List<Material>) session.getAttribute("beamList");
        double overhangSides = 35.0;
        double start = 100.0 + overhangSides; // starting distance from viewbox
        double distance = 100.0; //starting distance from viewbox increments to get space between beams
        for (Material material : beamsList) {
            double height = (double) material.getHeight() / 10;
            double width = (double) material.getWidth() / 10;
            double length = (double) material.getLength() / 10;
            for (int i = 0; i < material.getQuantity(); i++) {
                svg.append(String.format(rectTemplate, distance, start, height, length));
                start = order.getCarportWidth() + overhangSides;
            }
        }
    }

    public void addPosts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> postList = (List<Material>) session.getAttribute("postList");
        double overhangSides = 35;
        double overhangFront = 110;
        double overhangBack = 25;
        double start = 100 + overhangSides; // starting distance from viewbox
        double second = order.getCarportWidth() + overhangSides;
        double finish = order.getCarportLength() - overhangBack;
        double distance = 100 + overhangFront; //starting distance from viewbox increments to get space between posts
        int quant;
        for (Material material : postList) {
            double height = (double) material.getHeight() / 10;
            double width = (double) material.getWidth() / 10;
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
