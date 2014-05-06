/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

/**
 *
 * @author Jean Mubaied
 */

import eui.miw.pfm.models.dao.interfaces.CalendarTemplateDAO;
import eui.miw.pfm.models.entities.CalendarTemplateEntity;

public class JPACalendarTemplateDAO extends JPATransactionGenericDAO<CalendarTemplateEntity, Integer> implements CalendarTemplateDAO{

    public JPACalendarTemplateDAO(){
       super(CalendarTemplateEntity.class);
    }
}