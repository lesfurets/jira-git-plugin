/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package com.xiplink.jira.git.revisions;

import java.util.Collection;

interface BranchFilter {
    Collection<String> filter(Collection<String> branches);
}