package com.holidu.tree;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface TreeRetriever {

    Optional<List<Tree>> getTreesInRadius(BigDecimal cartesianX, BigDecimal cartesianY, Integer radius);

}
