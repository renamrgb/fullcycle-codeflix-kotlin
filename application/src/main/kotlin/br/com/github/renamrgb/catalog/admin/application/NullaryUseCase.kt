package br.com.github.renamrgb.catalog.admin.application

abstract class NullaryUseCase<OUT> {
    abstract fun execute(): OUT
}
