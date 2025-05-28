package com.motlagh.lint

import com.android.tools.lint.detector.api.CURRENT_API
import com.motlagh.lint.DomainAndroidImportDetector.Companion.ISSUE_ANDROID_IMPORT_IN_DOMAIN
import com.motlagh.lint.LineSpacingDetector.Companion.ISSUE_LINE_SPACING
import com.motlagh.lint.NamingConventionDetector.Companion.ISSUE_REPOSITORY_NAMING
import com.motlagh.lint.NamingConventionDetector.Companion.ISSUE_USECASE_NAMING
import com.motlagh.lint.NamingConventionDetector.Companion.ISSUE_VIEWMODEL_NAMING


class IssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val api = CURRENT_API

    override val issues
        get() = listOf(
            ISSUE_MISSING_EMPTY_LINES_AROUND_BLOCK_STATEMENTS,
            ISSUE_ANDROID_IMPORT_IN_DOMAIN,
            ISSUE_VIEWMODEL_NAMING,
            ISSUE_LINE_SPACING,
            ISSUE_USECASE_NAMING,
            ISSUE_REPOSITORY_NAMING
        )
}