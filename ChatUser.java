package l8e;

public class ChatUser
{
    private String name;
    private long lastInteractionTime;
    private String sessionId;
    private long lastMessageTime;

    public ChatUser(String name,long lastInteractionTime, String sessionId)
    {
        super();
        this.name = name;
        this.lastInteractionTime = lastInteractionTime;
        this.sessionId = sessionId;
        this.lastMessageTime = lastInteractionTime;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getLastInteractionTime()
    {
        return lastInteractionTime;
    }

    public void setLastInteractionTime(long lastInteractionTime)
    {
        this.lastInteractionTime = lastInteractionTime;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public void setLastMessageTime(long lastMessageTime)
    {
        this.lastMessageTime = lastMessageTime;
    }

    public long getLastMessageTime()
    {
        return lastMessageTime;
    }
}
