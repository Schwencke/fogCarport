package business.services;

import business.entities.Material;
import business.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.geom.Arc2D;
import java.util.List;

public class SVG
{
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" "+
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";

    public SVG(int x, int y, String viewBox, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y ));
    }

    public void addRect(int x, int y, double height, double width)
    {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }
    //lægter
    public void addRafters(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Material> rafterlist = (List<Material>) session.getAttribute("rafterList");
        int start = 100; // starting distance from viewbox
        int distance = 100; //starting distance from viewbox increments to get space between rafters
        for (Material material : rafterlist) {
            double height = (double)material.getHeight() / 10;
            double length = (double) material.getLength() / 10;
            for (int i = 0; i < material.getQuantity(); i++) {
               svg.append(String.format(rectTemplate, distance, start, length, height));
                distance += 60;
            }
        }
    }
        //remme
    public void addBeams(HttpServletRequest request){
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> beamsList = (List<Material>) session.getAttribute("beamList");
        int overhangSides = 35;
        int start = 100 +overhangSides; // starting distance from viewbox
        int distance = 100; //starting distance from viewbox increments to get space between beams
        for (Material material : beamsList) {
            double height = (double)material.getHeight() / 10;
            double width = (double) material.getWidth() / 10;
            double length = (double) material.getLength() / 10;
            for (int i = 0; i < material.getQuantity(); i++) {
                svg.append(String.format(rectTemplate, distance, start, height, length));
                //distance += 60;
            }
        }
    }

    public void addPosts(HttpServletRequest request){
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Material> postList = (List<Material>) session.getAttribute("postList");
        int overhangSides = 35;
        int overhangFront = 110;
        int overhangBack = 25;
        int start = 100 + overhangSides; // starting distance from viewbox
        int distance = 100 + overhangFront; //starting distance from viewbox increments to get space between posts
        int mincount = 2;
        int count = 0;
        for (Material material : postList) {
            double height = (double)material.getHeight() / 10;
            double width = (double) material.getWidth() / 10;
            for (int i = 0; i < material.getQuantity(); i++) {
                if (distance < order.getCarportLength()) {
                    svg.append(String.format(rectTemplate, (int) (distance - width / 2), start, height, width));
                    distance += 300;
                    count++;
                    if (distance > order.getCarportLength() && count <2){
                        distance = order.getCarportLength() + overhangFront - overhangBack;
                        svg.append(String.format(rectTemplate, (int) (distance - width / 2), start, height, width));
                    }
                }
            }

        }
    }

    public void addLine(int x1, int y1, int x2, int y2 )
    {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }



    public void addSvg(SVG innerSVG)
    {
        svg.append(innerSVG.toString());
    }

    @Override
    public String toString()
    {
        return svg.toString() + "</svg>" ;
    }
}
