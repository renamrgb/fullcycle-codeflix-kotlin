package br.com.github.renamrgb.catalog.admin.application

abstract class UnitUseCase<IN> {
    abstract fun execute(input: IN)
}
