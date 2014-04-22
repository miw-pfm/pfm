package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.ProjectDAO;
import eui.miw.pfm.models.entities.ProjectEntity;

public class JPAProjectDAO extends JPATransactionGenericDAO<ProjectEntity, Integer> implements ProjectDAO{

    public JPAProjectDAO() {
        super(ProjectEntity.class);
    }
    
}
