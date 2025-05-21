package com.motlagh.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiWhiteSpace
import org.jetbrains.uast.*
import java.util.EnumSet

class LineSpacingDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE_LINE_SPACING = Issue.create(
            id = "LineSpacing",
            briefDescription = "Enforces single blank lines between code blocks",
            explanation = """
                Ensures that there is exactly one blank line between code blocks to improve readability.
            """,
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.ERROR,
            implementation = Implementation(
                LineSpacingDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE)
            )
        )
    }

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {
        override fun visitMethod(node: UMethod) {
            val psi = node.sourcePsi ?: return
            val prevSibling = psi.prevSibling
            if (prevSibling is PsiWhiteSpace) {
                val text = prevSibling.text
                val newlineCount = text.count { it == '\n' }
                if (newlineCount != 2) { // One blank line means two newline characters
                    context.report(
                        ISSUE_LINE_SPACING,
                        node,
                        context.getLocation(node),
                        "Method should be preceded by a single blank line."
                    )
                }
            }
        }
    }
}
