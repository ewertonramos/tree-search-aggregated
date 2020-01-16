package com.holidu.tree;

import com.holidu.config.TreeDataConfig;
import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.GenericType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TreeRetrieverImpl implements TreeRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeRetrieverImpl.class);

    private TreeDataConfig treeDataConfig;

    @Autowired
    public TreeRetrieverImpl(TreeDataConfig treeDataConfig) {
        this.treeDataConfig = treeDataConfig;
    }

    @Override
    public Optional<List<Tree>> getTreesInRadius(BigDecimal cartesianX, BigDecimal cartesianY, Integer radius) {
        LOGGER.debug("Finding trees with the configs {}", treeDataConfig.toString());

        String baseUrl = treeDataConfig.getBaseUrl();
        String resourceId = treeDataConfig.getResourceId();

        Soda2Consumer consumer = Soda2Consumer.newConsumer(baseUrl);

        String whereClause = generateWhereClause(cartesianX, cartesianY, radius);

        SoqlQuery departmentOfStateQuery = new SoqlQueryBuilder()
                .addSelectPhrase("spc_common")
                .setWhereClause(whereClause)
                .build();

        Optional<List<TreeData>> treeObjects = Optional.empty();
        try {
            GenericType<List<TreeData>> genericType = new GenericType<List<TreeData>>() {};
            treeObjects = Optional.of(consumer.query(resourceId, departmentOfStateQuery, genericType));
            LOGGER.debug("Returned from server {} ", treeObjects);
        } catch (SodaError e) {
            LOGGER.error("API error.", e);
        } catch (InterruptedException e) {
            LOGGER.error("Interruption error.", e);
        }

        Function<List<TreeData>, ArrayList<Tree>> newList = ArrayList::new;
        return Optional.of(treeObjects.map(newList).orElseGet(ArrayList::new));
    }

    /**
     * The where clause is based on the cartesian circle formula.
     * (x - xc)^2 + (y - yc)^2 = r^2
     *
     * @param cartesianX axis x from the center of the circle
     * @param cartesianY axis y from the center of the circle
     * @param radius radius of the circle
     * @return formula to filter based on location
     */
    private String generateWhereClause(BigDecimal cartesianX, BigDecimal cartesianY, Integer radius) {
        String x = cartesianX.setScale(5, RoundingMode.HALF_EVEN).toString();
        String y = cartesianY.setScale(5, RoundingMode.HALF_EVEN).toString();
        String r = radius.toString();
        return String.format(treeDataConfig.getWhereFormula(), x, y, r);
    }
}
