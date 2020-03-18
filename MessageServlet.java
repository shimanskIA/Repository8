package l8s;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.lang.Thread;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import l8e.ChatMessage;
import l8e.ChatUser;


public class MessageServlet extends ChatServlet
{
    private static final long serialVersionUID = 1L;
    private long actionTimeout;

    public void init() throws ServletException
    {
        super.init();
        String value = getServletConfig().getInitParameter("ACTION_TIMEOUT");
        if (value!=null)
        {
            actionTimeout = Long.parseLong(value);
        }
        new Thread(new Runnable()
        {
            public void run()
            {
                while(true)
                {
                    Collection<ChatUser> aUser = activeUsers.values();
                    Iterator<ChatUser> iter = aUser.iterator();
                    while(iter.hasNext())
                    {
                        ChatUser bUser = iter.next();
                        if (Calendar.getInstance().getTimeInMillis() - bUser.getLastMessageTime() > actionTimeout * 60 * 1000 && Calendar.getInstance().getTimeInMillis() - bUser.getLastInteractionTime() > actionTimeout * 60 * 1000)
                        {
                            messages.add(new ChatMessage("Анекдот хаха", bUser, Calendar.getInstance().getTimeInMillis()));
                            activeUsers.get(bUser.getName()).setLastMessageTime(Calendar.getInstance().getTimeInMillis());
                            break;
                        }
                    }
                }
            }
        }).start();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        String message = (String)request.getParameter("message");

        if (message != null && !"".equals(message))
        {
            ChatUser author = activeUsers.get((String)request.getSession().getAttribute("name"));
            activeUsers.get((String)request.getSession().getAttribute("name")).setLastMessageTime(Calendar.getInstance().getTimeInMillis());
            synchronized (messages)
            {
                messages.add(new ChatMessage(message, author, Calendar.getInstance().getTimeInMillis()));
            }
        }
        response.sendRedirect("compose_message2.html");
    }

}
