package com.holidu.tree;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerTest {

    @Mock
    private TreeRetriever treeRetriever;
    @Mock
    private TreeAggregator treeAggregator;

    private TreeController treeController;

    @Before
    public void setUp() throws Exception {
        treeController = new TreeController(treeRetriever, treeAggregator);
    }

    @Test
    public void should_return_map() throws Exception {
        //given
        BigDecimal x = BigDecimal.ONE;
        BigDecimal y = BigDecimal.TEN;
        Integer r = 5;

        ArrayList<Tree> treeList = new ArrayList<>();
        HashMap<String, Integer> mapResult = new HashMap<>();

        given(treeRetriever.getTreesInRadius(any(BigDecimal.class), any(BigDecimal.class), any(Integer.class)))
                .willReturn(Optional.of(treeList));
        given(treeAggregator.aggregateTrees(treeList))
                .willReturn(mapResult);

        //when
        Map<String, Integer> result = treeController.aggregateTrees(x, y, r)
                .orElseThrow(Exception::new);

        //then
        assertTrue(result.isEmpty());
        verify(treeRetriever, times(1)).getTreesInRadius(x, y, r);
        verify(treeAggregator, times(1)).aggregateTrees(treeList);
    }
}