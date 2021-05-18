package bsu.rfe.java.group8.lab9.Yury.varA4.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bsu.rfe.java.group8.lab9.Yury.varA4.entity.User;
import bsu.rfe.java.group8.lab9.Yury.varA4.entity.UserList;

public class Login extends SimpleTagSupport {

    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        UserList userList = (UserList) 	getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);

        if (login==null || login.equals("")) {
            errorMessage = "Enter login!";

        } else {
            User user = userList.findUser(login);

            if (user==null || !user.getPassword().equals(password)) {
                errorMessage = "Invalid password!";

            } else {
                getJspContext().setAttribute("authUser", user,  PageContext.SESSION_SCOPE);
            }
        }

        getJspContext().setAttribute("errorMessage", errorMessage,  PageContext.SESSION_SCOPE);
    }
}
