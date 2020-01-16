package com.holidu.tree;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class TreeAggregatorTest {

    private ArrayList<Tree> trees;
    private TreeAggregator treeAggregator;

    @Before
    public void setUp() throws Exception {
        treeAggregator = new TreeAggregatorImpl();
        trees = new ArrayList<>();
    }

    @Test
    public void should_aggregate_trees() {
        //given
        trees.add(new TreeData("tree1"));
        trees.add(new TreeData("tree2"));
        trees.add(new TreeData("tree2"));

        //when
        Map<String, Integer> result = treeAggregator.aggregateTrees(trees);

        //then
        assertThat(result, Matchers.hasEntry("tree1", 1));
        assertThat(result, Matchers.hasEntry("tree2", 2));
    }

    @Test
    public void should_aggregate_empty_trees() {
        //given
        trees.add(new TreeData());

        //when
        Map<String, Integer> result = treeAggregator.aggregateTrees(trees);

        //then
        assertThat(result.size(), Matchers.equalTo(0));
    }

    @Test
    public void should_not_contain_null_key_on_result() {
        //given

        //when
        Map<String, Integer> result = treeAggregator.aggregateTrees(trees);

        //then
        assertThat(result.size(), Matchers.equalTo(0));
    }
}