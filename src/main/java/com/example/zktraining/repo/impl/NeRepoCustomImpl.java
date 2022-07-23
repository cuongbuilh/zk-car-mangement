package com.example.zktraining.repo.impl;

import com.example.zktraining.repo.NeRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NeRepoCustomImpl  implements NeRepoCustom {
    @Autowired
    private EntityManager entityManager;
    @Override
    public void deleteNes(Boolean checkAll, List<Integer> neIds) {
        String sql = "delete from ne where ";
        if (checkAll){
            if (neIds.isEmpty()){
                sql.concat(" 1 = 1 ");
            }else {
                sql.concat(" id not in :neId");
            }
        }else {
            sql.concat(" id in :neId ");
        }
        Query query = entityManager.createNativeQuery(sql);
        if (checkAll && !neIds.isEmpty()){
            query.setParameter("neId", neIds);
        }else {
            query.setParameter("neId", neIds);
        }
        query.executeUpdate();
    }
}
