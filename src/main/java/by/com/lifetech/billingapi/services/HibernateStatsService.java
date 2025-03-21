package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.models.dto.HibernateDetailedStatistic;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class HibernateStatsService {
    private final SessionFactory sessionFactory;

    public HibernateDetailedStatistic returnDetailedStatistics() {
        Statistics stats = sessionFactory.getStatistics();
        HibernateDetailedStatistic hibernateDetailedStatistic = new HibernateDetailedStatistic();
        hibernateDetailedStatistic.setCommonStatistic(stats);
        hibernateDetailedStatistic.setQueryStatistics(new HashMap<>());

        String[] queries = stats.getQueries();
        for (String query : queries) {
            QueryStatistics queryStats = stats.getQueryStatistics(query);
            hibernateDetailedStatistic.getQueryStatistics().put(query, queryStats);
        }

        return hibernateDetailedStatistic;
    }

    public void changeHibernateStatisticsStatus(String status) {
        boolean isAct = status.equalsIgnoreCase("act");
        Statistics stats = sessionFactory.getStatistics();

        if (stats.isStatisticsEnabled() == isAct) {
            return;
        }

        if (!isAct) {
            stats.clear();
        }

        stats.setStatisticsEnabled(isAct);
    }
}
