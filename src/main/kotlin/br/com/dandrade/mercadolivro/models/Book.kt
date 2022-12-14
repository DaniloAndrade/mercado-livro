package br.com.dandrade.mercadolivro.models

import br.com.dandrade.mercadolivro.controller.exception.BadRequestException
import br.com.dandrade.mercadolivro.enums.BookStatus
import br.com.dandrade.mercadolivro.enums.Errors
import org.hibernate.Hibernate
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
) {

    constructor(
        id: Long? = null,
        name: String,
        price: BigDecimal,
        status: BookStatus?,
        customer: Customer? = null
    ) : this(id, name, price, customer) {
        this.status = status
    }


    @Enumerated(value = EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw BadRequestException(Errors.ML0102.message.format(field?.name), Errors.ML0102.code)
            }
            field = value
        }


    fun delete() {
        this.status = BookStatus.DELETADO
    }

    fun cancelar() {
        this.status = BookStatus.CANCELADO
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}
