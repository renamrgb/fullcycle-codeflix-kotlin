package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.exceptions.DomainException
import br.com.github.renamrgb.catalog.admin.domain.validation.handler.ThrowsValidationHandler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CategoryTest {

    @Test
    fun givenAvalidParams_whenCallNewCategory_thenInstantiateACategory() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val aActualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)

        Assertions.assertNotNull(aActualCategory)
        Assertions.assertNotNull(aActualCategory.id)
        Assertions.assertEquals(expectedName, aActualCategory.name)
        Assertions.assertEquals(expectedDescription, aActualCategory.description)
        Assertions.assertEquals(expectedIsActive, aActualCategory.active)
        Assertions.assertNotNull(aActualCategory.createdAt)
        Assertions.assertNotNull(aActualCategory.updatedAt)
        Assertions.assertNull(aActualCategory.deletedAt)
    }

    @Test
    fun givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        val expectedName = null
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)


        val exception = Assertions.assertThrows(DomainException::class.java) {
            actualCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertEquals(1, exception.errors.size)
        Assertions.assertEquals("'name' should not be null.", exception.errors[0].message)
    }
}