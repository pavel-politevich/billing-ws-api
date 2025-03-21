package by.com.lifetech.billingapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HibernateDetailedStatistic {
    Statistics commonStatistic;
    Map<String, QueryStatistics> queryStatistics;
}
