package com.motlagh.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UImportStatement
import java.util.EnumSet

class DomainAndroidImportDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE_ANDROID_IMPORT_IN_DOMAIN = Issue.create(
            id = "AndroidImportInDomain",
            briefDescription = "Domain layer should not import Android classes",
            explanation = "The domain layer should be platform-independent and must not depend on Android framework classes.",
            category = Category.CORRECTNESS,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(
                DomainAndroidImportDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE)
            )
        )
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitImportStatement(node: UImportStatement) {
                val importText = node.importReference?.asRenderString() ?: return
                if (importText.startsWith("android") || importText.startsWith("androidx")) {
                    context.report(
                        ISSUE_ANDROID_IMPORT_IN_DOMAIN,
                        node,
                        context.getLocation(node),
                        "Avoid importing Android classes in the domain layer."
                    )
                }
            }
        }
    }
}
