package bsu.rfe.java.group8.lab8.Yury.varA4.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bsu.rfe.java.group8.lab8.Yury.varA4.entity.User;
import bsu.rfe.java.group8.lab8.Yury.varA4.entity.UserList;
import bsu.rfe.java.group8.lab8.Yury.varA4.entity.UserListHelper;
import bsu.rfe.java.group8.lab8.Yury.varA4.entity.UserList.UserExistsException;

import java.io.IOException;

public class AddUser extends SimpleTagSupport {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);

        if (user.getLogin()==null || user.getLogin().equals("")) {
            errorMessage = "Login cannot be empty!";

        } else {
            if (user.getName()==null || user.getName().equals("")) {
                errorMessage = "Username cannot be empty!";
            }
        }

        if (errorMessage==null) {
            try {
                userList.addUser(user);
                UserListHelper.saveUserList(userList);
            } catch (UserExistsException e) {
                errorMessage = "A user with this login already exists!";
            }
        }

        getJspContext().setAttribute("errorMessage", errorMessage,PageContext.SESSION_SCOPE);
    }

}