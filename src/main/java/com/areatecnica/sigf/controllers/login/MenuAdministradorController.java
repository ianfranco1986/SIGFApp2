package com.areatecnica.sigf.controllers.login;

import com.areatecnica.sigf.dao.IRolMenuDao;
import com.areatecnica.sigf.dao.impl.RolMenuDaoImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Omer Faruk KURT
 * @e-mail kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.com
 */
@ManagedBean
@ViewScoped
public class MenuAdministradorController implements java.io.Serializable {

    private static final long serialVersionUID = 7481372634818437093L;

    private String pageLink;
    private String pageName;
    private List<Menu> menuList;
    private String searhText;
    private IRolMenuDao dao;

    /**
     *
     */
    public MenuAdministradorController() {
        this.pageLink = "blankPage";
        this.pageName = "Dashboard";
        
        this.pageLink = "/app/operacion/bus/index";

    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    /**
     *
     * @return
     */
    public String getPageName() {
        return pageName;
    }

    /**
     *
     * @param pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public List<Menu> getMenuList() {

        dao = new RolMenuDaoImpl();

        
        
        if (menuList == null) {
            menuList = new ArrayList<Menu>();

            int auxId = 0;

            
            //MENUS
            
            menuList.add(new Menu(1,
                            "Buses",
                            null,
                            1,
                            null,
                            "fa fa-bars",
                            "white",
                            1));
            
            menuList.add(new Menu(2,
                            "Conductores",
                            null,
                            1,
                            null,
                            "fa fa-bars",
                            "white",
                            1));
            
            menuList.add(new Menu(3,
                            "Informes",
                            null,
                            1,
                            null,
                            "fa fa-bars",
                            "white",
                            1));
            
            //ITEMS
            
            menuList.add(new Menu(99,
                    "Listado de Buses",
                    1,
                    2,
                    "/app/operacion/bus/index",
                    "fa fa-bars",
                            null,
                    100));

        } else {

        }

        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

//    /**
//     *
//     * @return
//     */
//    public List<Menu> menuList() {
//        return menuDao.findAll();
//    }
    public String getSearhText() {
        return searhText;
    }

    public void setSearhText(String searhText) {
        this.searhText = searhText;
    }

    /**
     *
     * @param link
     * @param name
     */
    public void setPage(String link, String name) {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> map = context.getViewRoot().getViewMap();
        List<String> list = new ArrayList<>();

        for (String key : map.keySet()) {
            if (!key.equals("menuController")) {
                list.add(key);
            }
        }

        if (list != null && !list.isEmpty()) {
            for (String get : list) {
                map.remove(get);
            }
        }

        setPageLink(link);
        setPageName(name);
    }

    public void menuSearchValueChange(ValueChangeEvent event) {
        System.out.println("Menu Search Value Change");
        if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {

            System.out.println("Event getOldValue");
            System.out.println("menuListSize:" + menuList.size());
        }

        for (int i = 0; i < menuList.size(); i++) {
            Menu get = menuList.get(i);
            if (get.getTopMenuId() != null) {
                Menu topMenu = new Menu();//menuDao.getTopMenu(get.getTopMenuId());

                if (topMenu != null) {
                    if (!menuList.contains(topMenu)) {
                        menuList.add(topMenu);
                    }
                }
            }
        }
    }

}
