package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.UseCaseDAO;
import eui.miw.pfm.models.entities.UseCaseEntity;

public class JPAUseCaseDAO extends JPATransactionGenericDAO<UseCaseEntity, Integer> implements UseCaseDAO{

    public JPAUseCaseDAO() {
        super(UseCaseEntity.class);
    }
    
}
