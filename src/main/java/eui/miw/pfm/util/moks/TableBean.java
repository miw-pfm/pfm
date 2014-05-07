package eui.miw.pfm.util.moks;

import eui.miw.pfm.controllers.beans.Bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class TableBean extends Bean implements Serializable {

    private List<Player> players;

    public TableBean() {
        populatePlayers();
    }

    public void populatePlayers() {
        players = new ArrayList<Player>();

        Player proyectox = new Player("Proyecto X");
        proyectox.getStats().add(new Stats("Tarea A", 2));
        proyectox.getStats().add(new Stats("Tarea B", 3));
        proyectox.getStats().add(new Stats("Tarea C", 8));
        players.add(proyectox);

        Player proyectoy = new Player("Proyecto Y");
        proyectoy.getStats().add(new Stats("Tarea M", 7));
        proyectoy.getStats().add(new Stats("Tarea N", 8));
        players.add(proyectoy);

        Player proyectoz = new Player("Proyecto Z");
        proyectoz.getStats().add(new Stats("Tarea O", 1));
        proyectoz.getStats().add(new Stats("Tarea P", 1));
        proyectoz.getStats().add(new Stats("Tarea Q", 3));
        proyectoz.getStats().add(new Stats("Tarea R", 5));
        players.add(proyectoz);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
