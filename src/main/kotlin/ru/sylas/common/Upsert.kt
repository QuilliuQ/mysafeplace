package ru.sylas.common

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.*

fun <T : UUIDEntity, A : Any> UUIDEntityClass<T>.upsert(column: Column<A>, value: A, init: T.() -> Unit): T {
    val existing = this.findOneByCol(column, value)

    return if (existing == null) {
        this.new(init)
    } else {
        existing.apply(init)
        existing
    }
}


fun <T : UUIDEntity, A : Any> UUIDEntityClass<T>.findOneByCol(column: Column<A>, value: A): T? {
    return this.find { column eq value }.firstOrNull()
}
fun <T : IntEntity, A : Any> IntEntityClass<T>.upsert(column: Column<A>, value: A, init: T.() -> Unit): T {
    val existing = this.findOneByCol(column, value)

    return if (existing == null) {
        this.new(init)
    } else {
        existing.apply(init)
        existing
    }
}


fun <T : IntEntity, A : Any> IntEntityClass<T>.findOneByCol(column: Column<A>, value: A): T? {
    return this.find { column eq value }.firstOrNull()
}