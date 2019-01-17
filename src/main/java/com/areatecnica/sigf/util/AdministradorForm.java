package com.areatecnica.sigf.util;


import com.areatecnica.sigf.entities.Administrador;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Omer Faruk KURT e-mail:kurtomerfaruk@gmail.com
 * @email kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.wordpress.com
 */
public class AdministradorForm {

    private String sessionId;
    private Administrador usuario;
    private Date sessionCreatedTime;
    private String userMachineName;
    private String userIpAdress;
    private HttpSession session;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Administrador getUsuario() {
        return usuario;
    }

    public void setUsuario(Administrador usuario) {
        this.usuario = usuario;
    }

    public Date getSessionCreatedTime() {
        return sessionCreatedTime;
    }

    public void setSessionCreatedTime(Date sessionCreatedTime) {
        this.sessionCreatedTime = sessionCreatedTime;
    }

    public String getUserMachineName() {
        return userMachineName;
    }

    public void setUserMachineName(String userMachineName) {
        this.userMachineName = userMachineName;
    }

    public String getUserIpAdress() {
        return userIpAdress;
    }

    public void setUserIpAdress(String userIpAdress) {
        this.userIpAdress = userIpAdress;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
