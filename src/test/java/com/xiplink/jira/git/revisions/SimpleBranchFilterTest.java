/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package com.xiplink.jira.git.revisions;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleBranchFilterTest {

    public static Collection<String> branchesMatch = new ArrayList<String>();
    public static Collection<String> branchesNotMatch = new ArrayList<String>();
    public static Collection<String> branches = new ArrayList<String>();

    @BeforeClass
    public static void setUp() {
        branchesMatch.add("master");
        branchesMatch.add("stable/qa");
        branchesMatch.add("trunk");
        branchesMatch.add("LF_2.8");
        branchesMatch.add("AMX_1.28");
        
        branchesNotMatch.add("perso/fmo/mabranche");
        branchesNotMatch.add("remote-run/dba");
        branchesNotMatch.add("readable");
        
        branches.addAll(branchesMatch);
        branches.addAll(branchesNotMatch);
        
    }

    @Test
    public void noFilterKeepSameBranches() {
        final SimpleBranchFilter defaultBranchFilter = new SimpleBranchFilter();
        final Collection<String> filteredBranches = defaultBranchFilter.filter(branches);
        Assert.assertSame(branches, filteredBranches);
    }

    @Test
    public void simpleFilterDoNotKeepPersoAndRemote() {
        final SimpleBranchFilter branchFilter = new SimpleBranchFilter("trunk", "LF_", "AMX_", "stable", "master");
        final Collection<String> filteredBranches = branchFilter.filter(branches);
        for (String branch : branchesMatch){
            Assert.assertTrue("This branch should be accepted : " + branch,filteredBranches.contains(branch));
        }
        for (String branch : branchesNotMatch){
            Assert.assertFalse("This branch should not be accepted : " + branch, filteredBranches.contains(branch));
        }
        
    }
}
