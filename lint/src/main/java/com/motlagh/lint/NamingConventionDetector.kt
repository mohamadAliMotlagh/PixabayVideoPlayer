package com.motlagh.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UClass

class NamingConventionDetector : Detector(), SourceCodeScanner {

    companion object {
        val ISSUE_VIEWMODEL_NAMING = Issue.create(
            id = "ViewModelNaming",
            briefDescription = "ViewModel class must be suffixed with 'ViewModel'",
            explanation = "All classes extending ViewModel must have names ending with 'ViewModel' to maintain consistency.",
            category = Category.CORRECTNESS,
            priority = 9,
            androidSpecific = true,
            severity = Severity.ERROR,
            implementation = Implementation(NamingConventionDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )

        val ISSUE_USECASE_NAMING = Issue.create(
            id = "UseCaseNaming",
            briefDescription = "UseCase class must be suffixed with 'UseCase'",
            explanation = "All domain-level use cases should have names ending with 'UseCase' to reflect their role.",
            category = Category.CORRECTNESS,
            priority = 9,
            androidSpecific = true,
            severity = Severity.ERROR,
            implementation = Implementation(NamingConventionDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )

        val ISSUE_REPOSITORY_NAMING = Issue.create(
            id = "RepositoryNaming",
            briefDescription = "Repository class must be suffixed with 'Repository'",
            explanation = "All data/repository classes should have names ending with 'Repository'.",
            category = Category.CORRECTNESS,
            priority = 9,
            androidSpecific = true,
            severity = Severity.ERROR,
            implementation = Implementation(NamingConventionDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun applicableSuperClasses(): List<String> = listOf(
        "androidx.lifecycle.ViewModel"
    )

    override fun visitClass(context: JavaContext, declaration: UClass) {
        val className = declaration.name ?: return
        val filePath = context.file.absolutePath.replace("\\", "/")

        when {
             className.endsWith("ViewModel").not() -> {
                context.report(
                    ISSUE_VIEWMODEL_NAMING,
                    declaration,
                    context.getNameLocation(declaration),
                    "ViewModel must end with 'ViewModel'"
                )
            }

            filePath.contains("/domain/") && filePath.contains("usecase", ignoreCase = true) && className.endsWith("UseCase").not() -> {
                context.report(
                    ISSUE_USECASE_NAMING,
                    declaration,
                    context.getNameLocation(declaration),
                    "UseCase in domain layer must end with 'UseCase'"
                )
            }

            filePath.contains("/domain/") && filePath.contains("repository", ignoreCase = true) && className.endsWith("Repository").not() -> {
                context.report(
                    ISSUE_REPOSITORY_NAMING,
                    declaration,
                    context.getNameLocation(declaration),
                    "Repository in domain layer must end with 'Repository'"
                )
            }
        }
    }
}
