package br.com.github.renamrgb.catalog.admin.application

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class UseCaseTest {

    @Test
    fun test() {
        assertNotNull(UseCase())
        assertNotNull(UseCase().execute())
    }
}