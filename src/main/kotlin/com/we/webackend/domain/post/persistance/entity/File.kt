package com.we.webackend.domain.post.persistance.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.we.webackend.domain.user.persistance.entity.BaseTimeEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class File(
    fileUrl: String,
    fileName: String
): BaseTimeEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val fileUrl: String = fileUrl

    val fileName: String = fileName

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JsonIgnore
    var portfolio: Portfolio? = null
        protected set

    fun changePortfolio(portfolio: Portfolio) {
        this.portfolio = portfolio
    }


}
