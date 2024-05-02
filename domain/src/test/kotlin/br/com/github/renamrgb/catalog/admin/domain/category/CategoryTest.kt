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


        assertValidations(actualCategory, 1, "'name' should not be null.")
    }

    @Test
    fun givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        val expectedName = ""
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)

        assertValidations(actualCategory, 1, "'name' should not be empty.")
    }

    @Test
    fun givenAnInvalidNameLengthLess3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        val expectedName = "Fi "
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)

        assertValidations(actualCategory, 1, "'name' must be between 3 and 255 characters.")
    }

    @Test
    fun givenAnInvalidNameLengthMore255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        val expectedName = "F".repeat(256)
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)

        assertValidations(actualCategory, 1, "'name' must be between 3 and 255 characters.")
    }

    @Test
    fun givenAnValidEmptyDescription_whenCallNewCategory_thenInstantiateACategory() {
        val expectedName = "Filmes"
        val expectedDescription = ""
        val expectedIsActive = true

        val aActualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)

        Assertions.assertDoesNotThrow { aActualCategory.validate(ThrowsValidationHandler()) }

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
    fun givenAValidFalseIsActive_whenCallNewCategory_thenInstantiateACategory() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = false

        val aActualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)

        Assertions.assertDoesNotThrow { aActualCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertNotNull(aActualCategory)
        Assertions.assertNotNull(aActualCategory.id)
        Assertions.assertEquals(expectedName, aActualCategory.name)
        Assertions.assertEquals(expectedDescription, aActualCategory.description)
        Assertions.assertEquals(expectedIsActive, aActualCategory.active)
        Assertions.assertNotNull(aActualCategory.createdAt)
        Assertions.assertNotNull(aActualCategory.updatedAt)
        Assertions.assertNotNull(aActualCategory.deletedAt)
    }

    @Test
    fun givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivate() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertNull(aCategory.deletedAt)
        Assertions.assertTrue(aCategory.active!!)
        val updateAt = aCategory.updatedAt
        val createdAt = aCategory.createdAt

        val actualCategory = aCategory.deactivate()
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(aCategory.id, actualCategory.id)
        Assertions.assertEquals(expectedName, actualCategory.name)
        Assertions.assertEquals(expectedDescription, actualCategory.description)
        Assertions.assertFalse(actualCategory.active!!)
        Assertions.assertEquals(createdAt, actualCategory.createdAt)
        Assertions.assertTrue(actualCategory.updatedAt!!.isAfter(updateAt))
        Assertions.assertNotNull(actualCategory.deletedAt)
    }

    @Test
    fun givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = false

        val aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertNotNull(aCategory.deletedAt)
        Assertions.assertFalse(aCategory.active!!)
        val updateAt = aCategory.updatedAt
        val createdAt = aCategory.createdAt

        val actualCategory = aCategory.activate()
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(aCategory.id, actualCategory.id)
        Assertions.assertEquals(expectedName, actualCategory.name)
        Assertions.assertEquals(expectedDescription, actualCategory.description)
        Assertions.assertTrue(actualCategory.active!!)
        Assertions.assertEquals(createdAt, actualCategory.createdAt)
        Assertions.assertTrue(actualCategory.updatedAt!!.isAfter(updateAt))
        Assertions.assertNull(actualCategory.deletedAt)
    }

    @Test
    fun givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        val updatedAt = aCategory.updatedAt
        val createdAt = aCategory.createdAt

        val updateName = "Séries"
        val updateDescription = "Categoria de séries"
        val updateIsActive = true

        val actualCategory = aCategory.update(updateName, updateDescription, updateIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(aCategory.id, actualCategory.id)
        Assertions.assertEquals(updateName, actualCategory.name)
        Assertions.assertEquals(updateDescription, actualCategory.description)
        Assertions.assertTrue(actualCategory.active!!)
        Assertions.assertEquals(createdAt, actualCategory.createdAt)
        Assertions.assertTrue(actualCategory.updatedAt!!.isAfter(updatedAt))
        Assertions.assertNull(actualCategory.deletedAt)
    }

    @Test
    fun givenAValidCategory_whenCallUpdateToInactivate_thenReturnCategoryUpdated() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }
        Assertions.assertTrue(aCategory.active!!)
        Assertions.assertNull(aCategory.deletedAt)

        val updatedAt = aCategory.updatedAt
        val createdAt = aCategory.createdAt

        val updateName = "Séries"
        val updateDescription = "Categoria de séries"
        val updateIsActive = false

        val actualCategory = aCategory.update(updateName, updateDescription, updateIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(aCategory.id, actualCategory.id)
        Assertions.assertEquals(updateName, actualCategory.name)
        Assertions.assertEquals(updateDescription, actualCategory.description)
        Assertions.assertFalse(actualCategory.active!!)
        Assertions.assertEquals(createdAt, actualCategory.createdAt)
        Assertions.assertTrue(actualCategory.updatedAt!!.isAfter(updatedAt))
        Assertions.assertNotNull(actualCategory.deletedAt)
    }

    @Test
    fun givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        val expectedName = "Filmes"
        val expectedDescription = "Categoria de filmes"
        val expectedIsActive = true

        val aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        Assertions.assertDoesNotThrow { aCategory.validate(ThrowsValidationHandler()) }
        Assertions.assertTrue(aCategory.active!!)
        Assertions.assertNull(aCategory.deletedAt)

        val updatedAt = aCategory.updatedAt
        val createdAt = aCategory.createdAt

        val updateName = null
        val updateDescription = "Categoria de séries"
        val updateIsActive = true

        val actualCategory = aCategory.update(updateName, updateDescription, updateIsActive)

        Assertions.assertEquals(aCategory.id, actualCategory.id)
        Assertions.assertEquals(updateName, actualCategory.name)
        Assertions.assertEquals(updateDescription, actualCategory.description)
        Assertions.assertTrue(actualCategory.active!!)
        Assertions.assertEquals(createdAt, actualCategory.createdAt)
        Assertions.assertTrue(actualCategory.updatedAt!!.isAfter(updatedAt))
        Assertions.assertNull(actualCategory.deletedAt)
    }

    private fun assertValidations(actualCategory: Category, errorSize: Int, expectedError: String) {
        val exception = Assertions.assertThrows(DomainException::class.java) {
            actualCategory.validate(ThrowsValidationHandler())
        }
        Assertions.assertEquals(errorSize, exception.errors.size)
        Assertions.assertEquals(expectedError, exception.errors[0].message)
    }
}


