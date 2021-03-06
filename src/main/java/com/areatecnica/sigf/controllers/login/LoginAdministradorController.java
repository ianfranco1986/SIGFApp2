package com.areatecnica.sigf.controllers.login;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ILoginAdministradorDao;
import com.areatecnica.sigf.dao.IUsuarioSessionDao;
import com.areatecnica.sigf.dao.impl.LoginAdministradorDaoImpl;
import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.facade.UsuarioSessionFacade;
import com.areatecnica.sigf.util.AdministradorForm;
import com.areatecnica.sigf.util.CommonPage;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omer Faruk KURT
 * @e-mail kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.com
 */
@ManagedBean
@SessionScoped
public class LoginAdministradorController implements java.io.Serializable {

    private IUsuarioSessionDao iUsuarioSessionDao;
    @Inject
    private UsuarioSessionFacade usuarioSessionFacade;

    private static final long serialVersionUID = 1553957937211717410L;

    private String username;
    private String password;
    private boolean loggedIn;
    private ILoginAdministradorDao dao;
    private Administrador usuario;
    private int sessionCount;
    private List<Administrador> usuarioList;
    private String ipAddress;

    /**
     *
     */
    public LoginAdministradorController() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        dao = new LoginAdministradorDaoImpl();
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public Administrador getUsuario() {
        return usuario;
    }

    public void setUsuario(Administrador usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     *
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *
     * @return
     */
    public int getSessionCount() {
        return CommonPage.getUserSessionSize();
    }

    public List<Usuario> getStaffList() {
        return CommonPage.getUsuarioList();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     */
    public void login() {
        try {
            if (!username.equals("") && !username.equals(null) && !password.equals("") && !password.equals(null)) {
                usuario = dao.login(username, password);
            }

            if (usuario != null) {
                AdministradorForm form = new AdministradorForm();
                loggedIn = true;

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }

                form.setSessionId(request.getSession().getId());
                form.setSessionCreatedTime(new Date(session.getCreationTime()));
                form.setUserIpAdress(ipAddress);
                form.setSession(request.getSession());
                form.setUsuario(usuario);

                //CommonPage.addStaff(form, request.getSession().getId());

                session.setAttribute("staff", usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("index_administrador.xhtml");

//                UsuarioSession usuarioSession = new UsuarioSession();
//                usuarioSession.setUsuarioSessionIdUsuario(usuario);
//                usuarioSession.setUsuarioSessionIpAddress(ipAddress);
//
//                //iUsuarioSessionDao = new IUsuarioSessionDaoImpl();
//                usuarioSessionFacade.create(usuarioSession);

            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/MyBundle").getString("alertUsername"));
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            JsfUtil.addSuccessMessage("Su sessi??n ha expirado");

            //CommonPage.removeStaff(usuario);
            /*EventBus eventBus = EventBusFactory.getDefault().eventBus();
            eventBus.publish("/counter", String.valueOf(CommonPage.getUsuarioList().size()));*/
            loggedIn = false;
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("login_administrador.xhtml");

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(LoginAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onIdle() {
        JsfUtil.addErrorMessage("Sin Actividad durante 5 minutos, su sesi??n expirar??");
    }

    public void onActive() {
        JsfUtil.addSuccessMessage("Su sessi??n se ha reiniciado");
    }

    public void timeout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("...loginpage.xhtml");

    }

}
