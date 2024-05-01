package br.com.github.renamrgb.catalog.admin.infrastructure

import br.com.github.renamrgb.catalog.admin.infrastructure.br.com.github.renamrgb.catalog.admin.infrastructure.Main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun testMain() {
        Assertions.assertNotNull(Main())
        Main.main(emptyArray<String>())
    }
}