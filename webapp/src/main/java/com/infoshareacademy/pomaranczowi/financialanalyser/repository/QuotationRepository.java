package com.infoshareacademy.pomaranczowi.financialanalyser.repository;

import com.infoshareacademy.pomaranczowi.financialanalyser.domain.Quotation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class QuotationRepository {

    @PersistenceContext(unitName = "pUnit")
    private EntityManager entityManager;

    public boolean addOrUpdateQuotation(Quotation quotation) {
        entityManager.flush();
        entityManager.merge(quotation);
        System.out.println("Quotation " + quotation + " added ora update");
        return true;
    }

    /*public void addPriceToQuotation(Long quotationId ,List<Price> priceToAdded){
        entityManager.createQuery("update Quotation q set q.prices=:priceToAdded where q.id=:quotationId")
                .setParameter("priceToAdded",priceToAdded)
                .setParameter("quotationId",quotationId)
                .executeUpdate();
        System.out.println("Dodano price with id: ");
        for (Price price : priceToAdded) System.out.println(price.getId() + " ");
    }*/

}


