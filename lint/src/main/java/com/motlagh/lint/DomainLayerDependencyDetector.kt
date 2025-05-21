package com.motlagh.lint


import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement

class DomainLayerDependencyDetector : Detector(), SourceCodeScanner {

    companion object {
        val ISSUE_DOMAIN_DEPENDS_ON_OTHER_LAYERS: Issue = Issue.create(
            id = "DomainLayerDependency",
            briefDescription = "Domain layer should not depend on data or presentation layers",
            explanation = """
                According to Clean Architecture principles, the domain layer should be independent and not depend on the data or presentation layers.
            """,
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.ERROR,
            implementation = Implementation(
                DomainLayerDependencyDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UImportStatement::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitImportStatement(node: UImportStatement) {
                val filePath = context.file.absolutePath
                if (!filePath.contains("/domain/") && !filePath.contains(".domain.")) return

                val importPath = node.importReference?.asRenderString() ?: return

                if (importPath.contains(".data.") || importPath.contains("/data/") ||
                    importPath.contains(".presentation.") || importPath.contains("/presentation/")
                ) {
                    context.report(
                        ISSUE_DOMAIN_DEPENDS_ON_OTHER_LAYERS,
                        node,
                        context.getLocation(node),
                        "Domain layer should not depend on '${if (importPath.contains("data")) "data" else "presentation"}' layer: $importPath"
                    )
                }
            }
        }
    }
}
