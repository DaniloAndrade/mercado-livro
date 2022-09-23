package br.com.dandrade.mercadolivro.models

import br.com.dandrade.mercadolivro.enums.BookStatus
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

    @Enumerated(value = EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    fun delete() {
        this.status = BookStatus.DELETADO
    }

}
