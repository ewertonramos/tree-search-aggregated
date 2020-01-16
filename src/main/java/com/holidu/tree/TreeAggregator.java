package com.holidu.tree;

import java.util.List;
import java.util.Map;

public interface TreeAggregator {

    Map<String, Integer> aggregateTrees(List<Tree> trees);

}
