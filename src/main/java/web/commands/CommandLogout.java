package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandLogout extends CommandUnprotectedPage {

    public CommandLogout(String pageToShow) {
        super(pageToShow);
        // Here you can insert code to run before logout
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        pageToShow = "index";
        return REDIRECT_INDICATOR + pageToShow;
    }
}
