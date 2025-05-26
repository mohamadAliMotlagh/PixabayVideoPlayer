package com.motlagh.core.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor

class MainCoroutineExtension @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher() // or StandardTestDispatcher
) : BeforeEachCallback, AfterEachCallback, TestInstancePostProcessor {
    override fun postProcessTestInstance(testInstance: Any?, context: ExtensionContext?) {
        (testInstance as? UsesTestDispatcher)?.testDispatcher = testDispatcher
    }

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}

interface UsesTestDispatcher {
    var testDispatcher: TestDispatcher // A property to hold the dispatcher
}