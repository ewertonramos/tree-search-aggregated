package com.holidu.tree;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreeAggregatorImpl implements TreeAggregator {
    @Override
    public Map<String, Integer> aggregateTrees(@NotNull List<Tree> trees) {
        Assert.notNull(trees, "Trees should not be null.");
        Map<String, Integer> map = new HashMap<>();
        trees.forEach(tree -> map.merge(tree.getName(), 1, Integer::sum));
        map.remove(null);
        return map;
    }
}
