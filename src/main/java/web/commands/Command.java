package web.commands;

import business.exceptions.UserException;
import business.persistence.Database;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    // Return a token string from the execute method to make a client side redirect,
    // instead of a server side (forward) redirect
    public final static String REDIRECT_INDICATOR = "#*redirect*#_###_";
    public final static String WAS_NOT_FOUND_COMMAND = "404_NOT_FOUND";

    private static HashMap<String, Command> commands;
    public static Database database;

    private static void initCommands(Database database) {
        commands = new HashMap<>();
        commands.put("admin", new CommandOrderList("admin", "salesperson"));
        commands.put("admincalculate", new CommandPriceCalculations("order", "salesperson"));
        commands.put("adminorder", new CommandOrder("order", "salesperson"));
        commands.put("adminsvgdraw", new CommandSVGGenerate("order", "salesperson"));
        commands.put("adminupdatemeasurements", new CommandUpdateMeasurements("order", "salesperson"));
        commands.put("adminupdatestatus", new CommandUpdateStatus("admin", "salesperson"));
        commands.put("carportrequest", new CommandCarportRequest("index", "customer"));
        commands.put("commandlogin", new CommandLogin(""));
        commands.put("commandlogout", new CommandLogout(""));
        commands.put("commandsignup", new CommandSignup(""));
        commands.put("checkout", new CommandCheckout("checkout", "customer"));
        commands.put("customer", new CommandOrderList("customer", "customer"));
        commands.put("customerorder", new CommandOrderCustomer("order", "customer"));
        commands.put("customerupdatestatus", new CommandUpdateStatus("customer", "customer"));
        commands.put("index", new CommandUnprotectedPage("index"));
        commands.put("login", new CommandUnprotectedPage("login"));
        commands.put("payment", new CommandUpdateStatus("payment", "customer"));
        commands.put("signup", new CommandUnprotectedPage("signup"));
    }

    public static Command fromPath(
            HttpServletRequest request,
            Database db) {
        String action = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + action);

        if (commands == null) {
            database = db;
            initCommands(database);
        }

        return commands.getOrDefault(action, new CommandUnknown());   // This command is default
    }

    public abstract String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws UserException, SQLException;
}
