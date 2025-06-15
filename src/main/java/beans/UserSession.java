package beans;

import dh.DHKeyExchange;
import java.util.ArrayList;
import java.util.List;

public class UserSession {
    private String username;
    private DHKeyExchange dh;
    private List<String> messages = new ArrayList<>();
    private boolean keyExchanged = false;

    public void initDH() {
        this.dh = new DHKeyExchange(DHKeyExchange.DEFAULT_P, DHKeyExchange.DEFAULT_G);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public DHKeyExchange getDh() { return dh; }

    public List<String> getMessages() { return messages; }
    public void addMessage(String message) { messages.add(message); }

    public boolean isKeyExchanged() { return keyExchanged; }
    public void setKeyExchanged(boolean keyExchanged) { this.keyExchanged = keyExchanged; }
}
