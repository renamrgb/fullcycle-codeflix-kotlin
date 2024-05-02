package br.com.github.renamrgb.catalog.admin.application.category.create

import br.com.github.renamrgb.catalog.admin.domain.category.Category
import br.com.github.renamrgb.catalog.admin.domain.category.CategoryGateway
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import java.util.*

@ExtendWith(MockitoExtension::class)
class CreateCategoryUseCaseTest(@Mock var categoryGateway: CategoryGateway) {

    @Test
    fun givenAValidCommand_whenCallCreateCategory_shouldReturnCategoryId() {
        val expectedName = "Category Name"
        val expectedDescription = "Category Description"
        val expectedIsActive = true

        val command = CreateCategoryCommand.with(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Mockito.`when`(categoryGateway.create(any())).thenAnswer { invocation ->
            return@thenAnswer invocation.arguments[0] as Category
        }

        val actualOutput = DefaultCreateCategoryUseCase(categoryGateway).execute(command)

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.id)

        Mockito.verify(categoryGateway).create(argThat { category ->
            expectedName == category.name &&
                    expectedDescription == category.description &&
                    expectedIsActive == category.active &&
                    Objects.nonNull(category.createdAt) &&
                    Objects.nonNull(category.updatedAt) &&
                    Objects.isNull(category.deletedAt) &&
                    Objects.nonNull(category.id)
        })
    }
}