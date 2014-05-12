package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.RiskDAO;
import eui.miw.pfm.models.entities.RiskEntity;

/**
 *
 * @author Fred Peña
 */
public class JPARiskDAO extends JPATransactionGenericDAO<RiskEntity, Integer> implements RiskDAO {

    public JPARiskDAO() {
        super(RiskEntity.class);
    }
}
