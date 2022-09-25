package br.com.dandrade.mercadolivro.models

import br.com.dandrade.mercadolivro.enums.CustomerStatus
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var name: String,
    @Column
    var email: String,
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus = CustomerStatus.ATIVO
) {


    fun inativar() {
        this.status = CustomerStatus.INATIVO
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Customer

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
