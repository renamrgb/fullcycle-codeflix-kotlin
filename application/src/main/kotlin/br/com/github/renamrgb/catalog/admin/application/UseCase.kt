package br.com.github.renamrgb.catalog.admin.application

abstract class UseCase<IN, OUT> {
    abstract fun execute(input: IN): OUT
}
