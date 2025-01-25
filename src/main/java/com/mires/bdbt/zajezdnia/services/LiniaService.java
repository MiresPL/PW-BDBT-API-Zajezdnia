package com.mires.bdbt.zajezdnia.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class LiniaService {
    private final EntityManager entityManager;

    public LiniaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object[]> getRozkladJazdy() {
        String sql = "SELECT p.NAZWA AS przystanek, p.GOOGLE_MAPS_LINK as mapLink, " +
                "TO_CHAR(SYSDATE + NUMTODSINTERVAL( " +
                "    (TO_NUMBER(TO_CHAR(lp.CZAS_PRZEJAZDU, 'HH24')) * 3600) + " +
                "    (TO_NUMBER(TO_CHAR(lp.CZAS_PRZEJAZDU, 'MI')) * 60) + " +
                "    TO_NUMBER(TO_CHAR(lp.CZAS_PRZEJAZDU, 'SS')), 'SECOND'), 'HH24:MI:SS') AS czasPrzyjazdu, " +
                "TO_CHAR(SYSDATE + NUMTODSINTERVAL( " +
                "    (TO_NUMBER(TO_CHAR(lp.CZAS_PRZEJAZDU, 'HH24')) * 3600) + " +
                "    (TO_NUMBER(TO_CHAR(lp.CZAS_PRZEJAZDU, 'MI')) * 60) + " +
                "    TO_NUMBER(TO_CHAR(lp.CZAS_PRZEJAZDU, 'SS')) + " +
                "    (TO_NUMBER(TO_CHAR(lp.CZAS_POSTOJU, 'HH24')) * 3600) + " +
                "    (TO_NUMBER(TO_CHAR(lp.CZAS_POSTOJU, 'MI')) * 60) + " +
                "    TO_NUMBER(TO_CHAR(lp.CZAS_POSTOJU, 'SS')), 'SECOND'), 'HH24:MI:SS') AS czasOdjazdu " +
                "FROM LINIA_PRZYSTANKI lp " +
                "JOIN PRZYSTANEK p ON lp.ID_PRZYSTANKU = p.ID_PRZYSTANKU " +
                "WHERE lp.ID_LINII = ( " +
                "    SELECT l.ID_LINII FROM LINIA l WHERE l.ID_LINII = 1) " +
                "ORDER BY lp.KOLEJNOSC_NA_TRASIE";


        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();
        System.out.println(result.size());
        System.out.println(result.get(0)[0]);
        System.out.println(result.get(0)[1]);
        System.out.println(result.get(0)[2]);
        System.out.println(result.get(0)[3]);
        return result;
    }
}
