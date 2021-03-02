package com.dsorcelli.newfeaturesproject

import com.dsorcelli.newfeaturesproject.utils.SimpleDatabase
import com.dsorcelli.newfeaturesproject.utils.SimpleService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class SimpleServiceTest {

    @Mock
    lateinit var extMockDatabase: SimpleDatabase

    @Before
    fun setUp() {
        println("Setup before executing tests")
    }

    @After
    fun tearDown() {
        println("Tear down after tests execution")
    }

    @Test
    fun `when invoke query with database not available, then query should fail`() {

        // given
        val mockDatabase = mock<SimpleDatabase> {
            on { isAvailable() } doReturn false
        }

        val service = SimpleService(mockDatabase)

        // execute
        val result = service.query("select * from TABLE")

        // assert
        assertFalse(result)
    }

    @Test
    fun `when invoke toString then method getUniqueId should be invoked`() {

        val service = SimpleService(extMockDatabase)

        // execute
        service.toString()

        // verify method invocation
        verify(extMockDatabase).getUniqueId()
    }

}