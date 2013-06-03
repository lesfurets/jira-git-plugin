/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package com.xiplink.jira.git.revisions;

import java.util.ArrayList;
import java.util.Collection;

final class SimpleBranchFilter implements BranchFilter {

    private String[] excludePatterns = new String[] {};

    /**
     * Filter will keep branches starting by the given names (allows regexp)
     * @param patterns
     */
    public SimpleBranchFilter(String ... patterns) {
        this.excludePatterns = patterns;
    }

    public Collection<String> filter(Collection<String> branches) {
        if (excludePatterns.length == 0) {
            return branches;
        }
        // Uniquement les branches officielles : trunk ou LF_*
        Collection<String> filteredBranches = new ArrayList<String>();
        for (String branch : branches) {
            boolean branchMatches = false;
            for (String matchPattern : excludePatterns) {
                branchMatches |= branch.matches(matchPattern + ".*");
            }
            if (branchMatches) {
                filteredBranches.add(branch);
            }
        }
        return filteredBranches;
    }
}
