package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.util.Languaje;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yiyi
 */
@SessionScoped
@Named
public class I18nBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private Languaje languaje;

    public Locale getLocale() {
        return locale;
    }

    public Languaje getLanguaje() {
        return languaje;
    }

    public void setLanguaje(final Languaje languaje) {
        assert languaje != null;
        this.languaje = languaje;
        switch (languaje) {
            case ESPANOL:
                change("es");
                break;
            case ENGLISH:
                change("en");
                break;
            default:
                break;
        }
    }

    public void setLocale(final Locale locale) {
        assert locale != null;
        this.locale = locale;
    }

    private void change(final String languaje) {
        assert languaje != null;
        this.locale = new Locale(languaje);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String msg(final String key) {
        assert key != null;
        return ResourceBundle.getBundle("eui.miw.pfm.i18n.messages", this.locale).getString(key);
    }

    public Languaje[] getLanguajes() {
        return Languaje.values();
    }
}
