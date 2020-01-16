package com.holidu.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TreeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeController.class);

    private TreeRetriever treeRetriever;
    private TreeAggregator treeAggregator;

    @Autowired
    public TreeController(TreeRetriever treeRetriever, TreeAggregator treeAggregator) {
        this.treeRetriever = treeRetriever;
        this.treeAggregator = treeAggregator;
    }

    @GetMapping("/v1/tree/aggregate")
    public Optional<Map<String, Integer>> aggregateTrees(
            @RequestParam("cartesianX") BigDecimal cartesianX,
            @RequestParam("cartesianY") BigDecimal cartesianY,
            @RequestParam("radius") Integer radius) {

        Optional<List<Tree>> trees = treeRetriever.getTreesInRadius(cartesianX, cartesianY, radius);
        LOGGER.debug("Found {} trees", trees.map(List::size).orElse(0));
        return trees.map(treeList -> treeAggregator.aggregateTrees(treeList));

    }

}
