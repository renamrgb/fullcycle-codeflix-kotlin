package br.com.github.renamrgb.catalog.admin.domain

abstract class AggregateRoot<ID : Identifier> protected constructor(id: ID) : Entity<ID>(id)
